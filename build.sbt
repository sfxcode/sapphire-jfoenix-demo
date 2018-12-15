import ReleaseTransformations._


name := "sapphire-jfoenix-demo"

organization := "com.sfxcode.sapphire"

resolvers += "sfxcode-bintray" at "https://dl.bintray.com/sfxcode/maven"

scalaVersion := "2.12.8"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.3.5" % Test

// Sapphire

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-core" % "1.4.3"

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-extension" % "0.8.3.1"

// UI Kits

libraryDependencies += "com.jfoenix" % "jfoenix" % "9.0.8"

libraryDependencies += "eu.hansolo" % "Medusa" % "8.0"

// 

libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.2" 

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"


enablePlugins(BuildInfoPlugin)

buildInfoPackage := "com.sfxcode.sapphire.jfoenix.demo"

buildInfoOptions += BuildInfoOption.BuildTime

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runClean,                               // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  tagRelease,                             // : ReleaseStep
  //publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)

