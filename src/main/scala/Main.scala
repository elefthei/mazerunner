package mazerunner

object Main extends App {
  args.foreach(arg => {
    val maze = new IMaze(32, 32)
    maze.write(arg + ".bmp")
  })
}

