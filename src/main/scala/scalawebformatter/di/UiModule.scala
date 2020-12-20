package scalawebformatter.di

import cats.effect.{ContextShift, IO, Timer}
import scalawebformatter.ui.route.{FormatRoute, ReadyRoute}
import cats.syntax.semigroupk._
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT

class UiModule(implicit val cs: ContextShift[IO], timer: Timer[IO]) {
  private val readyRoute = new ReadyRoute().routes
   private val formatRoute = new FormatRoute(ApplicationModule.scalaFormatService).routes
   val routes = (readyRoute <+> formatRoute).orNotFound
}
