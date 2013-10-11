import sbt._
import Keys._
import org.scalastyle.sbt._
import org.scalastyle.sbt.ScalastylePlugin.{Settings => scalastyleSettings}
import sbtassembly.Plugin._
import AssemblyKeys._
import net.virtualvoid.sbt.graph.Plugin._
import com.typesafe.sbt.SbtAtmos.{ Atmos, atmosSettings }

object Build extends sbt.Build {
  import Dependencies._

  lazy val commonSettings = Defaults.defaultSettings ++ scalastyleSettings ++ graphSettings ++ Seq(
    organization  := "ru.dobrochan.dungeon",
    version       := "0.1",
    scalaVersion  := "2.10.1",
    scalacOptions := Seq("-deprecation", "-unchecked", "-encoding", "utf8"),
	unmanagedBase <<= baseDirectory { base => new java.io.File("project\\libs\\slick\\lib")},
	javaOptions in run += "-Djava.library.path=project\\libs\\slick",
    resolvers     ++= Dependencies.resolutionRepos
  )
  
  lazy val coreProject = Project("core", file("./core"), settings = commonSettings)
    .settings(
      libraryDependencies ++= test(specs2)
    )
	
  lazy val contentProject = Project("content", file("./content"), settings = commonSettings)
    .settings(
      libraryDependencies ++= test(specs2)
    )
	
  lazy val uiProject = Project("ui", file("./ui"), settings = commonSettings)
    .dependsOn(contentProject, coreProject)
    .settings(
      libraryDependencies ++= test(specs2)
    )


  lazy val clientProject = Project("client", file("./client"), settings = commonSettings ++ assemblySettings)
    .settings(
      libraryDependencies ++= compile(akka) ++ test(specs2),
      jarName in assembly := "ddg.jar",
      mainClass in assembly := Some("ru.dobrochan.dungeon.client.DungeonDobrochanGame")
    )
    .dependsOn(coreProject, uiProject)
    .configs(Atmos)
    .settings(atmosSettings: _*)
}
