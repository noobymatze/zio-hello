package io.noobymatze.hello.db

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import org.jooq.{DSLContext, SQLDialect}
import org.jooq.impl.DSL
import org.jooq.exception.DataAccessException
import io.noobymatze.hello.DatabaseConfig
import zio._

import java.io.PrintWriter
import java.util.Properties
import javax.sql.DataSource

/**
 * A type alias for actions, that use Jooq.
 *
 * @tparam A the type of the resulting value
 */
type JIO[A] = ZIO[Has[Jooq], DataAccessException, A]

/**
 *
 */
trait Jooq:

  /**
   *
   */
  val dsl: DSLContext


object Jooq:

  /**
   *
   * @param config
   * @return
   */
  def make(config: DatabaseConfig): Task[Jooq] = ZIO.effect {
    val props = new Properties()
    props.put("dataSource.logWriter", PrintWriter(System.out))

    val conf: HikariConfig = HikariConfig(props)
    conf.setJdbcUrl(config.url)
    conf.setDriverClassName("org.postgresql.Driver")
    conf.setPassword(config.password)
    conf.setUsername(config.user)
    conf.setSchema(config.schema)

    val ds = HikariDataSource(conf)

    val ctx = DSL.using(ds, SQLDialect.POSTGRES)
    new Jooq {
      override val dsl: DSLContext = ctx
    }
  }

  /**
   * Query the database by creating a JOOQ SQL query.
   *
   * @param runQuery
   * @tparam A the type of the resulting value
   * @return
   */
  def query[A](runQuery: DSLContext => A): JIO[A] = ZIO.serviceWith { jooq =>
    ZIO.effect(runQuery(jooq.dsl)).refineOrDie { case ex: DataAccessException => ex }
  }
