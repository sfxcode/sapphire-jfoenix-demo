package com.sfxcode.sapphire.jfoenix.demo.controller

import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.layout.StackPane

import com.sfxcode.sapphire.core.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractTabController
import com.sfxcode.sapphire.jfoenix.demo.controller.tab.{SecondTabController, FirstTabController}
import scalafx.Includes._

class WorkspaceController extends AbstractTabController {
  lazy val firstTabController = getController[FirstTabController]()
  lazy val secondTabController = getController[SecondTabController]()

  var firstTabContentManager: ContentManager = _
  var secondTabContentManager: ContentManager = _

  @FXML var firstTab: StackPane = _
  @FXML var secondTab: StackPane = _

  override def didGainVisibilityFirstTime(): Unit = {
    super.didGainVisibilityFirstTime()
    firstTabContentManager =  ContentManager(firstTab, this, firstTabController)
    secondTabContentManager =  ContentManager(secondTab, this, secondTabController)
  }

  def tabPaneHasChanged(oldValue: Tab, newValue: Tab): Unit = {
    statusBarController.setText("Tab selected : " + newValue.getText)
  }
}


