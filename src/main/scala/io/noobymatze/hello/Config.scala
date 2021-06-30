package io.noobymatze.hello

case class Config(val database: DatabaseConfig)

case class DatabaseConfig(val url: String, val user: String, val password: String, val schema: String)