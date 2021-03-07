package com.sfxcode.sapphire.jfoenix.demo.model

import org.bson.types.ObjectId

import java.util.Date

case class LogEntry(message: String, topic: String, date: Date = new Date(), _id: ObjectId = new ObjectId())
