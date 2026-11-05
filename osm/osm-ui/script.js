import * as maplibregl from 'https://unpkg.com/maplibre-gl@^6.16.0/dist/maplibre-gl.mjs';

const URL_QUERY_PARAM_REGEX = /[&?]url=/;
const URL_PARAM_REGEX = /^url=/;
const DEFAULT_URL_ENDPOINT = 'http://127.0.0.1:8080';
const ACCESS_TOKEN_QUERY_PARAM_REGEX = /[&?]access-token=/;
const ACCESS_TOKEN_PARAM_REGEX = /^access-token=/;
const DEFAULT_ACCESS_TOKEN = ''
const emptyGeojson = { "type": "FeatureCollection", "features": [] };
const routing = {};

// Récupère la valeur du query param `url=....`
function getUrlQueryParam() {
  const search = window.location.search;
  if (URL_QUERY_PARAM_REGEX.test(search)) {
    const param = search.split(/^\?|&/).find(function(param) {
      return param && URL_PARAM_REGEX.test(param);
    });
    return param ? param.replace(URL_PARAM_REGEX, '') : DEFAULT_URL_ENDPOINT;
  }
  return DEFAULT_URL_ENDPOINT;
}

// Récupère la valeur du query param `access-token=....`
function getAccessTokenQueryParam() {
  const search = window.location.search;
  if (ACCESS_TOKEN_QUERY_PARAM_REGEX.test(search)) {
    const param = search.split(/^\?|&/).find(function(param) {
      return param && ACCESS_TOKEN_PARAM_REGEX.test(param);
    });
    return param ? param.replace(ACCESS_TOKEN_PARAM_REGEX, '') : DEFAULT_ACCESS_TOKEN;
  }
  return DEFAULT_ACCESS_TOKEN;
}

const map = new maplibregl.Map({
  container: 'map', // container id
  style: {
    "version": 8,
    "sources": {
      "raster-tiles": {
        "type": "raster",
        "tiles": [`${getUrlQueryParam()}/{z}/{x}/{y}.png`],
        "tileSize": 256
      }
    },
    "layers": [{
      "id": "simple-tiles",
      "type": "raster",
      "source": "raster-tiles",
      "minzoom": 0,
      "maxzoom": 22
    }]
  },
  center: [-74.50, 40],
  zoom: 2,
  hash: true
});

// Pour le geocoding
var iconMarkerEl = document.createElement("div");
iconMarkerEl.innerHTML = "<div class='marker-arrow'></div>";
map.addControl(new PeliasGeocoder({
  params: {'access-token': getAccessTokenQueryParam() || '<JAWG_ACCESS_TOKEN>'},
  flyTo: 'hybrid',
  wof: true,
  url: 'https://places.jawg.io/v1',
  useFocusPoint: true,
  marker: {
    icon: iconMarkerEl,
    multiple: false
  },
  customAttribution: 'Powered by <a style="color: rgb(0, 148, 255); text-decoration: none;" href="http://jawg.io" title="Attributions" target="_blank" style=""><img style="max-height: 1em;" src="https://www.jawg.io/favicon.png"/><b>Jawg</b>Maps</a>'
}));
// Des plugins souvent utilisés
map.addControl(new maplibregl.NavigationControl());
map.addControl(new maplibregl.ScaleControl());

// Fonction pour aider à créer un itinéraire.
function latLngToGeoJSON(c) {
  return {
      "type": "Feature",
      "geometry": { "type": "Point", "coordinates": [ c.lng, c.lat ] }
    }
}

// Récuperer un trajet voiture
// https://api.jawg.io/routing/route/v1/car/{lng},{lat};{lng},{lat}?access-token=<access-token>&overview=full&steps=false&geometries=geojson
// https://api.jawg.io/routing/route/v1/car/2.342865,48.858705;7.71048,44.97739?access-token=<access-token>&overview=full&steps=false&geometries=geojson
function getRouting(c1, c2) {
  return fetch(`https://api.jawg.io/routing/route/v1/car/${c1.lng},${c1.lat};${c2.lng},${c2.lat}?access-token=${getAccessTokenQueryParam()}&overview=full&steps=false&geometries=geojson`)
    // on transforme la réponse en JSON, méthode de la spec de fetch
    // https://developer.mozilla.org/fr/docs/Web/API/Body/json
    .then(res => res.json())
    .then(res => {
      // Réponse : {"code":"Ok","routes":[ { GeoJSON } ], "waypoints": [ { Metadata } ] }
      map.getSource('routing').setData(res.routes[0].geometry)
      map.getSource('routing-start').setData(latLngToGeoJSON(routing.start))
      map.getSource('routing-end').setData(latLngToGeoJSON(routing.end))
    })
}

