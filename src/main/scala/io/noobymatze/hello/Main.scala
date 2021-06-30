package io.noobymatze.hello

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.noobymatze.hello.tables.Users.USERS
import org.jooq.impl.DSL
import org.jooq.{DSLContext, SQLDialect}
import zio.console._

import java.io.PrintWriter
import java.util.Properties


object Main extends zio.App:

  val config = Config(
    database = DatabaseConfig(
      url = "jdbc:postgresql://localhost:7086/hello",
      user = "noobymatze",
      password = "sql",
      schema = "hello"
    )
  )

  val program = for {
    _ <- putStrLn(s"$config")
  } yield ()

  def run(args: List[String]) =
    program.exitCode


//@main def hello: Unit =
//  val dbConfig = io.noobymatze.hello.DatabaseConfig(
//    url = "jdbc:postgresql://localhost:7086/hello",
//    user = "noobymatze",
//    password = "sql",
//    schema = "hello"
//  )
//
//  val props = new Properties()
//  props.put("dataSource.logWriter", PrintWriter(System.out))
//
//  val conf: HikariConfig = HikariConfig(props)
//  conf.setJdbcUrl(dbConfig.url)
//  conf.setDriverClassName("org.postgresql.Driver")
//  conf.setPassword(dbConfig.password)
//  conf.setUsername(dbConfig.user)
//  conf.setSchema(dbConfig.schema)
//
//  val ds = HikariDataSource(conf)
//
//  val dsl = DSL.using(ds, SQLDialect.POSTGRES)

  //println(result)

