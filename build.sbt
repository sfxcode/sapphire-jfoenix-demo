import no.vedaadata.sbtjavafx.JavaFXPlugin.JFX

name := "sapphire-jfoenix-demo"

organization := "com.sfxcode.sapphire"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions += "-target:jvm-1.7"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions += "-target:jvm-1.7"

resolvers ++= Seq(
  "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)


mainClass := Some("com.sfxcode.sapphire.jfoenix.demo.Application")

libraryDependencies += "org.specs2" %% "specs2-core" % "3.7" % "test"

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-core" % "1.1.0"

libraryDependencies += "de.jensd" % "fontawesomefx" % "8.9"

libraryDependencies += "org.controlsfx" % "controlsfx" % "8.40.10"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"


jfxSettings

JFX.mainClass := Some("com.sfxcode.sapphire.jfoenix.demo.Application")

JFX.nativeBundles := "none"

