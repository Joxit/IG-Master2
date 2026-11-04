package io.github.joxit.osm.controller

import io.jawg.geojson.GeoJsonObject
import java.io.IOException

/**
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
class TileController {
  /**
   * @param z zoom
   * @param x coordonée
   * @param y coordonée
   * @return ByteArray de l'image au format PNG
   */
  fun getTile(z: Int, x: Int, y: Int): ByteArray {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }

  /**
   * @return GeoJsonObject representant le GeoJSON des villes
   */
  @Throws(IOException::class)
  fun getCities(): GeoJsonObject {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }
}
