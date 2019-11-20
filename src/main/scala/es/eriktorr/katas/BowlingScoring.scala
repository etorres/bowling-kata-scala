package es.eriktorr.katas

import es.eriktorr.katas.FrameSplitter.windowedFramesFrom

import scala.collection.parallel.CollectionConverters._

object BowlingScoring {

  def scoreGame(frameScores: String): Int = {
    windowedFramesFrom(frameScores).par
      .map(WindowedFrame(_))
      .map(_.score)
      .sum
  }

}
