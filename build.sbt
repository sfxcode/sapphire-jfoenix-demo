import ReleaseTransformations._


name := "sapphire-jfoenix-demo"

organization := "com.sfxcode.sapphire"

resolvers += "sfxcode-bintray" at "https://dl.bintray.com/sfxcode/maven"

scalaVersion := "2.12.9"

val JavaFXVersion = "12.0.2"

val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

libraryDependencies ++= Seq("base", "controls", "fxml", "graphics", "media", "swing", "web").map(
  m => "org.openjfx" % s"javafx-$m" % JavaFXVersion classifier osName)

libraryDependencies += "org.specs2" %% "specs2-core" % "4.7.0" % Test

// Sapphire

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-core" % "1.6.4"

libraryDependencies +=   "com.sfxcode.sapphire" %% "sapphire-extension" % "0.9.2"

libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.1-R17"


// UI Kits

libraryDependencies += "com.jfoenix" % "jfoenix" % "9.0.9"

libraryDependencies += "eu.hansolo" % "Medusa" % "11.1"

// 

libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.7"

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

