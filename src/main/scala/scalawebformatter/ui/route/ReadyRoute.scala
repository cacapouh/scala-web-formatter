package scalawebformatter.ui.route

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

class ReadyRoute {
  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "ready" =>
      Ok("""{"status": "OK"}""")
  }
}
