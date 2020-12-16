package scalawebformatter.domain.model

sealed trait ScalaObject
case class ScalaValue(value: String) extends ScalaObject
case class ScalaCaseClass(className: String, values: List[ScalaObject]) extends ScalaObject
