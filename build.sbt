name := "scala-web-formatter"

version := "0.1"

scalaVersion := "2.13.4"

lazy val root = (project in file(".")).settings(
  libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
)