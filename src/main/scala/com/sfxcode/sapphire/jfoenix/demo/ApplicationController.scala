package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.core.controller.AppController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainWindowController
import com.typesafe.config.ConfigFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named


@Named
@ApplicationScoped
class ApplicationController extends AppController {
  val conf = ConfigFactory.load()

  lazy val mainWindowController = getController[MainWindowController]()

  def applicationDidLaunch() {
    logger.info("start " + this)
    applicationEnvironment.loadResourceBundle("bundles/application")
    replaceSceneContent(mainWindowController)
  }

  def replacePrimarySceneContent(): Unit = {
    val newMainWindowController = getController[MainWindowController]()
    replaceSceneContent(newMainWindowController)
  }


  @Produces
  def applicationName: ApplicationName = {
    ApplicationName(conf.getString("application.name"))
  }
}

case class ApplicationName(name: String)
