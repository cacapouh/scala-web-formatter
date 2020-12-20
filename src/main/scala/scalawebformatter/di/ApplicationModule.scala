package scalawebformatter.di

import pureconfig.ConfigSource
import scalawebformatter.application.{FormatService, ScalaFormatter, ScalaParser}
import pureconfig.generic.auto._

object ApplicationModule {

  case class Conf(nestLevel: Int)
  val nestLevel = ConfigSource.default.at("format").loadOrThrow[Conf].nestLevel

  val scalaFormatService = new FormatService(
    new ScalaParser(nestLevel),
    new ScalaFormatter
  )
}
