package com.sfxcode.sapphire.jfoenix.demo.controller.view.tab

import com.sfxcode.sapphire.core.application.ApplicationEnvironment
import com.sfxcode.sapphire.core.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.{ApplicationController, ApplicationName}
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractTabController
import javafx.fxml.FXML
import javafx.scene.control._
import javafx.scene.layout._

class ViewTabController extends AbstractTabController {
  lazy val firstTabController: FirstTabController   = getController[FirstTabController]()
  lazy val secondTabController: SecondTabController = getController[SecondTabController]()

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
    statusBarController.setText("Tab selected : " + newValue.getText)
}
