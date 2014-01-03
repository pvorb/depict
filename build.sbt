name := "depict"

organization := "de.vorb"

version := "0.0.1"

scalaVersion := "2.10.3"


// Dependencies
resolvers += "Sonatype" at "http://oss.sonatype.org/content/groups/public/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
