package com.sfxcode.sapphire.jfoenix.demo

import com.jfoenix.assets.JFoenixResources
import com.sfxcode.sapphire.javafx.ConfigValues
import com.sfxcode.sapphire.javafx.application.ApplicationEnvironment
import com.sfxcode.sapphire.javafx.controller.BaseApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainViewController
import com.sun.javafx.css.StyleManager
import javafx.event.ActionEvent

class ApplicationController extends BaseApplicationController with ConfigValues {

  var lastNavigationControllerName = ""

  var mainViewController: MainViewController = _

  def applicationDidLaunch() {
    logger.info("init " + this)
    reload()
  }

  def actionReload(event: ActionEvent): Unit = {
    logger.info("reload event called by %s".format(event.getSource))
    reload()
  }

  private def reload(): Unit = {
    // reset bundles
    ApplicationEnvironment.clearResourceBundleCache()
    ApplicationEnvironment.loadResourceBundle("bundles/application")

    // replace scene content with new MainViewController instance
    StyleManager.getInstance().stylesheetContainerMap.clear()
    mainViewController = getController[MainViewController]()
    replaceSceneContent(mainViewController)

    // relod jfoenix helper css
    val stylesheets = mainViewController.scene.getStylesheets
    stylesheets.clear()
    val jfoenixFontsCSS: String  = JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm
    val jfoenixDesignCSS: String = JFoenixResources.load("css/jfoenix-design.css").toExternalForm
    stylesheets.addAll(jfoenixFontsCSS, jfoenixDesignCSS)

    logger.info("reload finished")

  }

  def applicationName: ApplicationName =
    ApplicationName(configStringValue("application.name"))
}

case class ApplicationName(name: String)
