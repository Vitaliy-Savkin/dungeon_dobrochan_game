import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._


object Build extends sbt.Build {
  import Dependencies._

  lazy val commonSettings = Defaults.defaultSettings ++ Seq(
    organization  := "ru.dobrochan.dungeon",
    version       := "0.1",
    scalaVersion  := "2.11.2",
    scalacOptions := Seq("-deprecation", "-unchecked", "-encoding", "utf8"),
	unmanagedBase <<= baseDirectory { base => new java.io.File("project\\libs\\slick\\lib")},
	javaOptions in run += "-Djava.library.path=project\\libs\\slick",
    resolvers     ++= Dependencies.resolutionRepos
  )
  
  lazy val coreProject = Project("core", file("./core"), settings = commonSettings)
    .settings(
      libraryDependencies ++= test(scalaTest)
    )
	
  lazy val contentProject = Project("content", file("./content"), settings = commonSettings)
    .settings(
      libraryDependencies ++= test(scalaTest)
    )
	
  lazy val uiProject = Project("ui", file("./ui"), settings = commonSettings)
    .dependsOn(contentProject, coreProject)
    .settings(
      libraryDependencies ++= test(scalaTest)
    )


  lazy val clientProject = Project("client", file("./client"), settings = commonSettings ++ assemblySettings)
    .settings(
      libraryDependencies ++= compile(akka) ++ test(scalaTest),
      jarName in assembly := "ddg.jar",
      mainClass in assembly := Some("ru.dobrochan.dungeon.client.DungeonDobrochanGame")
    )
    .dependsOn(coreProject, uiProject)
}
