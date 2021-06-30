package io.noobymatze.hello.db

import org.jooq.DSLContext
import org.jooq.exception.DataAccessException
import zio._


object jooq:

  type JIO[A] = ZIO[Jooq, DataAccessException, A]

  type Jooq = Has[Service]

  def query[A](f: DSLContext => A): JIO[A] = ZIO.serviceWith { jooq =>
    ZIO.effect(f(jooq.dsl)).refineOrDie { case ex: DataAccessException => ex }
  }

  trait Service:

    val dsl: DSLContext

