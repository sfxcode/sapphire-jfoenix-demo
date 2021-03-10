package com.sfxcode.sapphire.jfoenix.demo.sevices

import com.sfxcode.nosql.mongo._
import com.sfxcode.nosql.mongo.bson.DocumentHelper
import com.sfxcode.sapphire.jfoenix.demo.database.Database.PersonDAO
import com.sfxcode.sapphire.jfoenix.demo.model.Person
import com.sfxcode.sapphire.jfoenix.demo.sevices.LogService.addLogEntry
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.result.{DeleteResult, UpdateResult}
import org.mongodb.scala.{BulkWriteResult, Document, SingleObservable}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object PersonServices extends LazyLogging {

  def initPersonCollection(): Unit = {
    // since all database function results are observables - we have to add .result() to wait for the end of current operation
    val importResult: BulkWriteResult =
      importJsonFile().result()

    // add indexes
    PersonDAO.createUniqueIndexForField("id").result()
    PersonDAO.createIndexForField("name").result()

    logger.info(
      "Person List import acknowledged: %s - %s documents imported".format(
        importResult.wasAcknowledged(),
        PersonDAO.count().result()
      )
    )

    addLogEntry("initPersonCollection", "Database")

  }

  def personNames(): List[String] =
    PersonDAO.distinctResult[String]("name").toList

  def personForName(name: String): Option[Person] =
    PersonDAO.find(Map("name" -> name)).resultOption()

  def personAll(): List[Person] =
    PersonDAO.find().resultList()

  def personCount(): Long = PersonDAO.count().result()

  def updatePerson(person: Person): UpdateResult =
    PersonDAO.replaceOne(person).result()

  def deletePerson(person: Person): DeleteResult =
    PersonDAO.deleteOne(person).result()

  def importJsonFile(): SingleObservable[BulkWriteResult] = {
    val docs  = new ArrayBuffer[Document]()
    val lines = Source.fromResource("data/person_list.json").getLines
    lines.foreach(line => docs.+=(DocumentHelper.documentFromJsonString(line).get))
    PersonDAO.Raw.bulkWriteMany(docs.toSeq)
  }
}
