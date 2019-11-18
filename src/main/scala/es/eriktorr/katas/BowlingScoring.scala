package es.eriktorr.katas

import es.eriktorr.katas.FrameSplitter.windowedFramesFrom

import scala.annotation.tailrec

object BowlingScoring {

  def scoreGame(frameScores: String): Int = {
    val frames = windowedFramesFrom(frameScores).to(List)
    score(frames, 0)
  }

  @tailrec
  def score(frames: List[Array[String]], scoreAccumulator: Int): Int = frames match {
    case Nil => scoreAccumulator
    case first :: allExceptFirst => score(allExceptFirst, scoreAccumulator + score(first))
  }

  val score: Array[String] => Int = (frame: Array[String]) => {
    1
  }

}
