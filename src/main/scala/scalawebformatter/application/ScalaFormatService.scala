package scalawebformatter.application

import scalawebformatter.domain.model.format.FormatConfig
import scalawebformatter.domain.model.response.{ErrorMessage, FormattedString}

class ScalaFormatService(scalaParser: ScalaParser, scalaFormatter: ScalaFormatter) {
  def format(input: String, formatConfig: FormatConfig): Either[ErrorMessage, FormattedString] = {
    scalaParser.parse(input) match {
      case Left(value) => Left(ErrorMessage(value))
      case Right(value) =>
        val formattedStructure = scalaFormatter.format(value, formatConfig)
        Right(scalaFormatter.format(formattedStructure, formatConfig))
    }
  }
}
