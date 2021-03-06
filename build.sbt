import ReleaseTransformations._

name := "sapphire-jfoenix-demo"

organization := "com.sfxcode.sapphire"

resolvers += "sfxcode-bintray" at "https://dl.bintray.com/sfxcode/maven"

scalaVersion := "2.13.5"

val JavaFXVersion = "15.0.1"

val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "mac"
  case n if n.startsWith("Windows") => "win"
  case _                            => throw new Exception("Unknown platform!")
}

libraryDependencies ++= Seq("base", "controls", "fxml", "graphics", "media", "swing", "web").map(m =>
  "org.openjfx" % s"javafx-$m" % JavaFXVersion classifier osName
)

// specs
libraryDependencies += "org.specs2" %% "specs2-core" % "4.10.6" % Test

// Sapphire
libraryDependencies += "com.sfxcode.sapphire" %% "sapphire-javafx" % "1.0.3"

// scalafx
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R21"

// UI Kits

libraryDependencies += "com.jfoenix" % "jfoenix" % "9.0.10"

libraryDependencies += "eu.hansolo" % "Medusa" % "11.5"

// Persistance (MongoDB) / DAO

libraryDependencies += "com.sfxcode.nosql" %% "simple-mongo" % "2.2.0"

// local java mongodb server - can be used in memory or file based - optional if a real mongodb database is available
val MongoJavaServerVersion = "1.37.0"

libraryDependencies += "de.bwaldvogel" % "mongo-java-server" % MongoJavaServerVersion

libraryDependencies += "de.bwaldvogel" % "mongo-java-server-h2-backend" % MongoJavaServerVersion

// logging

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

mainClass := Some("com.sfxcode.sapphire.jfoenix.demo.Application")

// BuildInfo Configuration
enablePlugins(BuildInfoPlugin)

buildInfoPackage := "com.sfxcode.sapphire.jfoenix.demo"

buildInfoOptions += BuildInfoOption.BuildTime

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies, // : ReleaseStep
  inquireVersions,           // : ReleaseStep
  runClean,                  // : ReleaseStep
  runTest,                   // : ReleaseStep
  setReleaseVersion,         // : ReleaseStep
  commitReleaseVersion,      // : ReleaseStep, performs the initial git checks
  tagRelease,                // : ReleaseStep
  //publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
  setNextVersion,    // : ReleaseStep
  commitNextVersion, // : ReleaseStep
  pushChanges        // : ReleaseStep, also checks that an upstream branch is properly configured
)
