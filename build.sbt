name := "scala-web-formatter"

version := "0.1"

scalaVersion := "2.13.4"

enablePlugins(JavaAppPackaging)

val http4sVersion = "1.0.0-M9"
lazy val root = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % http4sVersion,
    "org.specs2" %% "specs2-core" % "4.10.0" % "test",
    "org.specs2" %% "specs2-mock" % "4.10.5" % Test,
    "org.http4s" %% "http4s-circe" % "1.0.0-M9",
    "io.circe" %% "circe-generic" % "0.12.3",
    "com.github.pureconfig" %% "pureconfig" % "0.14.0"
  )
)
