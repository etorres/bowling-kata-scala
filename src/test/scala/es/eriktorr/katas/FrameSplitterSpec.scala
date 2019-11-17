package es.eriktorr.katas

import es.eriktorr.katas.FrameSplitter.framesFrom

class FrameSplitterSpec extends UnitSpec {

  "Any regular game" should "should be split into frames" in {
    framesFrom("X|7/|9-|X|-8|8/|-6|X|X|X||81").to(LazyList) should (have size 10 and contain inOrderOnly(
      Array("X", "7/", "9-"),
      Array("7/", "9-", "X"),
      Array("9-", "X", "-8"),
      Array("X", "-8", "8/"),
      Array("-8", "8/", "-6"),
      Array("8/", "-6", "X"),
      Array("-6", "X", "X"),
      Array("X", "X", "X"),
      Array("X", "X", "81"),
      Array("X", "81", "--")))
  }

  "A perfect game" should "be split into frames" in {
    framesFrom("X|X|X|X|X|X|X|X|X|X||XX").to(LazyList) should (have size 10 and contain only Array.fill(3){"X"})
  }

  "A game without bonus balls" should "be split into frames" in {
    framesFrom("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||").to(LazyList) should (have size 10 and contain only (
      Array.fill(3){"9-"},
      Array("9-", "9-", "--"),
      Array("9-", "--", "--")
    ))
  }

  "A game with only one bonus ball" should "be split into frames" in {
    framesFrom("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5").to(LazyList) should (have size 10 and contain only (
      Array.fill(3){"5/"},
      Array("5/", "5/", "5-"),
      Array("5/", "5-", "--")
    ))
  }

}
