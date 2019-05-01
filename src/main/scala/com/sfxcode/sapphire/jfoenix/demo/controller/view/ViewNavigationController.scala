package com.sfxcode.sapphire.jfoenix.demo.controller.view

import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractNavigationController
import com.sfxcode.sapphire.jfoenix.demo.controller.view.tab.ViewTabController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button


class ViewNavigationController extends AbstractNavigationController {

  @FXML var tabButton: Button = _


  lazy val tabController = getController[ViewTabController]()

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    navigationContentManager.updatePaneContent(tabController)
    updateToolbarButtonStyles(tabButton)
  }


  def actionShowTab(event: ActionEvent) {
    toolbarButtonClicked(event, tabController)
  }


}
