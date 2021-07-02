package io.noobymatze.hello

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.noobymatze.hello.tables.Users.USERS
import io.noobymatze.hello.users.UserRepo
import org.jooq.exception.DataAccessException
import org.jooq.impl.DSL
import org.jooq.{DSLContext, SQLDialect}
import zio.console._
import zio._
import io.noobymatze.hello.db._
import io.noobymatze.hello.tables.records.UsersRecord

import java.io.PrintWriter
import java.util.Properties

class User(var x: String) {

  def setX(bla: String): Unit = {
    this.x = bla
  }
}

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
    users <- UserRepo.select()
    _ <- putStrLn(s"$users")
    newUser <- UserRepo.insert(UsersRecord().run { u =>
      u.setName("Hello")
      u.setEmail("World@test.de")
    })
    users2 <- UserRepo.select()
    _ <- putStrLn(s"$users2")
    users2 <- UserRepo.delete(newUser.getId())
  } yield ()

  def run(args: List[String]) = {
    // The DataSource should probably be a layer as well
    val jooqLayer = ZLayer.fromEffect(Jooq.make(config.database))
    val env = Console.live ++ jooqLayer
    program.provideLayer(env).exitCode
  }

extension [T](x: T)
  def run(init: T => Unit): T = {
    init(x)
    x
  }

