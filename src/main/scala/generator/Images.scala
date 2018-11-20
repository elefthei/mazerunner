package mazerunner

import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class IMaze (height: Int, width: Int) extends Maze (height, width) {
  def write(fn: String) {

    // create new image of the same size
    val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    // copy pixels (mirror horizontally)
    for (x <- 0 until width)
      for (y <- 0 until height)
        if (maze(y)(x) == 0) {
          out.setRGB(x, y, 0xffffff)
        } else {
          out.setRGB(x, y, 0x000000)
        }

    // Set entry and exit
    out.setRGB(entry._2, entry._1, 0xff0000)
    out.setRGB(exit._2, exit._1, 0xff0000)
    ImageIO.write(out, "bmp", new File(fn))
  }
}
