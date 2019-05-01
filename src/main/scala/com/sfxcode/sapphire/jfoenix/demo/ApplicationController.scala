package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.core.controller.AppController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainWindowController
import com.sun.javafx.css.StyleManager
import com.typesafe.config.ConfigFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named


@Named
@ApplicationScoped
class ApplicationController extends AppController {
  val conf = ConfigFactory.load()

  var lastNavigationControllerName = ""


  lazy val mainWindowController = getController[MainWindowController]()

  def applicationDidLaunch() {
    logger.info("start " + this)
    applicationEnvironment.loadResourceBundle("bundles/application")
    replaceSceneContent(mainWindowController)
  }

  def replacePrimarySceneContent(): Unit = {
    StyleManager.getInstance().stylesheetContainerMap.clear()
    val newMainWindowController = getController[MainWindowController]()
    replaceSceneContent(newMainWindowController)
  }


  @Produces
  def applicationName: ApplicationName = {
    ApplicationName(conf.getString("application.name"))
  }
}

case class ApplicationName(name: String)
