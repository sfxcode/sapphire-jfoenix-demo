package com.sfxcode.sapphire.jfoenix.demo.database

import better.files.{File, Resource}
import com.sfxcode.nosql.mongo._
import com.sfxcode.sapphire.jfoenix.demo.database.Database.PersonDAO
import com.sfxcode.sapphire.jfoenix.demo.model.Person
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.BulkWriteResult

object PersonServices extends LazyLogging {

  def initPersonTable(): Unit = {
    // since all database function results are observables - we have to add .result() to wait for the end of current operation
    val importResult: BulkWriteResult =
      PersonDAO.importJsonFile(File(Resource.getUrl("data/person_list.json"))).result()

    // add indexes
    PersonDAO.createUniqueIndexForField("id").result()
    PersonDAO.createIndexForField("name").result()

    logger.info(
      "Person List import acknowledged: %s - %s documents imported".format(
        importResult.wasAcknowledged(),
        PersonDAO.count().result()
      )
    )
  }

  def personNames(): List[String] =
    PersonDAO.distinctResult[String]("name").toList

  def personForName(name: String): Option[Person] =
    PersonDAO.find(Map("name" -> name)).resultOption()

  def personCount(): Long = PersonDAO.count().result()
}
