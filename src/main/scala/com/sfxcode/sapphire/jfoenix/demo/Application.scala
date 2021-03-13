package com.sfxcode.sapphire.jfoenix.demo

import better.files.{File, Resource}
import com.jfoenix.controls.JFXDecorator
import com.sfxcode.nosql.mongo._
import com.sfxcode.nosql.mongo.server.LocalServer
import com.sfxcode.sapphire.data.Configuration
import com.sfxcode.sapphire.javafx.application.SFXBaseApplication
import com.sfxcode.sapphire.javafx.controller.SFXBaseApplicationController
import com.sfxcode.sapphire.javafx.{BuildInfo}
import com.sfxcode.sapphire.jfoenix.demo.database.Database.PersonDAO
import com.sfxcode.sapphire.jfoenix.demo.sevices.{LogService, PersonServices}
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage
import org.mongodb.scala.BulkWriteResult

object Application extends SFXBaseApplication with Configuration {
  var localMongoDBServer: LocalServer = _

  override def title: String = "%s (%s)".format(configStringValue("project.name"), BuildInfo.version)

  override def height: Int = 800

  override def width: Int = 1000

  override val applicationController: SFXBaseApplicationController = new ApplicationController

  override def applicationWillLaunch(): Unit = {
    super.applicationWillLaunch()
    localMongoDBServer = LocalServer.fromPath("local.mongo.server")
    LogService.addLogEntry("init database", LogService.TopicDatabase)

    PersonServices.initPersonCollection()
    LogService.initLogCollection()
    LogService.addLogEntry("applicationWillLaunch")
  }

  override def applicationWillTerminate(): Unit = {
    localMongoDBServer.shutdown()
    super.applicationWillTerminate()
  }
}
