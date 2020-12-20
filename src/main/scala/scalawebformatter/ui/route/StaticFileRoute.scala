package scalawebformatter.ui.route

import cats.effect._
import org.http4s.{HttpRoutes, Request, Response, StaticFile}
import org.http4s.dsl.io._


class StaticFileRoute(implicit val cs: ContextShift[IO], blocker: Blocker){
  val routes = HttpRoutes.of[IO] {
    case request @ GET -> Root => indexHtml(request)
  }

  private def indexHtml(request: Request[IO]): IO[Response[IO]] =
    StaticFile.fromResource("/assets/index.html", blocker, Some(request)).getOrElseF(NotFound())
}
