package com.sfxcode.sapphire.jfoenix.demo

import better.files.{File, Resource}
import com.sfxcode.nosql.mongo._
import com.sfxcode.nosql.mongo.server.LocalServer
import com.sfxcode.sapphire.javafx.application.BaseApplication
import com.sfxcode.sapphire.javafx.controller.BaseApplicationController
import com.sfxcode.sapphire.javafx.{BuildInfo, ConfigValues}
import com.sfxcode.sapphire.jfoenix.demo.database.Database.PersonDAO
import com.sfxcode.sapphire.jfoenix.demo.sevices.PersonServices
import org.mongodb.scala.BulkWriteResult

object Application extends BaseApplication with ConfigValues {
  var localMongoDBServer: LocalServer = _

  override def title: String = "%s (%s)".format(configStringValue("project.name"), BuildInfo.version)

  override def height: Int = 800

  override def width: Int = 1000

  override val applicationController: BaseApplicationController = new ApplicationController

  override def applicationWillLaunch(): Unit = {
    super.applicationWillLaunch()
    localMongoDBServer = LocalServer.fromPath("local.mongo.server")
    PersonServices.initPersonTable()
  }

  override def applicationWillTerminate(): Unit = {
    localMongoDBServer.shutdown()
    super.applicationWillTerminate()
  }
}
