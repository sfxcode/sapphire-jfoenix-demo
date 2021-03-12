package com.sfxcode.sapphire.jfoenix.demo.controller.page

import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractNavigationController
import com.sfxcode.sapphire.jfoenix.demo.controller.component.jfoenix.JFoenixComponentController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button

class UISamplePageController extends AbstractNavigationController {

  @FXML var jfoenixButton: Button  = _
  @FXML var sapphireButton: Button = _

  lazy val tabController = getController[JFoenixComponentController]()

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    navigationContentManager.updatePaneContent(tabController)
    updateToolbarButtonStyles(jfoenixButton)
  }

  def actionShowTab(event: ActionEvent) {
    toolbarButtonClicked(event, tabController)
  }

}
