organization := "net.horowic"

name := "todo"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.0",
  "postgresql" % "postgresql" % "8.4-701.jdbc4",
  "org.xerial" % "sqlite-jdbc" % "3.6.20",
  "io.spray" % "spray-can" % "1.2-M8",
  "io.spray" % "spray-routing" % "1.2-M8",
  "io.spray" %% "spray-json" % "1.2.5",
  "com.typesafe.akka" %% "akka-actor" % "2.2.0-RC1",
  "org.slf4j" % "slf4j-api" % "1.7.1"
)