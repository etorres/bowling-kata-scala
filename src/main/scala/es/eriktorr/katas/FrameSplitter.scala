package es.eriktorr.katas

object FrameSplitter {

  def framesFrom(bowlingGame: String): Iterator[Array[String]] = {

    val (regularFrames, bonusFrames) = splitInto(bowlingGame)

    val bonusBalls = bonusFrames.headOption.getOrElse("--")
    val extraFrame = bonusBalls match {
      case "XX" => "X|X"
      case _ => s"${bonusBalls.padTo(2, '-')}|--"
    }

//    val frames =
      splitIntoFrames(s"${regularFrames.head}|$extraFrame")

//    var i = 0
//    for (a <- frames) {
//      for (b <- a) {
//        println(s"$i: $b")
//      }
//      println("***")
//      i = i + 1
//    }
//
//    frames
  }

  val splitInto: String => (Array[String], Array[String]) = _.split("\\|\\|").splitAt(1)

  val splitIntoFrames: String => Iterator[Array[String]] = _.split("\\|").sliding(3, 1)

}