map.on("load", () => {
  // Code executé après le chargement de la carte
  // C'est ici qu'on peut ajouter de nouvelles sources ou de nouveaux layers
  // Quelques méthodes utiles:
  // Ajout de source : map.addSource("nom-de-source", {type: "source-type", data: "data-url or content"})
  // Ajout de layer : map.addLayer("nom-de-layer", {id: "id-layer", type: "type-layer", source: "source-ref", paint: {}, layout: {}})
  map.addSource("cities", {
    "type": "geojson",
    "data": `${getUrlQueryParam()}/cities.geojson`
  })
  // Le style d'affichage pour les préfectures, un rond rouge
  map.addLayer({
    "id": "cities-circle",
    "type": "circle",
    "source": "cities",
    "filter": ["<=", ["get", "SCALERANK"], ["zoom"]],
    "paint": {
      "circle-radius": {
        "stops": [
          [0, 1],
          [5, 4]
        ]
      },
      "circle-color": "rgb(172, 59, 246)"
    }
  });
  map.addLayer( {
    "type": "symbol",
    "id": "cities-text",
    "source": "cities",
    "filter": ["<", ["get", "SCALERANK"], ["zoom"]],
    "layout": {
      "text-anchor": "top",
      "text-field": [
        "coalesce",
        [ "get", "NAME_FR" ],
        [ "get", "NAME" ]
      ],
      "text-font": [
        "Roboto Bold",
        "Noto Bold"
      ],
      "text-offset": [ 0, 0.15 ],
      "text-size": [
        "interpolate",
        [ "linear" ],
        [ "zoom" ],
        4.0, 12,
        10.0, 22
      ]
    },
    "paint": {
      "text-color": "#ffffff",
      "text-halo-blur": 1.0,
      "text-halo-color": "hsl(0, 5%, 0%)",
      "text-halo-width": 1.0
    }
  })
  // On prépare les sources pour le routing, pour le moment elle sont vides et seront remplies via un handler
  map.addSource('routing', { type: 'geojson', data: emptyGeojson })
  map.addSource('routing-start', { type: 'geojson', data: emptyGeojson })
  map.addSource('routing-end', { type: 'geojson', data: emptyGeojson })
  // Le style pour le tracé du routing
  map.addLayer({
    id: 'routing-layer',
    type: 'line',
    source: 'routing',
    layout: { 'line-cap': "round", 'line-join': "round" },
    paint: { 'line-color': "#6f6fff", 'line-width': { base: 1.5, stops: [[0, 1], [7, 10], [18, 82]] } }
  })
  // Le style pour le point de départ
  map.addLayer({
    id: 'routing-start',
    type: 'symbol',
    source: 'routing-start',
    layout: { 'icon-image': "poi-15", 'icon-size': 1.2, 'icon-anchor': "bottom" }
  })
  // le style pour le point d'arrivé
  map.addLayer({
    id: 'routing-end',
    type: 'symbol',
    source: 'routing-end',
    layout: { 'icon-image': "poi-15", 'icon-size': 1.2, 'icon-anchor': "bottom" }
  })
  // Le handler pour avoir le routing via un CTRK + click
  map.on('click', ({ lngLat, originalEvent }) => {
    if (!originalEvent.ctrlKey) { return; }
    if (routing.start && routing.end) {
      delete routing.start;
      delete routing.end;
    }
    if (!routing.start) {
      routing.start = lngLat;
    } else if (!routing.end) {
      routing.end = lngLat;
      getRouting(routing.start, routing.end)
    }
  })
});