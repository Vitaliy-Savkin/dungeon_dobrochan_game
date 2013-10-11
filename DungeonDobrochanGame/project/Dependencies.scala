import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    ScalaToolsSnapshots,
    "Typesafe repo"    at "http://repo.typesafe.com/typesafe/releases/",
    "scala-tools repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
    "spray"            at "http://repo.spray.io/"
  )

  def compile   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
  def provided  (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test      (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
  def runtime   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")
  def container (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  object V {
    val scala     = "2.10.1"
    val akka      = "2.2.0"
    val scalaz    = "7.0.0"
    val specs2    = "2.0"
    val mockito   = "1.9.5"
    val jettyAll  = "9.0.4.v20130625"
    val wcs       = "1.3"
    val lift      = "2.5"
    val liftJson  = "2.5"
    val rogue     = "2.2.0"
    val casbah    = "2.6.2"
    val slf4j     = "1.7.5"
    val jbcrypt   = "0.3m"
    val logback   = "1.0.7"
    val spray     = "1.2.5"
    val dispatch  = "0.11.0"
 	val slick 	  = "2013.10-SNAPSHOT"
  }

  val akka                = "com.typesafe.akka"             %%  "akka-actor"          % V.akka
  val akkaSlf4j           = "com.typesafe.akka"             %%  "akka-slf4j"          % V.akka
  val akkaTestKit         = "com.typesafe.akka"             %%  "akka-testkit"        % V.akka
  val scalaz              = "org.scalaz"                    %%  "scalaz-core"         % V.scalaz
  val swing               = "org.scala-lang"                %   "scala-swing"         % V.scala
  val specs2              = "org.specs2"                    %%  "specs2"              % V.specs2
  val mockito             = "org.mockito"                   %   "mockito-core"        % V.mockito
  val jettyAll            = "org.eclipse.jetty.aggregate"   %   "jetty-all"           % V.jettyAll
  val webSockets          = "org.jfarcand"                  %   "wcs"                 % V.wcs
  val lift                = "net.liftweb"                   %%  "lift-webkit"         % V.lift
  val liftMongodbRecord   = "net.liftweb"                   %%  "lift-mongodb-record" % V.lift
  val liftJson            = "net.liftweb"                   %%  "lift-json"           % V.liftJson
  val rogueField          = "com.foursquare"                %% 	"rogue-field"         % V.rogue intransitive()
  val rogueCore           = "com.foursquare"                %%	"rogue-core"          % V.rogue intransitive()
  val rogueLift           = "com.foursquare"                %% 	"rogue-lift"          % V.rogue intransitive()
  val rogueIndex          = "com.foursquare"                %% 	"rogue-index"         % V.rogue intransitive()
  val sprayJson           = "io.spray"                      %%  "spray-json"          % V.spray
  val casbah              = "org.mongodb"                   %%  "casbah-core"         % V.casbah
  val slf4j               = "org.slf4j"                     %   "slf4j-api"           % V.slf4j
  val jbcrypt             = "org.mindrot"                   %   "jbcrypt"             % V.jbcrypt
  val jline               = "org.scala-lang"                %   "jline"               % V.scala
  val logback             = "ch.qos.logback"                %   "logback-classic"     % V.logback
  val dispatch            = "net.databinder.dispatch"       %%  "dispatch-core"       % V.dispatch
}
