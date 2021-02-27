package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.javafx.application.ApplicationEnvironment
import com.sfxcode.sapphire.javafx.controller.BaseApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainViewController
import com.sun.javafx.css.StyleManager
import com.typesafe.config.ConfigFactory

class ApplicationController extends BaseApplicationController {
  val conf = ConfigFactory.load()

  var lastNavigationControllerName = ""

  lazy val mainViewController = getController[MainViewController]()

  def applicationDidLaunch() {
    logger.info("start " + this)
    ApplicationEnvironment.loadResourceBundle("bundles/application")
    replaceSceneContent(mainViewController)
  }

  def replacePrimarySceneContent(): Unit = {
    StyleManager.getInstance().stylesheetContainerMap.clear()
    val newmainViewController = getController[MainViewController]()
    replaceSceneContent(newmainViewController)
  }

  def applicationName: ApplicationName =
    ApplicationName(conf.getString("application.name"))
}

case class ApplicationName(name: String)
