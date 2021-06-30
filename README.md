## Hello

This project is a simple playground for [ZIO][zio], [JOOQ][jooq] and [Scala 3][scala].

### Usage

```sh
$ docker-compose up -d
$ sbt
sbt:hello> flywayMigrate
sbt:hello> jooqCodegen
sbt:hello> run
sbt:hello> assembly # for a final artifact
$ java -jar target/scala-3.0.0/hello-assembly-0.1.0.jar
```

[zio]: https://zio.dev/
[jooq]: https://www.jooq.org/
[scala]: https://dotty.epfl.ch/