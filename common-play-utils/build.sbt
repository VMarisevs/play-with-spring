
name := """common-play-utils"""

organization := "com.vm.play.common.util"

version := "1.0.1"

lazy val root = (project in file("."))

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "io.kamon" %% "kamon-logback" % "1.0.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.4"

)
