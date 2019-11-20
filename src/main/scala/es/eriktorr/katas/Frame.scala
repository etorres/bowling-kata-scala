package es.eriktorr.katas

trait Frame {
  def balls: Seq[Int]
  def scoringBalls: Int
}

case class Regular(balls: Seq[Int]) extends Frame {
  override def scoringBalls: Int = 2
}

case class Spare(balls: Seq[Int]) extends Frame {
  override def scoringBalls: Int = 3
}

case object Strike extends Frame {
  override def balls: Seq[Int] = Seq(10)
  override def scoringBalls: Int = 3
}

object FrameBuilder {
  def apply(frameScore: String): Frame = frameFrom(frameScore)

  private def frameFrom(frameScore: String): Frame = {
    import util.matching.Regex

    implicit class RegexContext(sc: StringContext) {
      def r = new Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
    }

    val frame = frameScore match {
      case "X" => Strike
      case "--" => Regular(Seq(0, 0))
      case s"$a-" => Regular(Seq(a.toInt, 0))
      case s"-$b" => Regular(Seq(0, b.toInt))
      case s"$a/" =>
        val firstBall = a.toInt
        Spare(Seq(firstBall, 10 - firstBall))
      case r"(\d)$a(\d)$b" => Regular(Seq(a.toInt, b.toInt))
      case _ => throw new IllegalArgumentException(s"failed to parse frame score $frameScore")
    }
    frame
  }
}
