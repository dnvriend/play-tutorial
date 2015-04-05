name := "play-tutorial"

organization := "com.github.dnvriend"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies ++= Seq(
  "io.spray"       %% "spray-json" % "1.3.1"
)

name in Universal := name.value