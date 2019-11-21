package es.eriktorr.katas

object FrameBuilder {
  private case class Regular(firstBall: Int, secondBall: Int) extends Frame {
    override val balls: Seq[Int] = Seq(firstBall, secondBall)
    override val scoringBalls: Int = 2
  }

  private case class Spare(firstBall: Int) extends Frame {
    override val balls: Seq[Int] = Seq(firstBall, 10 - firstBall)
    override val scoringBalls: Int = 3
  }

  private case object Strike extends Frame {
    override val balls: Seq[Int] = Seq(10)
    override val scoringBalls: Int = 3
  }

  def apply(frameScore: String): Frame = frameFrom(frameScore)

  private def frameFrom(frameScore: String): Frame = {
    import util.matching.Regex

    implicit class RegexContext(sc: StringContext) {
      def r = new Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
    }

    val frame = frameScore match {
      case "X" => Strike
      case "--" => Regular(0, 0)
      case s"$a-" => Regular(a.toInt, 0)
      case s"-$b" => Regular(0, b.toInt)
      case s"$a/" => Spare(a.toInt)
      case r"(\d)$a(\d)$b" => Regular(a.toInt, b.toInt)
      case _ => throw new IllegalArgumentException(s"failed to parse frame score $frameScore")
    }
    frame
  }
}

trait Frame {
  val balls: Seq[Int]
  val scoringBalls: Int
}
