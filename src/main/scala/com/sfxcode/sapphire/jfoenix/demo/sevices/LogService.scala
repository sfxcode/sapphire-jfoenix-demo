package com.sfxcode.sapphire.jfoenix.demo.sevices

import com.sfxcode.nosql.mongo._
import com.sfxcode.sapphire.jfoenix.demo.database.Database.LogDAO
import com.sfxcode.sapphire.jfoenix.demo.model.LogEntry
import org.mongodb.scala.result.InsertOneResult

object LogService {
  val TopicDatabase = "Database"

  def initLogCollection(): Unit = {
    LogDAO.createIndexForField("topic").result()
    addLogEntry("initLogCollection", TopicDatabase)
  }

  def logEntries(): List[LogEntry] =
    LogDAO.find(sort = Sort.sortByKey("date", sortAscending = false)).resultList()

  def logEntriesByTopic(topic: String): List[LogEntry] =
    LogDAO.find(Map("topic" -> topic)).resultList()

  def addLogEntry(message: String, topic: String = "Application", severity: String = "info"): InsertOneResult =
    LogDAO.insertOne(LogEntry(message, topic, severity)).result()
}
