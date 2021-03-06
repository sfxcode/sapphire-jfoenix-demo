package com.sfxcode.sapphire.jfoenix.demo

import com.jfoenix.assets.JFoenixResources
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
    replacePrimarySceneContent()
  }

  def replacePrimarySceneContent(): Unit = {
    ApplicationEnvironment.clearResourceBundleCache()
    ApplicationEnvironment.loadResourceBundle("bundles/application")

    StyleManager.getInstance().stylesheetContainerMap.clear()
    replaceSceneContent(mainViewController)

    val stylesheets = mainViewController.scene.getStylesheets
    stylesheets.clear()
    val jfoenixFontsCSS: String  = JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm
    val jfoenixDesignCSS: String = JFoenixResources.load("css/jfoenix-design.css").toExternalForm
    val jfoenixMainCSS: String   = getClass.getResource("/css/jfoenix-main.css").toExternalForm
    val mainCSS: String          = getClass.getResource("/css/main.css").toExternalForm
    stylesheets.addAll(jfoenixFontsCSS, jfoenixDesignCSS, jfoenixMainCSS, mainCSS)

    logger.error("" + StyleManager.getInstance().stylesheetContainerMap)
  }

  def applicationName: ApplicationName =
    ApplicationName(conf.getString("application.name"))
}

case class ApplicationName(name: String)
