package scalawebformatter.ui

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.server.blaze.BlazeServerBuilder
import scalawebformatter.di.UiModule
import cats.effect._
import org.http4s.server.Server

import scala.concurrent.ExecutionContext.global

object WebServer extends IOApp {
  implicit val cs = IO.contextShift(global)
  override implicit val timer = IO.timer(global)

  def run(args: List[String]): IO[ExitCode] =
    app.use(_ => IO.never).as(ExitCode.Success)

  def app: Resource[IO, Server] = for {
      blocker <- Blocker[IO]
      routes = new UiModule()(implicitly[ContextShift[IO]], implicitly[Timer[IO]], blocker).routes
      server <- BlazeServerBuilder.apply[IO](global)
        .bindHttp(8080, "localhost")
        .withHttpApp(routes)
        .resource
    } yield {
      server
    }
}
