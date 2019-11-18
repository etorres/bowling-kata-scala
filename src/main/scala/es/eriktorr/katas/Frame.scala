package es.eriktorr.katas

object Frame {
  private case class Regular(balls: Seq[Int], next: Option[Frame]) extends Frame {
    override def score: Int = balls.sum
  }

  private case class Strike(next: Option[Frame]) extends Frame {
    override def balls: Seq[Int] = Seq(10)
    override def score: Int = balls.head + nextTwoBalls
  }

  private case class Spare(balls: Seq[Int], next: Option[Frame]) extends Frame {
    override def score: Int = balls.sum + nextBall
  }

  def apply(frameScores: Array[String]): Frame = {
    val afterNext = frameFrom(frameScores, 2, None)
    val next = frameFrom(frameScores, 1, afterNext)
    frameFrom(frameScores, 0, next).get
  }

  private def frameFrom(frameScores: Array[String], position: Int, next: Option[Frame]): Option[Frame] = {
    if (frameScores.length > position) frameFrom(frameScores(position), next) else None
  }

  private def frameFrom(frameScore: String, next: Option[Frame]): Option[Frame] = {
    import util.matching.Regex

    implicit class RegexContext(sc: StringContext) {
      def r = new Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
    }

    val frame = frameScore match {
      case "X" => Some(Strike(next))
      case "--" => Some(Regular(Seq(0, 0), next))
      case s"$a-" => Some(Regular(Seq(a.toInt, 0), next))
      case s"-$b" => Some(Regular(Seq(0, b.toInt), next))
      case s"$a/" =>
        val firstBall = a.toInt
        Some(Spare(Seq(firstBall, 10 - firstBall), next))
      case r"(\d)$a(\d)$b" => Some(Regular(Seq(a.toInt, b.toInt), next))
      case _ => None
    }
    frame
  }
}

trait Frame {
  def balls: Seq[Int]
  def next: Option[Frame]
  def score: Int

  def nextTwoBalls(): Int = next match {
    case Some(frame) =>
      if (frame.balls.isEmpty) return 0
      if (frame.balls.size > 1) frame.balls.take(2).sum
      else frame.balls.head + nextBallIn(frame.next)
    case _ => 0
  }

  def nextBallIn(frame: Option[Frame]): Int = frame match {
    case Some(frame) => frame.balls.headOption.getOrElse(0)
    case _ => 0
  }

  def nextBall(): Int = next match {
    case Some(frame) =>
      if (frame.balls.isEmpty) 0
      else frame.balls.head
    case _ => 0
  }
}
