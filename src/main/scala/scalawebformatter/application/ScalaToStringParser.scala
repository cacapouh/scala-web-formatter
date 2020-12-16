package scalawebformatter.application

import scalawebformatter.domain.model.{ScalaCaseClass, ScalaObject, ScalaValue}

import scala.util.parsing.combinator._

class ScalaToStringParser(nestLevel: Int) {
  def parse(input: String): Either[String, ScalaObject] =
    ScalaToStringParser.parse(ScalaToStringParser.nestedCaseClass(nestLevel), input) match {
      case ScalaToStringParser.Success(v, _) => Right(v)
      case ScalaToStringParser.Failure(msg, _) => Left(msg)
    }
}

private object ScalaToStringParser extends RegexParsers {

  def value: Parser[ScalaValue] =
    "[^(),]".r.* ^^ (ss => ScalaValue(ss.mkString))

  def className: Parser[String] =
    "[^(),]".r.* ^^ (_.mkString)

  def createCaseClass(v: String ~ List[ScalaObject]): ScalaCaseClass =
    ScalaCaseClass(v._1, v._2)

  def tuple(p: Parser[ScalaObject]): Parser[List[ScalaObject]] =
    "(" ~> repsep(p, ",") <~ ")"

  def caseClass: Parser[ScalaCaseClass] =
    className ~ tuple("[^(),]".r.* ^^ (ss => ScalaValue(ss.mkString))) ^^ createCaseClass

  def nestedCaseClass(nestLevel: Int = 10): Parser[ScalaObject] = {
    nestLevel match {
      case 0 => className ~ tuple(caseClass | value) ^^ createCaseClass
      case n => className ~ tuple(nestedCaseClass(n - 1) | value) ^^ createCaseClass
    }
  }
}