name := "play-with-spring"
version := "1.0-SNAPSHOT"

lazy val playWithSpring = (project in file("."))
  .enablePlugins(PlayJava)

scalaVersion := "2.11.12"


val SpringVersion = "4.3.11.RELEASE"

libraryDependencies ++= Seq(
  javaJdbc,
  "com.google.guava" % "guava" % "27.0-jre",
  "com.vm.play.common.util" %% "common-play-utils" % "1.0.1",
  javaWs,
  "com.lightbend.play" %% "play-spring-loader" % "0.0.2",
  "mysql" % "mysql-connector-java" % "5.1.44",
  "org.springframework" % "spring-core" % SpringVersion,
  "org.springframework" % "spring-context" % SpringVersion,
  "org.springframework" % "spring-jdbc" % SpringVersion,
  "org.springframework" % "spring-aop" % SpringVersion,
  "org.apache.commons" % "commons-collections4" % "4.2",
  "org.aspectj" % "aspectjrt" % "1.6.11",
  "org.aspectj" % "aspectjweaver" % "1.6.11",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.11",
  "joda-time" % "joda-time" % "2.9.9",
  "javax.el" % "javax.el-api" % "2.2.4",
  "org.glassfish.web" % "javax.el" % "2.2.4",
  "com.fasterxml.jackson.module" % "jackson-module-afterburner" % "2.8.10",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.8.10",
  "org.modelmapper" % "modelmapper" % "2.2.0",
  "org.projectlombok" % "lombok" % "1.16.18",
  "org.apache.httpcomponents" % "httpclient" % "4.5.3",
  ("oauth.signpost" % "signpost-commonshttp4" % "1.2.1.2")
    .exclude("org.apache.httpcomponents", "httpclient"),
  "io.swagger" %% "swagger-play2" % "1.6.0",
  "net.spy" % "spymemcached" % "2.12.3",
  "com.esotericsoftware" % "kryo" % "4.0.1",
  "org.jdbi" % "jdbi3-core" % "3.1.0"
)

