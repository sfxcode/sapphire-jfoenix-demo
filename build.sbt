import ReleaseTransformations._


name := "sapphire-jfoenix-demo"

organization := "com.sfxcode.sapphire"

resolvers += "sfxcode-bintray" at "https://dl.bintray.com/sfxcode/maven"

scalaVersion := "2.13.1"

val JavaFXVersion = "13"

val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

libraryDependencies ++= Seq("base", "controls", "fxml", "graphics", "media", "swing", "web").map(
  m => "org.openjfx" % s"javafx-$m" % JavaFXVersion classifier osName)

libraryDependencies += "org.specs2" %% "specs2-core" % "4.7.1" % Test

// Sapphire

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-core" % "1.6.7"

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-extension" % "1.0.0"

libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.2-R18"


// UI Kits

libraryDependencies += "com.jfoenix" % "jfoenix" % "9.0.9"

libraryDependencies += "eu.hansolo" % "Medusa" % "11.2"

// 

libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.7"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

mainClass := Some("com.sfxcode.sapphire.jfoenix.demo.Application")

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

