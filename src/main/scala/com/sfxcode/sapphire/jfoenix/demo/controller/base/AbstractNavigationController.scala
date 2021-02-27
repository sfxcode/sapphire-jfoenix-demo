package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.sfxcode.sapphire.javafx.scene.ContentManager
import javafx.fxml.FXML
import javafx.scene.layout.StackPane
import scalafx.Includes._

abstract class AbstractNavigationController extends AbstractViewController with ToolbarStyling {

  var navigationContentManager: ContentManager = _

  @FXML
  var navigationContentPane: StackPane = _

  override def mainContentManager: ContentManager = navigationContentManager

  override def toolbarButtonStyleClass: String = "navigation-menu"

  override def didGainVisibilityFirstTime(): Unit =
    navigationContentManager = ContentManager(navigationContentPane, this)

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    applicationController.lastNavigationControllerName = this.getClass.getSimpleName
  }
}
