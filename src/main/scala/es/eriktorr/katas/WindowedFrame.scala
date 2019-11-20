package es.eriktorr.katas

import scala.math.min

object WindowedFrame {

  private case class ScoringWindowedFrame(frames: Array[Frame]) extends WindowedFrame {
    override def score: Int = {
      val balls = frames.flatMap(_.balls)
      val firstFrame = frames.head
      val n = min(firstFrame.scoringBalls, balls.length)
      balls.take(n).sum
    }
  }

  def apply(frameScores: Array[String]): WindowedFrame = {
    val frames: Array[Frame] = frameScores.map(FrameBuilder(_))
    ScoringWindowedFrame(frames)
  }
}

trait WindowedFrame {
  def score: Int
}
