package es.eriktorr.katas

object Frame extends Frame {
  override def score: Int = 30
}

trait Frame {

  def score: Int

}