package mazerunner

object Main extends App {
  args.foreach(arg => {
    // generate randomized maze
    val maze = new IMaze(32, 32)

    // Write to image file
    maze.write(arg + ".bmp")

    // Define solver
    val solver = new Solver(maze)

    // Solve
    solver.solve() match {
      case Some((path, d)) => Console.println("Path: %s with difficulty: %d", path.mkString(" -> "), d)
      case None => Console.println("Error: No path to exit found!")
    }

  })
}

