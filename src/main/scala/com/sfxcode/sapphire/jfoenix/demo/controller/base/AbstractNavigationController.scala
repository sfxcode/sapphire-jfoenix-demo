package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.sfxcode.sapphire.javafx.scene.SFXContentManager
import javafx.fxml.FXML
import javafx.scene.layout.StackPane

abstract class AbstractNavigationController extends AbstractViewController with ToolbarStyling {

  var navigationSFXContentManager: SFXContentManager = _

  @FXML
  var navigationContentPane: StackPane = _

  override def mainSFXContentManager: SFXContentManager = navigationSFXContentManager

  override def toolbarButtonStyleClass: String = "navigation-menu"

  override def didGainVisibilityFirstTime(): Unit =
    navigationSFXContentManager = SFXContentManager(navigationContentPane, this)

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    applicationController.lastNavigationControllerName = this.getClass.getSimpleName
  }
}
