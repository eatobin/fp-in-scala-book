ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "fp-in-scala",
    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.14.0"
    )
  )
