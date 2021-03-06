package com.sfxcode.sapphire.jfoenix.demo.database

import com.sfxcode.nosql.mongo.MongoDAO
import com.sfxcode.nosql.mongo.database.DatabaseProvider
import com.sfxcode.sapphire.jfoenix.demo.model.{Friend, Person}
import com.typesafe.scalalogging.LazyLogging
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.codecs.Macros._

object Database extends LazyLogging {

  private val registry: CodecRegistry = fromProviders(classOf[Person], classOf[Friend])

  // create provider
  val provider: DatabaseProvider = DatabaseProvider.fromPath(configPath = "local.mongo.client", registry = registry)

  object PersonDAO extends MongoDAO[Person](provider, "person")

}
