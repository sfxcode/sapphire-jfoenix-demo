package com.sfxcode.sapphire.jfoenix.demo.controller.component.jfoenix

import com.sfxcode.sapphire.javafx.application.ApplicationEnvironment
import com.sfxcode.sapphire.javafx.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractTabController
import com.sfxcode.sapphire.jfoenix.demo.{ApplicationController, ApplicationName}
import javafx.fxml.FXML
import javafx.scene.control._
import javafx.scene.layout._

class JFoenixComponentController extends AbstractTabController {
  lazy val firstTabController: FirstTabComponentController   = getController[FirstTabComponentController]()
  lazy val secondTabController: SecondTabComponentController = getController[SecondTabComponentController]()

  var firstTabContentManager: ContentManager  = _
  var secondTabContentManager: ContentManager = _

  var applicationName: ApplicationName =
    ApplicationEnvironment.applicationController[ApplicationController].applicationName

  @FXML var firstTab: StackPane  = _
  @FXML var secondTab: StackPane = _

  @FXML var applicationNameLabel: Label = _

  override def didGainVisibilityFirstTime(): Unit = {
    super.didGainVisibilityFirstTime()
    firstTabContentManager = ContentManager(firstTab, this, firstTabController)
    secondTabContentManager = ContentManager(secondTab, this, secondTabController)
    applicationNameLabel.setText(applicationName.name)
  }

  def tabPaneHasChanged(oldValue: Tab, newValue: Tab): Unit =
    statusBarController.updateStatusBarText("Tab selected : " + newValue.getText)
}
