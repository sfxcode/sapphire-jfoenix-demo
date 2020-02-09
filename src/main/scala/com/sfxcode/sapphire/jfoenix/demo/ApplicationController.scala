package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.core.controller.DefaultWindowController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainViewController
import com.sun.javafx.css.StyleManager
import com.typesafe.config.ConfigFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named


@Named
@ApplicationScoped
class ApplicationController extends DefaultWindowController {
  val conf = ConfigFactory.load()

  var lastNavigationControllerName = ""


  lazy val mainViewController = getController[MainViewController]()

  def applicationDidLaunch() {
    logger.info("start " + this)
    applicationEnvironment.loadResourceBundle("bundles/application")
    replaceSceneContent(mainViewController)
  }

  def replacePrimarySceneContent(): Unit = {
    StyleManager.getInstance().stylesheetContainerMap.clear()
    val newmainViewController = getController[MainViewController]()
    replaceSceneContent(newmainViewController)
  }


  @Produces
  def applicationName: ApplicationName = {
    ApplicationName(conf.getString("application.name"))
  }
}

case class ApplicationName(name: String)
