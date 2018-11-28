package mazerunner

package object types {

  // Type alias for (x,y)
  type Point = (Int, Int)

  // Possible moves
  sealed trait Move
  case object U extends Move
  case object D extends Move
  case object L extends Move
  case object R extends Move

  // Type alias for solutions
  type Solution = (List[Point], Int)
}
