name := "scala-web-formatter"

version := "0.1"

scalaVersion := "2.13.4"

val http4sVersion = "0.21.13"
lazy val root = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % http4sVersion
  )
)