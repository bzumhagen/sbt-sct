sbtPlugin := true

name := "sbt-sct"

organization := "com.github.bzumhagen"

version := "0.1.0"

libraryDependencies ++= Seq(
  "com.github.bzumhagen" %% "sct" % "0.7.0"
)

pgpSecretRing := file("local.secring.asc")

pgpPublicRing := file("local.pubring.asc")

publishMavenStyle := true

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses := Seq("MIT-style" -> url("https://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/bzumhagen"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/bzumhagen/sbt-sct"),
    "scm:git@github.com:bzumhagen/sbt-sct.git"
  )
)

developers := List(
  Developer(
    id    = "bzumhagen",
    name  = "Ben Zumhagen",
    email = "bzumhagen@gmail.com",
    url   = url("https://github.com/bzumhagen")
  )
)