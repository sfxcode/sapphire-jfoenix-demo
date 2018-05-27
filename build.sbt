

name := "sapphire-jfoenix-demo"

organization := "com.sfxcode.sapphire"

resolvers += "sfxcode-bintray" at "https://dl.bintray.com/sfxcode/maven"

scalaVersion := "2.12.6"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.2.0" % "test"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-extension" % "0.7.4"

libraryDependencies += "com.jfoenix" % "jfoenix" % "1.11.1"

libraryDependencies += "eu.hansolo" % "Medusa" % "8.0"


enablePlugins(BuildInfoPlugin)

buildInfoPackage := "com.sfxcode.sapphire.jfoenix.demo"

buildInfoOptions += BuildInfoOption.BuildTime


