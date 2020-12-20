package scalawebformatter.domain.model.format

trait FormattedStructure

case class OneLine(value: String) extends FormattedStructure

case class NewLine(className: String, children: List[FormattedStructure]) extends FormattedStructure
