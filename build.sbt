name := """black"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.apache.spark" %% "spark-core" % "1.3.1",
  "org.apache.spark" %% "spark-mllib"  % "1.3.1",
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
  "org.jsoup" % "jsoup" % "1.8.2",
  "commons-io" % "commons-io" % "2.4"
)
