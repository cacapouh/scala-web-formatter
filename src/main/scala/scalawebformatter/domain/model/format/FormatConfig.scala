package scalawebformatter.domain.model.format

case class FormatConfig(maxLineWidth: Int, indentWidth: Int) {
  def subtractMaxLineWidth: FormatConfig = copy(maxLineWidth = maxLineWidth - indentWidth)
  def indent: String = List.fill(indentWidth)(" ").mkString
  def nestIndent: FormatConfig = copy(indentWidth = indentWidth * 2)
}
