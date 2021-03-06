package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.sfxcode.sapphire.javafx.application.ApplicationEnvironment
import com.sfxcode.sapphire.javafx.controller.ViewController
import com.sfxcode.sapphire.javafx.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.ApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.{MainViewController, StatusBarController}
import com.typesafe.scalalogging.LazyLogging

abstract class AbstractViewController extends ViewController with LazyLogging {

  def applicationController: ApplicationController = ApplicationEnvironment.applicationController[ApplicationController]

  def mainViewController: MainViewController = applicationController.mainViewController

  def statusBarController: StatusBarController = mainViewController.statusBarController

  def workspaceManager: ContentManager = mainViewController.workspaceManager

}
