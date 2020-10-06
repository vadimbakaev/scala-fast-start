import sbt.Keys.parallelExecution

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "scala-fast-start",
    version := "0.1.0",
    scalaVersion := "2.12.11",
    scalafmtOnCompile := true,
    coverageEnabled in(Compile, compile) := false,
    coverageEnabled in(Test, test) := true,
    parallelExecution in(Test, test) := true,
    mainClass in Compile := Some("vbakaev.app.Main"),
    libraryDependencies ++= {
      object Version {
        val scalaTest       = "3.2.2"
        val mockitoScala    = "1.15.0"
        val cats            = "2.1.1"
        val pureConfig      = "0.14.0"
        val scalaLogging    = "3.9.2"
        val logback         = "1.2.3"
        val akkaHttp        = "10.1.12"
        val akka            = "2.6.8"
        val akkaHttpCORS    = "1.1.0"
        val swaggerAkkaHttp = "2.1.1"
        val swaggerScala    = "2.1.1"
        val circe           = "0.13.0"
        val akkaHttpCirce   = "1.33.0"
        val javaxWsRs       = "2.1.1"
        val webJars         = "0.40"
        val swaggerUI       = "3.32.5"
      }

      Seq(
        "org.webjars"                  % "webjars-locator"       % Version.webJars,
        "org.webjars"                  % "swagger-ui"            % Version.swaggerUI,
        "javax.ws.rs"                  % "javax.ws.rs-api"       % Version.javaxWsRs artifacts(Artifact("javax.ws.rs-api", "jar", "jar")),
        "de.heikoseeberger"            %% "akka-http-circe"      % Version.akkaHttpCirce,
        "io.circe"                     %% "circe-core"           % Version.circe,
        "io.circe"                     %% "circe-generic"        % Version.circe,
        "io.circe"                     %% "circe-parser"         % Version.circe,
        "com.github.swagger-akka-http" %% "swagger-akka-http"    % Version.swaggerAkkaHttp,
        "com.github.swagger-akka-http" %% "swagger-scala-module" % Version.swaggerScala,
        "ch.megard"                    %% "akka-http-cors"       % Version.akkaHttpCORS,
        "com.typesafe.akka"            %% "akka-http"            % Version.akkaHttp,
        "com.typesafe.akka"            %% "akka-stream"          % Version.akka,
        "com.typesafe.akka"            %% "akka-slf4j"           % Version.akka,
        "org.typelevel"                %% "cats-core"            % Version.cats,
        "com.github.pureconfig"        %% "pureconfig"           % Version.pureConfig,
        "com.typesafe.scala-logging"   %% "scala-logging"        % Version.scalaLogging,
        "ch.qos.logback"               % "logback-classic"       % Version.logback,
        "com.typesafe.akka"            %% "akka-stream-testkit"  % Version.akka % Test,
        "com.typesafe.akka"            %% "akka-http-testkit"    % Version.akkaHttp % Test,
        "org.scalatest"                %% "scalatest"            % Version.scalaTest % Test,
        "org.mockito"                  %% "mockito-scala"        % Version.mockitoScala % Test,
      )
    }
  )

scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-Ypartial-unification",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Xfatal-warnings",
  "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
  "-Ywarn-unused:locals", // Warn if a local definition is unused.
  "-Ywarn-unused:params", // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates", // Warn if a private member is unused.
  "-Ywarn-value-discard" // Warn when non-Unit expression results are unused.
)
