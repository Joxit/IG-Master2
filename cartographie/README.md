# Cartographie et OpenStreetMap

Si vous êtes ici, c'est parce que vous avez choisi l'option Cartographie et OpenStreetMap de ce Semestre 4 à l'Institut Galilée, je vous souhaite la bienvenue.

À l'issue de ce cours, vous aurez produit un projet et/ou aurez participé au monde d'OpenStreetMap. Chaque éléments que vous rendrez, sera pris en compte dans votre note finale.

## Restrictions frontend

- Vos interfaces peuvent être faites en utilisant [React](https://fr.react.dev/), [Vue.js](https://vuejs.org/), [Svelte](https://svelte.dev/), [Android](https://developer.android.com/) ou [React Natvie](https://reactnative.dev/) (=> Pas de iOS ou Angular).
- Plusieurs frameworks peuvent se mettre au dessus comme [Next.js](https://nextjs.org/) et [Remix](https://remix.run/) au dessus de React, [Nuxt.js](https://nuxt.com/) au dessus de Vue.js...
- Pour le fond de carte vous avez le choix entre [JawgMaps](https://jawg.io/) et [OpenStreetMap](https://www.openstreetmap.org/).
- Pour l'affichage des cartes voyez [MapLibre GL](https://maplibre.org/) et [Leaflet](https://leafletjs.com/)
  - N'hésitez pas à regarder les plugins existants pour accélerer vos developpements
  - Il y a des intégrations pour React, Vue.js etc
- Besoin de faire des calculs geospatials ? Allez voir [Turf.js](https://turfjs.org/)

Vous voulez utiliser autre chose ? Demandez mon avis ;)

## Restrictions backend

- Les langages autorisés sont JVM (Java et Kotlin), NdeJS (Javascript et TypeScript) et Rust (pour le fun)
- Idée de frameworks pour les applications web:
  - JVM:[Spring Boot](https://spring.io/projects/spring-boot), [Vert.x](https://vertx.io/), [Quarkus](https://quarkus.io/) ou [Micronaut](https://micronaut.io/)
  - NodeJS: [Express.js](https://expressjs.com/), [Nest.js](https://nestjs.com/), [Koa](https://koajs.com/), [fastify](https://fastify.dev/)...
- Bases de données: [PostgreSQL](https://www.postgresql.org/) avec [Postgis](https://postgis.net/) ou [Elasticsearch](https://www.elastic.co/fr/elasticsearch)
- Besoin de faire des calculs geospatials ? 
  - Directement en base avec [Postgis](https://postgis.net/)
  - Pour JVM avec [JTS](https://github.com/locationtech/jts), [Spatial4J](https://github.com/locationtech/spatial4j) ou [Geotools](https://github.com/geotools/geotools)
  - Pour NodeJS avec [Turf.js](https://turfjs.org/)
- Vous devez créer des APIs REST, vous serez libre pour le nommage mais également jugé sur celui-ci

Vous voulez utiliser autre chose ? Demandez mon avis ;)

## Contribution à OpenStreetMap

Notation: 5pts max.

Avant de commencer vos contributions, créez une compte sur [osm.org](https://www.openstreetmap.org/) avec votre mail personel.
Si vous faites des contributions, vous devrez me communiquer votre pseudo pour que je puisse vous noter après la dernière séance.

La contribution à OSM peut être compliquée quand on ne s'y connait pas trop. Je vous propose plusieurs alternatives pour vos contribuions, vous pourrez en tester plusieurs si vous le souhaitez.

### StreetComplete

Jawg Maps est sponsor de l'application StreetComplete depuis 2020 où nos fournissions gratuitement le fond de carte au créateur de l'application. C'est un projet open source que vous pouvez retrouver sur [GitHub.com/StreetComplete](https://github.com/streetcomplete/StreetComplete/).

:warning: StreetComplete est disponible uniquement sur Android à télécharger sur [Google Play](https://play.google.com/store/apps/details?id=de.westnordost.streetcomplete).

StreetComplete est un éditeur de données OpenStreetMap facile à utiliser et disponible pour **Android uniquement**. Il peut être utilisé sans aucune connaissance spécifique d'OpenStreetMap. Il pose des questions simples (ou comme des quêtes), avec des réponses directement utilisées pour modifier et améliorer les données OpenStreetMap. L'application est destinée aux utilisateurs qui ne connaissent rien aux schémas de tagging OSM mais qui souhaitent néanmoins contribuer à OpenStreetMap.

Votre mission est d'ajouter des informations supplémentaires sur les objets déjà existants. Attention, vos contributions devront être correctes et basés sur vos connaissances sur le terrain. Je favoriserais les contributions qui ont une utilité (nom des rues, adresses des maisons, hauteur des bâtiments, noms et types des commerces/POI, présences de trottoirs...).

### Rapid Editor

Le projet [Rapid Editor](https://rapideditor.org/) (ou Map With AI) est une initiative de Meta (Facebook/Instagram...) qui lie l'IA avec la cartographie pour aider les contributeurs à ajouter de la donnée. Ce projet vous aidera à ajouter des bâtiments ou routes qui sont basés sur de l'imagerie satellite.

Dans les règles de contributions, il est interdit de faire des ajouts de masse par des ordinateurs. C'est pour cela que Meta ne peut pas ajouter directement toute la donnée qu'ils ont généré, elle doit obligatoirement être vérifiée par un humain.

Votre mission est d'ajouter des bâtiments et/ou routes dans des zones du monde. Il vous sera peut-être demandé d'ajouter également des types sur ce que vous allez ajouter. Vous pouvez regarder le wiki OSM pour les [bâtiments](https://wiki.openstreetmap.org/wiki/Key:building) ainsi que pour les [routes](https://wiki.openstreetmap.org/wiki/Key:highway). Vous serez juger sur la précisier de vos ajouts, [Rapid Editor](https://rapideditor.org/) ajoute grossièrement les bâtiments, il faudra les rendre moins grossiers.

:bulb: Pour les bâtiments, vous pouvez utiliser Q pour le rendre orthogonal.

### OSM iD Editor

L'editeur iD est l'éditeur par défaut de OSM, c'est celui que vous avez quand vous allez sur [osm.org](https://www.openstreetmap.org/) et cliquez sur modifier. Avec cet éditeur vous avez la possibilité de tout faire, c'est l'un des plus complets et simple d'utilisation.

Votre mission est d'ajouter ce qui n'est pas possible de faire avec les autres projets. Attention, vos contributions devront être correctes et basés sur vos connaissances sur le terrain. Restez sur des objets à taille humaine, noms/tags des points d'intérêts, bâtiments, routes... Placer de façon précise les [adresses](https://wiki.openstreetmap.org/wiki/Addresses) (nous les plaçons au niveau de la porte d'entrée).

:bulb: Pour les bâtiments, vous pouvez utiliser Q pour le rendre orthogonal.

## Projet: Chasse au trésor

Vous aurez la mission de faire une chasse au trésor en utilisant bien évidemment une carte, vous pouvez au choix faire que le front ou le backend ou les deux.

Sujet: Vous êtes les assistants du célèbre Indiana Jones qui est à la recherche de l'arche perdue. Il n'a pas la position exacte de l'arche, mais il peut se reposer sur une carte interactive et des énigmes pour espérer atteindre l'arche perdue. Les règles globales sont:

- Il ne peut voir qu'ne seule énigme à la fois
- Il doit répondre correctement à l'énigme pour voir la suivante
- La réponse de l'énigme est un emplacement sur la carte

Ce projet peut se faire seul ou en binôme.
:warning: la notation est répartie pour chacun d'entre vous (i.e. si vous avez 12 points, ça peut très bien être 6 chacun ou 7 pour le premier et 3 pour le second). Étant donnée que le front est back sont indépendants, un membre du binoôme peut très bien avoir 100% des points front et l'autre 100% des points back.

### La partie frontend de la chasse au trésor

Notation: 8pts max.

Voici une liste non exaustive d'idées que vous pouvez implémenter:

- Une page web ou application contenant une carte et un emplacement pour l'énigme en cours
  - L'énigme peut être sur la carte (composant flottant) ou à l'exterieur
  - Vous pouvez afficher la liste des énigmes réussies
    - Les énigmes réussies devront être visuellement marquées
    - Dans le cas d'une composant flottant, pour voir les anciennes énigmes il faudra utiliser des boutons précédent et suivant
- L'utilisateur doit se déplacer sur la carte pour trouver l'emplacement de l'énigme
  - Le point doit donc être caché jusqu'à un certain moment avant d'être révélé, vous avez 2 choix:
    - Vous pouvez vous baser sur le niveau de zoom de l'utilisateur pour décider si le point doit être visible ou pas
      - Arrivé au zoom où la solution peut être visible, vous pouvez afficher un bouton pour indiquer une distance approximative
    - Vous pouvez demander à l'utilisateur de cliquer sur la carte
      - Au click, un POI temporaire sera affiché pour montrer le choix de l'utilisateur
      - Le POI peut être déplacé (drag and drop) ou supprimé (click dessus) ou écrasé (click sur une autre zone)
      - L'utilisateur aura un bouton pour valider son choix
      - Si le POI est proche de la solution (x km), l'énigme est réussie
      - Si le POI est trop loin de la solution, indiquez une distance approximative
- Après chaque enigme réussie, vous pouvez relier les résultats entre eux (en faisant une ligne droite ou un [grand cercle](https://fr.wikipedia.org/wiki/Grand_cercle))
- Arrivé à l'arche perdue, une célébration doit être faite (idées: un message, une image, une animation...)
- Avoir une page pour afficher toutes les actions effectuées par l'utilisateur

Dans le cas où vous faîtes sans backend:

- Vous pouvez garder toutes les énigmes et résultats dans un fichier json
- Enregistrez l'avancement de votre utilisateur dans le local storage

Dans le cas où vous faîtes backend + frontend:

- Votre frontend ne doit pas connaître les énigmes ni les emplacements des points
  - Le backend envoie uniquement l'énigme
  - Le frontend fait des requêtes avec la bbox de la carte / le point central de la carte / le point choisi par l'utilisateur pour savoir si l'énigme est résolue.
  - C'est uniquement quand l'énigme est résolue que le backend renvoie le point résultant
- Vous devez avoir un minimum de gestion d'erreur entre votre backend et frontend

### La partie backend de la chasse au trésor

Notation: 8pts max.

Voici une liste non exaustive d'idées que vous pouvez implémenter:

- Une API REST/JSON pour gérer votre chasse au trésor
- Utiliser une base de donnée qui permet d'effectuer des calculs géospatiaux (comme Postgis)
- Les énigmes, points et résultats doivent être enregistrés dans la BDD
  - L'énigme uniquement est communiqué au front, pas le résultat
  - Vous gérez l'état d'avancement de la chasse au trésor
  - À la première requête:
    - Vous générez la session si elle n'est pas présente
    - Dans le cas contraire vous envoyez la liste des points des énigmes réussies
  - À la dernière requête vous pouvez renvoyer la liste de tous les points en même temps que le lieu du trésor
- Avoir une validation sur les entrées faites par le front
  - Un format doit être défini (status code + message + code d'erreur)
- Avoir une gestion des accès à votre API via un [access-token](https://fr.wikipedia.org/wiki/Jeton_d%27acc%C3%A8s)
- Être capable de reprendre la partie d'un joueur à l'aide d'une session persistante
  - Cela peut-être un cookie
  - Cela peut-être une valeur générée, renvoyé au front et présent dans tous les échanges
- Sauvegarder l'historique des actions de votre utilisateur
  - Il devra y avoir un historique par étape => L'énigme 1 a trois actions, l'énigme 2 à 4 actions etc
- Pouvoir supprimer un historique d'actions
- Avoir une documentation de votre API pour la correction
  - Elle peut-être dans le README.md de votre projet
  - Il faut pour chaque endpoint: `method`, `uri`, `query-params`, `data` (JSON décrit), `return` (JSON décrit)

Dans le cas où vous le faites sans frontend:

- Plusieurs scripts bash qui simulent les interactions de l'utilisateur et affiche les method + uri + input + output
  - Un script du cas nominal où tout se passe bien du premier coup
  - Un script avec des actions loupées
  - Un script pour montrer l'historique d'un joueur

## Projet: Voyage et partages

Ce projet a pour but de créer une carte representant un carnet de voyage comprenant des points d'intérêts (musées, points de vues...), des photos et les tracés piétons/voiture entre les points.

### La partie frontend du voyage et partages

Notation: 8pts max.

Voici une liste non exaustive d'idées que vous pouvez implémenter:

- Une page web ou application mobile contenant une carte avec dessus
  - Des markers à des emplacements importants (musées, points de vues, hôtel, aéroport...)
    - Ils doivent être intéractifs, au clic une popup doit apparaître pour visualiser le lieu avec une ou des photos
    - Le nom du lieu doit être visible sans cliquer dessus
  - Le tracé piéton ou voiture entre chaque markers (en fonction des distances)
    - Vous devez mettre en évidence le temps de transports par mode pour le jour
    - Une différence visuelle doit être faite entre un tracé piéton et voiture
    - Essayez de ne pas dépasser 25km par jours
  - Un paneau de navigation pour aller sur différents jours
    - Vous devez avoir une flèche pour aller au jour d'avant ou d'après
    - Vous devez avoir un raccourcis pour aller sur le premier jour et dernier jour
- Pour la navigation, vous devez utiliser une API spécialisée (vous serez jugés sur votre choix du fournisseur)
- Les photos ne devront pas être directement dans votre projet, ça devra être des liens vers des services externes stockés (unsplash, instagram...)
  - Si vous faîte le choix du Server Side Rendering, elles pourront être téléchargées au run
- Vous devez être capable de mettre en évidence plusieurs voyages
- Il doit y avoir les heures d'arrivé et de départ sur les lieux
  - Il faut prendre en compte le transport et le temps passé sur place
- Vous pouvez ajouter une recherche par forward geocoding (vous serez jugés sur votre choix du fournisseur)
  - Vous pouvez également y mettre du reverse geocoding quand l'utilisateur appuie sur une touche en même temps que cliquer

Dans le cas où vous faîtes sans backend:

- Tous vos points et configurations peuvent être dans un fichier json
  - Ça doit être au minimum un fichier json par voyage

### La partie backend du voyage et partages

Notation: 6pts max.

Voici une liste non exaustive d'idées que vous pouvez implémenter:

- Une API REST/JSON pour gérer les voyages partagés
  - Votre API doit renvoyer un jour par un jour et non pas le voyage en entier
- Utiliser une base de donnée qui permet d'effectuer des calculs géospatiaux (comme Postgis)
- Les positions et informations doivent être enregestrés dans la BDD
  - Les url des photos seront enregistrées en BDD également
    - Vous avez le choix entre renvoyer l'URL simplement ou faire un proxy via votre API (votre API télécharge l'image et la met en cache)
- Avoir une validation sur les entrées faites par le front
  - Un format doit être défini (status code + message + code d'erreur)
- Avoir une gestion des accès à votre API via un [access-token](https://fr.wikipedia.org/wiki/Jeton_d%27acc%C3%A8s)
- Avoir une documentation de votre API pour la correction
  - Elle peut-être dans le README.md de votre projet
  - Il faut pour chaque endpoint: `method`, `uri`, `query-params`, `data` (JSON décrit), `return` (JSON décrit)

Dans le cas où vous le faites sans frontend:
- Vous devez avoir un endpoint pour calculer la navigation piéton/voiture
  - Vous devez utiliser une API spécialisée (vous serez jugés sur votre choix du fournisseur)
- Plusieurs scripts bash qui simulent les interactions de l'utilisateur et affiche les method + uri + input + output
  - Un script du cas nominal où vous selectionnez un voyage
  - Un script avec des actions loupées
  - Un script pour montrer l'historique d'un joueur

## Projet: Une carte à mon goût

Ce projet a pour but de créer une carte avec de la donnée open data venant exclusivement de OSM.

### La partie frontend d'une carte à mon goût

Notation: 6pts max.

Voici une liste non exaustive d'idées que vous pouvez implémenter:

- Une page web ou une application mobile contenant une carte avec dessus
  - Des markers avec la donnée que vous souhaitez mettre en valeur (restaurants, fontaines à eau, bancs, toilettes publiques...)
  - Les markers peuvent être différents en fonction de la donnée ou si vous en mettez plusieurs
- Vous devez ajouter des interactions sur les marker
  - Au click ouvrez une popup et affichez des informations tirés de la donnée OSM
  - Vous pouvez faire quelque chose au survol du marker également
- Amusez vous à créer une carte sur mesure si c'est mieux pour le thème que vous avez choisi
- Pour récupérer la donnée vous pouvez utiliser l'[API Overpass](https://overpass-turbo.eu/)