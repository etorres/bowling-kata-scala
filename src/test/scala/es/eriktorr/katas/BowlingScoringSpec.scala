package es.eriktorr.katas

import es.eriktorr.katas.BowlingScoring.scoreGame
import org.scalatest.prop.TableDrivenPropertyChecks

class BowlingScoringSpec extends UnitSpec with TableDrivenPropertyChecks {

  private val games = Table(
    ("game", "score"),
    ("X|X|X|X|X|X|X|X|X|X||XX", 300),
    ("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||", 90),
    ("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5", 150),
    ("X|7/|9-|X|-8|8/|-6|X|X|X||81", 167),
    ("X|9/|5/|72|X|X|X|9-|8/|9/||X", 0),
    ("X|9/|5/|72|X|X|X|9-|8/|9/||-", 0),
    ("9-|8-|7-|6-|5-|4-|3-|2-|1-|9-", 0),
    ("5/|4/|3/|2/|1/|9/|8/|7/|6/|4/||7", 0),
    ("X|9/|5/|72|X|X|X|9-|8/|8/||3", 0)
  )

  "Bowling game " should "score" in {
    forAll(games) { (game, score) =>
      scoreGame(game) shouldBe score
    }
  }

}
