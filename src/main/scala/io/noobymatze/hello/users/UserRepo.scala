package io.noobymatze.hello.users

import io.noobymatze.hello.Tables
import io.noobymatze.hello.db._
import io.noobymatze.hello.tables.records.UsersRecord
import org.jooq.exception.DataAccessException
import zio._
import scala.jdk.CollectionConverters._


object UserRepo:

  /**
   *
   * @return
   */
  def select(): JIO[List[UsersRecord]] =
    Jooq.query(_.selectFrom(Tables.USERS).fetch().asScala.toList)

  def insert(record: UsersRecord): JIO[UsersRecord] =
    Jooq.query(_.insertInto(Tables.USERS).set(record).returning().fetchOne())

  // This operation is blocking, so we should mark it and wrap it as such.
  def delete(id: Long): JIO[Int] =
    Jooq.query(_.deleteFrom(Tables.USERS).where(Tables.USERS.ID.eq(id)).execute())
