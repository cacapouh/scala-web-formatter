package scalawebformatter.di

import cats.effect.{ContextShift, IO, Timer}
import scalawebformatter.ui.route.{FormatRoute, ReadyRoute}
import cats.syntax.semigroupk._
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT
import org.http4s.server.middleware.{CORS, CORSConfig}

import scala.concurrent.duration.DurationInt

class UiModule(implicit val cs: ContextShift[IO], timer: Timer[IO]) {
  val originConfig = CORSConfig(
    anyOrigin = false,
    allowedOrigins = Set("http://localhost:63342"),
    allowCredentials = false,
    maxAge = 1.day.toSeconds)

  private val readyRoute = new ReadyRoute().routes
  private val formatRoute = new FormatRoute(ApplicationModule.scalaFormatService).routes
  val routes = CORS(readyRoute <+> formatRoute).orNotFound
}
