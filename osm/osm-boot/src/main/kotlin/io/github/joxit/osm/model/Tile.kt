package io.github.joxit.osm.model

data class Tile(val z: Int, val x: Int, val y: Int) {
  val id: String = "$z/$x/$y"

  companion object {
    @JvmStatic
    fun idOf(z: Int, x: Int, y: Int) = "$z/$x/$y"
  }
}