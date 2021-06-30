package io.noobymatze.hello.users

import io.noobymatze.hello.Tables
import io.noobymatze.hello.db._
import io.noobymatze.hello.db.jooq._
import io.noobymatze.hello.tables.records.UsersRecord
import org.jooq.exception.DataAccessException
import zio._
import scala.jdk.CollectionConverters._


trait UserRepo {

  def select(id: Long): JIO[List[UsersRecord]] =
    jooq.query(_.selectFrom(Tables.USERS).fetch().asScala.toList)

}
