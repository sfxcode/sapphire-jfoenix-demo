package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.sfxcode.sapphire.javafx.application.ApplicationEnvironment
import com.sfxcode.sapphire.javafx.controller.ViewController
import com.sfxcode.sapphire.jfoenix.demo.ApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.MainViewController
import com.typesafe.scalalogging.LazyLogging

abstract class AbstractViewController extends ViewController with LazyLogging {

  def applicationController: ApplicationController = ApplicationEnvironment.applicationController[ApplicationController]

  def mainViewController: MainViewController = applicationController.mainViewController

  def statusBarController = mainViewController.statusBarController

  def workspaceManager = mainViewController.workspaceManager

}
