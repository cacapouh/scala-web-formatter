package scalawebformatter.application

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import scalawebformatter.domain.model.format.{FormatConfig, FormattedStructure, NewLine, OneLine}
import scalawebformatter.domain.model.response.FormattedString
import scalawebformatter.domain.model.{ScalaCaseClass, ScalaValue}

class ScalaFormatterTest extends Specification with Mockito {
  val scalaFormatter = new ScalaFormatter

  trait Context extends Scope {
    val conf = FormatConfig(80, 2)
  }

  "#format" should {
    "return OneLine when input is ScalaValue" in new Context {
      val value: String = List.fill(50)("A").mkString
      val scalaValue = ScalaValue(value)
      scalaFormatter.format(scalaValue, conf) === OneLine(value)
    }

    "return OneLine when input is ScalaValue" in new Context {
      val value: String = List.fill(100)("A").mkString
      val scalaValue = ScalaValue(value)
      scalaFormatter.format(scalaValue, conf) === OneLine(value)
    }

    "return OneLine when input is ScalaCaseClass" in new Context {
      val value = List.fill(50)("A").mkString
      val scalaCaseClass = ScalaCaseClass(
        "Hoge",
        List(
          ScalaValue(value)
        )
      )
      scalaFormatter.format(scalaCaseClass, conf) === OneLine(s"Hoge($value)")
    }

    "return NewLine when input is ScalaCaseClass" in new Context {
      val value = List.fill(50)("A").mkString
      val scalaCaseClass = ScalaCaseClass(
        "Hoge",
        List(
          ScalaValue(value),
          ScalaValue(value)
        )
      )
      scalaFormatter.format(scalaCaseClass, conf) === NewLine("Hoge", List(OneLine(value), OneLine(value)))
    }
  }

  "#format" should {
    "return" in new Context {
      val formattedStructure = NewLine(
        "Hoge",
        List(
          OneLine("aaa"),
          OneLine("bbb")
        )
      )

      scalaFormatter.format(formattedStructure, conf) === FormattedString(
        """
          |Hoge(
          |  aaa,
          |  bbb
          |)
          |""".stripMargin
      )
    }
  }
}
