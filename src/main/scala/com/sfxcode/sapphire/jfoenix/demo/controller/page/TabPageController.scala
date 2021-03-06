package com.sfxcode.sapphire.jfoenix.demo.controller.page

import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractNavigationController
import com.sfxcode.sapphire.jfoenix.demo.controller.component.tab.TabViewComponentController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button

class TabPageController extends AbstractNavigationController {

  @FXML var tabButton: Button    = _
  @FXML var secondButton: Button = _

  lazy val tabController = getController[TabViewComponentController]()

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    navigationContentManager.updatePaneContent(tabController)
    updateToolbarButtonStyles(tabButton)
  }

  def actionShowTab(event: ActionEvent) {
    toolbarButtonClicked(event, tabController)
  }

}
