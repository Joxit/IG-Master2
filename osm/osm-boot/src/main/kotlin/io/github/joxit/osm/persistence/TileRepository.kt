package io.github.joxit.osm.persistence

import io.github.joxit.osm.model.Tile
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.pow
import org.apache.batik.anim.dom.SAXSVGDocumentFactory
import org.apache.batik.anim.dom.SVGOMSVGElement
import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.ImageTranscoder
import org.apache.batik.util.XMLResourceDescriptor
import org.springframework.core.io.Resource
import org.w3c.dom.Document

class TileRepository(private val worldSVG: Resource) {
  private fun getWorldSVG(): Document {
    try {
      return SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName())
        .createDocument(null, worldSVG.inputStream)
    } catch (e: IOException) {
      throw IllegalStateException(e)
    }
  }

  fun generateTile(tile: Tile): ByteArray {
    return getWorldSVG().tile(tile).toPng(256)
  }

  private fun Document.tile(t: Tile): Document {
    val elt = this.documentElement as SVGOMSVGElement
    val divider = 2.toFloat().pow(t.z)
    val size = elt.viewBox.baseVal.height / divider

    elt.viewBox.baseVal.x += size * t.x
    elt.viewBox.baseVal.y += size * t.y
    elt.viewBox.baseVal.height = size
    elt.viewBox.baseVal.width = size
    return this
  }

  private fun Document.toPng(size: Int = 256): ByteArray {
    val transcoder = CustomTranscoder()
    val writer = ByteArrayOutputStream()

    transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, size.toFloat())
    transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, size.toFloat())
    transcoder.transcode(TranscoderInput(this), null)

    ImageIO.write(transcoder.image, "PNG", writer)
    return writer.toByteArray()
  }

  private class CustomTranscoder : ImageTranscoder() {
    lateinit var image: BufferedImage

    override fun createImage(width: Int, height: Int) = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    override fun writeImage(img: BufferedImage, output: TranscoderOutput?) {
      image = img
    }
  }
}