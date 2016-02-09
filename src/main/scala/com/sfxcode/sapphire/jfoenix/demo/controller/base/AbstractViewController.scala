package com.sfxcode.sapphire.jfoenix.demo.controller.base


import com.sfxcode.sapphire.core.controller.ViewController
import com.sfxcode.sapphire.core.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.controller.MainWindowController
import com.sfxcode.sapphire.jfoenix.demo.controller.tab.{SecondTabController, FirstTabController}
import com.typesafe.scalalogging.LazyLogging

abstract class AbstractViewController extends ViewController with LazyLogging{

  def mainWindowController:MainWindowController = {
    parent.asInstanceOf[MainWindowController]
  }

  def statusBarController = mainWindowController.statusBarController

  def workspaceManager = mainWindowController.workspaceManager

  def dialogController = mainWindowController.dialogController

  override def willLooseVisibility(): Unit = {
    dialogController.closeDialog()
  }


}
