/* Maze generator in Scala
 * Joe Wingbermuehle
 * 2010-10-16
 */
package mazerunner

class Maze(width: Int, height: Int) {

  // Generate a maze.
  private var maze: Array[Array[Int]] = Array.fill[Int](height, width)(1)
  private val entry = (0,1)
  private val exit  = (height - 1, width - 2)
  maze(entry._1 + 1)(entry._2) = 0

  // Generate paths.
  carve(entry._1 + 1, entry._2)
  maze(entry._1)(entry._2) = 0
  maze(exit._1)(exit._2)   = 0

   // Show the maze.
   def show {
      println("#--  " + "--"*(width-2) + "#")

      maze.foreach(row =>
         {
            print("|")
            row.foreach(block => print(if(block == 1) "[]" else "  "))
            print("|")
            println
         }
      )
      println("#" + "--"*(width-2) + "  --#")
   }

   /* Start carving at x, y. */
   private def carve(x: Int, y: Int) {

      def update_pos(dir: Int, x: Int, y: Int): (Int, Int) = dir match {
         case 0 => (x + 1, y + 0)
         case 1 => (x + 0, y + 1)
         case 2 => (x - 1, y + 0)
         case _ => (x + 0, y - 1)
      }

      var dir:    Int = (scala.math.random * 4.0).toInt
      var count:  Int = 0
      while(count < 4) {
         val (x1, y1) = update_pos(dir, x, y)
         val (x2, y2) = update_pos(dir, x1, y1)
         if(x2 > 0 && x2 < width && y2 > 0 && y2 < height) {
            if(maze(y1)(x1) == 1 && maze(y2)(x2) == 1) {
               maze(y1)(x1) = 0
               maze(y2)(x2) = 0
               carve(x2, y2)
            }
         }
         count += 1
         dir = (dir + 1) % 4
      }

   }
}

