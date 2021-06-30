val scala3Version = "3.0.0"

lazy val root = project
  .in(file("."))
  .enablePlugins(FlywayPlugin, JooqCodegenPlugin)
  .settings(
    name := "hello",
    version := "0.1.0",
    scalaVersion := scala3Version,
    flywayUrl := "jdbc:postgresql://localhost:7086/hello",
    flywayUser := "noobymatze",
    flywayPassword := "sql",
    flywaySchemas += "hello",
    jooqCodegenConfig := file("jooq-codegen.xml"),

    libraryDependencies += "org.postgresql" % "postgresql" % "42.2.22",
    libraryDependencies += "com.zaxxer" % "HikariCP" % "4.0.3",
    libraryDependencies += "dev.zio" %% "zio" % "1.0.9",
    libraryDependencies += "org.postgresql" % "postgresql" % "42.2.22" % JooqCodegen
)
