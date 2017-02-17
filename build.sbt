/*
 * Project metadata
 */
name := "echo"
version := "0.1.0"
description := "Grpc example using finagle"
organization := "com.github.teodorpripoae"
organizationHomepage := Some(url("https://github.com/teodor-pripoae/finagle-grpc-example"))

/*
 * Compiler
 */
scalaVersion := "2.11.8"
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
fork in run := true
fork in Test := true
cancelable in Global := true
test in assembly := {}
mainClass in Compile := Some("com.github.teodorpripoae.echo.Application")

lazy val versions = new {
  val grpc      = "1.0.2"
  val logback   = "1.1.7"
  val scalatest = "3.0.0"
  val scalapb   = "0.5.47"
  val linkderd  = "0.9.0-SNAPSHOT"
  val finagle = "6.41.0"
}

// dependencyOverrides += "com.twitter" %% "finagle-netty4"      % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-netty4-http" % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle"             % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-core"        % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-base-http"   % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-stats"       % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-toggle"      % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-thrift"      % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-exp"         % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-zipkin-core" % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-http"        % versions.finagle
// dependencyOverrides += "com.twitter" %% "finagle-mux"         % versions.finagle

/*
 * Dependencies
 */
libraryDependencies ++= Seq(
  // Configuration
  "com.typesafe" % "config" % "1.3.1",
  // Logging
  "ch.qos.logback" % "logback-classic" % versions.logback,
  // Testing
  "org.scalatest" %% "scalatest" % versions.scalatest % "test"
)

// gRPC and Protocol Buffers
libraryDependencies ++= Seq(
  "io.buoyant" %% "grpc-runtime"                   % versions.linkderd,
  "io.buoyant" %% "finagle-h2"                     % versions.linkderd,
  "io.netty"   % "netty-tcnative-boringssl-static" % "1.1.33.Fork19", // SSL support
  "javassist"  % "javassist"                       % "3.12.1.GA" // Improves Netty performance
)

assemblyMergeStrategy in assembly := {
  case s if s.endsWith(".properties")      => MergeStrategy.filterDistinctLines
  case s if s.endsWith("application.conf") => MergeStrategy.concat
  case s if s.endsWith("pom.xml")          => MergeStrategy.last
  case s if s.endsWith("BUILD")            => MergeStrategy.discard
  case PathList("google", "protobuf", _ *) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
