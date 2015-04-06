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
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)

name in Universal := name.value

TwirlKeys.templateImports ++= Seq(
  "domain._",
  "tags._"
)