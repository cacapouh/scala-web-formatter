package scalawebformatter.ui

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.server.blaze.BlazeServerBuilder
import scalawebformatter.di.UiModule

import scala.concurrent.ExecutionContext.global

object WebServer extends IOApp {
  implicit val cs = IO.contextShift(global)
  override implicit val timer = IO.timer(global)

  val routes = new UiModule().routes

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder.apply[IO](global)
      .bindHttp(8080, "localhost")
      .withHttpApp(routes)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
}
