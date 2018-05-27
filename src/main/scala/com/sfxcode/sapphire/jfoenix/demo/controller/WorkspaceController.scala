package com.sfxcode.sapphire.jfoenix.demo.controller

import com.sfxcode.sapphire.core.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.ApplicationName
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractTabController
import com.sfxcode.sapphire.jfoenix.demo.controller.tab.{FirstTabController, SecondTabController}
import javafx.fxml.FXML
import javafx.scene.control._
import javafx.scene.layout._
import javax.inject.Inject
import scalafx.Includes._

class WorkspaceController extends AbstractTabController {
  lazy val firstTabController = getController[FirstTabController]()
  lazy val secondTabController = getController[SecondTabController]()

  var firstTabContentManager: ContentManager = _
  var secondTabContentManager: ContentManager = _

  @Inject
  var applicationName: ApplicationName = _

  @FXML var firstTab: StackPane = _
  @FXML var secondTab: StackPane = _

  @FXML var applicationNameLabel:Label = _

  override def didGainVisibilityFirstTime(): Unit = {
    super.didGainVisibilityFirstTime()
    firstTabContentManager =  ContentManager(firstTab, this, firstTabController)
    secondTabContentManager =  ContentManager(secondTab, this, secondTabController)
    applicationNameLabel.setText(applicationName.name)
  }

  def tabPaneHasChanged(oldValue: Tab, newValue: Tab): Unit = {
    statusBarController.setText("Tab selected : " + newValue.getText)
  }
}


