package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.sfxcode.sapphire.javafx.application.SFXApplicationEnvironment
import com.sfxcode.sapphire.javafx.controller.SFXViewController
import com.sfxcode.sapphire.javafx.scene.SFXContentManager
import com.sfxcode.sapphire.jfoenix.demo.ApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.app.{MainController, StatusBarController}

trait BaseController extends SFXViewController {

  def applicationController: ApplicationController =
    SFXApplicationEnvironment.applicationController[ApplicationController]

  def mainViewController: MainController = applicationController.mainViewController

  def statusBarController: StatusBarController = mainViewController.statusBarController

  def workspaceManager: SFXContentManager = mainViewController.workspaceManager

}
