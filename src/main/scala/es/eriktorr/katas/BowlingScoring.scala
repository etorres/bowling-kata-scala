package es.eriktorr.katas

import es.eriktorr.katas.FrameSplitter.windowedFramesFrom

import scala.collection.parallel.CollectionConverters._

object BowlingScoring {

  def scoreGame(frameScores: String): Int = {
    windowedFramesFrom(frameScores).par
      .map(_ => Frame)
      .map(_.score)
      .sum
  }

}
