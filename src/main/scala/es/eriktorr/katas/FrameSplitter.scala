package es.eriktorr.katas

object FrameSplitter {

  def windowedFramesFrom(frameScores: String): Iterator[Array[String]] = {
    val (regularFrames, bonusBalls) = extractFrom(frameScores)
    val extraFrame = asExtraFrame(bonusBalls)
    windowedFramesFrom(s"$regularFrames|$extraFrame", 3)
  }

  val windowedFramesFrom: (String, Int) => Iterator[Array[String]] = (frameScores: String, windowsSize: Int) => {
    frameScores.split("\\|").sliding(windowsSize, 1)
  }

  val extractFrom: String => (String, String) = (frameScores: String) => {
    val (regularFrames, bonusFrame) = frameScores.split("\\|\\|").splitAt(1)
    (regularFrames.head, bonusFrame.headOption.getOrElse("--"))
  }

  val asExtraFrame: String => String = {
    case "XX" => "X|X"
    case bonusFrame => s"${bonusFrame.padTo(2, '-')}|--"
  }

}
