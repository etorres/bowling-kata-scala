package es.eriktorr.katas

import es.eriktorr.katas.BowlingScoring.scoreGame
import es.eriktorr.katas.spec.DynamicUnitSpec

class BowlingScoringSpec extends DynamicUnitSpec {

  private val games = Table(
    ("game", "score"),
    ("X|X|X|X|X|X|X|X|X|X||XX", 300),
    ("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||", 90),
    ("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5", 150),
    ("X|7/|9-|X|-8|8/|-6|X|X|X||81", 167),
    ("X|9/|5/|72|X|X|X|9-|8/|9/||X", 187),
    ("X|9/|5/|72|X|X|X|9-|8/|9/||-", 177),
    ("9-|8-|7-|6-|5-|4-|3-|2-|1-|9-", 54),
    ("X|9/|5/|72|X|X|X|9-|8/|8/||3", 179)
  )

  "Bowling scoring" should "score a game" in {
    forAll(games) { (game, score) =>
      scoreGame(game) shouldBe score
    }
  }

}
