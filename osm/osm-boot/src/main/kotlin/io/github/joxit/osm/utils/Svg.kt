package io.github.joxit.osm.utils

import io.github.joxit.osm.model.Tile
import org.apache.batik.anim.dom.SAXSVGDocumentFactory
import org.apache.batik.anim.dom.SVGOMSVGElement
import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.ImageTranscoder
import org.apache.batik.util.XMLResourceDescriptor
import org.w3c.dom.Document
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.pow

object Svg {
  private fun getWorldSVG(): Document {
    try {
      return SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName())
          .createDocument(null, Svg::class.java.classLoader.getResourceAsStream("world.svg"))
    } catch (e: IOException) {
      throw IllegalStateException(e)
    }
  }

  @JvmStatic
  fun getTile(t: Tile): ByteArray {
    return svgToPng(getWorldSVG().tile(t.z, t.x, t.y), 256)
  }

  fun Document.tile(z: Int, x: Int, y: Int): Document {
    val elt = this.documentElement as SVGOMSVGElement
    val divider = 2.toFloat().pow(z)
    val size = elt.viewBox.baseVal.height / divider

    elt.viewBox.baseVal.x += size * x
    elt.viewBox.baseVal.y += size * y
    elt.viewBox.baseVal.height = size
    elt.viewBox.baseVal.width = size
    return this
  }

  @JvmStatic
  fun svgToPng(svg: Document, size: Int = 256): ByteArray {
    val transcoder = CustomTranscoder()
    val writer = ByteArrayOutputStream()

    transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, size.toFloat())
    transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, size.toFloat())
    transcoder.transcode(TranscoderInput(svg), null)

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