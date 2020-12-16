package scalawebformatter.application

import scalawebformatter.domain.model.response.ErrorMessage

class ScalaFormatService(scalaParser: ScalaParser) {
  def format(input: String) = {
    scalaParser.parse(input) match {
      case Left(value) => Left(ErrorMessage(value))
      case Right(value) =>
        ???
    }
  }
}
