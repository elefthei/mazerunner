package mazerunner

import types._

class Solver(m: Maze) {

  // get difficulty of a path
  private def difficulty(path: List[Point]) : Int  =
    path.foldLeft(0) { (a, p) =>
      a + m.getPossibilities(p).count(_ => true) - 1
    } * path.length

  // Solve maze by DFS
  def solve (start: Point = m.getEntry, path: List[Point] = List[Point]()) : Option[Solution] = {
    // Avoid cycles
    if (path.contains(start)) {
      Console.println("Cycle found at (%d, %d)", start._1, start._2)
      None
    } else {
      // Get all possibile moves from (start)
      // 1. Recurse on them (DFS)
      // 2. Compute difficulty for each path
      // 3. Find the path of least resistance and return it
      val steps = m.getPossibilities(start)
        .map(pos => pos match {
          case L => (start._1 - 1, start._2)
          case R => (start._1 + 1, start._2)
          case U => (start._1, start._2 + 1)
          case D => (start._1, start._2 - 1)
        })
        .filter(p => !path.contains(p)) // Only keep unvisited points

      // if the exit is in the next step, return
      if (steps.contains(m.getExit)) {
        Some((path + m.getExit, difficulty(path)))
      } // otherwise recurse
      else {
        steps.map(p => solve(p, path + p))
        .fold(None) { (best, p) =>
          val pdiff = difficulty(p)
          best match {
            case None => Some((p, pdiff))
            case Some((bestpath, bdiff)) if (bdiff > pdiff) => Some((p, pdiff))
            case o => o
          }
        }
      }
    }
  }
}

