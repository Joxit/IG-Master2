package io.github.joxit.osm.model

data class Tile(val z: Int, val x: Int, val y: Int) {
  /**
   * Ceci pourrait être utile pour le caching de tuiles.
   */
  val id: String = "$z/$x/$y"

  companion object {
    @JvmStatic
    fun idOf(z: Int, x: Int, y: Int) = "$z/$x/$y"
  }
}