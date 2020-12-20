package scalawebformatter.ui.route

import cats.effect._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import scalawebformatter.application.FormatService
import scalawebformatter.domain.model.format.FormatConfig
import scalawebformatter.ui.route.FormatRoute.ScalaString

class FormatRoute(formatService: FormatService)(implicit val cs: ContextShift[IO], timer: Timer[IO]) {
  implicit val decoder = jsonOf[IO, ScalaString]

  // TODO: フォーマットの設定値はフロントエンドから受け取るようにする
  private val formatConfig = FormatConfig(maxLineWidth = 80, indentWidth = 2)

  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req @ POST -> Root / "format" =>
      for {
        scalaString <- req.as[ScalaString]
        res <- formatService.format(scalaString.value, formatConfig) match {
          case Right(value) => Ok(value.value)
          case Left(value) => BadRequest(value.value)
        }
      } yield res
  }
}

object FormatRoute {
  case class ScalaString(value: String)
}