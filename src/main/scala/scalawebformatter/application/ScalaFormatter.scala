package scalawebformatter.application

import scalawebformatter.domain.model.format.{FormatConfig, FormattedStructure, NewLine, OneLine}
import scalawebformatter.domain.model.response.FormattedString
import scalawebformatter.domain.model.{ScalaCaseClass, ScalaObject, ScalaValue}


class ScalaFormatter {
  def format(scalaObject: ScalaObject, formatConfig: FormatConfig): FormattedStructure = scalaObject match {
    case ScalaValue(value) => OneLine(value)
    case scalaCaseClass: ScalaCaseClass =>
      val result = formatToString(scalaCaseClass)
      if (formatConfig.maxLineWidth < result.length) {
        NewLine(
          scalaCaseClass.className,
          scalaCaseClass.values.map(format(_, formatConfig.subtractMaxLineWidth))
        )
      } else {
        OneLine(result)
      }
  }

  private def formatToString(scalaObject: ScalaObject): String = scalaObject match {
    case ScalaValue(value) => value
    case ScalaCaseClass(className, values) =>
      s"$className(${values.map(formatToString).mkString(",")})"
  }

  def format(formattedStructure: FormattedStructure, formatConfig: FormatConfig): FormattedString = formattedStructure match {
    case OneLine(v) => FormattedString(v)
    case NewLine(className, values) =>
      val indent = List.fill(formatConfig.indentWidth)(" ").mkString
      val vs = values.map(format(_, formatConfig).value).map(indent + _).mkString(",\n")

      FormattedString {
        s"""
           |$className(
           |$vs
           |)
           |""".stripMargin
      }
  }
}
