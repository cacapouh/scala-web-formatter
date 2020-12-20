package scalawebformatter.domain.model.format

case class FormatConfig(maxLineWidth: Int, indentWidth: Int) {
  def subtractMaxLineWidth: FormatConfig = copy(maxLineWidth = maxLineWidth - indentWidth)
}
