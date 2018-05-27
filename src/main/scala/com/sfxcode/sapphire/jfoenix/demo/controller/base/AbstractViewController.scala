package com.sfxcode.sapphire.jfoenix.demo.controller.base


import com.sfxcode.sapphire.core.controller.ViewController
import com.sfxcode.sapphire.jfoenix.demo.ApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainWindowController
import com.typesafe.scalalogging.LazyLogging

abstract class AbstractViewController extends ViewController with LazyLogging{

  def applicationController:ApplicationController =   applicationEnvironment.applicationController.asInstanceOf[ApplicationController]

  def mainWindowController: MainWindowController = {
    val mainWindowController = applicationEnvironment.getController[MainWindowController]
    if (mainWindowController.isDefined)
      mainWindowController.get
    else
      null
  }

  def statusBarController = mainWindowController.statusBarController

  def workspaceManager = mainWindowController.workspaceManager

}
