lazy val root = (project in file("."))
  .settings(
    name := "???",
    version := "0.1",
    scalaVersion := "2.12.8",
    libraryDependencies ++= {

      object Version {
        val scalaTest    = "3.0.5"
        val scalaFmt     = "1.5.1"
        val cats         = "1.5.0"
        val pureConfig   = "0.10.1"
        val scalaLogging = "3.9.2"
        val logback      = "1.2.3"
      }

      Seq(
        "org.scalatest"              %% "scalatest"      % Version.scalaTest % Test,
        "com.geirsson"               %% "scalafmt-core"  % Version.scalaFmt,
        "org.typelevel"              %% "cats-core"      % Version.cats,
        "com.github.pureconfig"      %% "pureconfig"     % Version.pureConfig,
        "com.typesafe.scala-logging" %% "scala-logging"  % Version.scalaLogging,
        "ch.qos.logback"             % "logback-classic" % Version.logback
      )
    }
  )

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-Ypartial-unification",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-unused-import",
  "-Xfatal-warnings"
)

coverageEnabled := true
scalafmtOnCompile := true
parallelExecution in Test := true
