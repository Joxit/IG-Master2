package io.github.joxit.osm.controller

import java.io.IOException

/**
 * C'est le controlleur de l'application, il faut le déclarer comme tel et activer les CrossOrigin
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
class TileController {
  /**
   * Cette méthode est le point d'entrée de l'API, il prend les requêtes au format `/{z}/{x}/{y}.png`.
   * Attention, il doit renvoyer le header Content-Type image/png; voir les MediaType de Spring
   *
   * @param z zoom
   * @param x coordonée
   * @param y coordonée
   * @return l'image au format PNG
   */
  fun getTile(z: Int, x: Int, y: Int): ByteArray {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }

  /**
   * Cette méthode est le point d'entrée des préfectures, il prend les requêtes sur l'entrée `/prefectures.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return String representant le GeoJSON des prefectures
   */
  @Throws(IOException::class)
  fun getPrefectures(): String {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }
}
