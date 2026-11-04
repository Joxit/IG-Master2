package io.github.joxit.osm.service

import io.github.joxit.osm.model.Tile
import io.jawg.geojson.GeoJsonObject
import java.io.IOException

/**
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
class TileService {
  /**
   * @param tile qu'il faut renvoyer
   * @return le byte array au format png
   */
  fun getTile(tile: Tile): ByteArray {
    TODO("À implémenter, lisez les consignes !")
  }

  /**
   * @return le contenu du fichier cities.geojson au format GeoJsonObject
   */
  @Throws(IOException::class)
  fun getCities(): GeoJsonObject {
    TODO("À implémenter, lisez les consignes !")
  }
}
