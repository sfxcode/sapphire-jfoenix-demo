package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.jfoenix.controls.JFXToolbar
import com.sfxcode.sapphire.javafx.controller.ViewController
import com.sfxcode.sapphire.javafx.scene.ContentManager
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import scalafx.Includes._

trait ToolbarStyling {

  @FXML
  var toolbar: JFXToolbar = _

  def toolbarButtonStyleClass: String

  def mainContentManager: ContentManager

  def updateToolbarButtonStyles(selectedButton: Button): Unit =
    toolbar.getLeftItems.filter(node => node.isInstanceOf[Button]).foreach { button =>
      if (button == selectedButton) {
        button.getStyleClass.remove(toolbarButtonStyleClass)
        if (!button.getStyleClass.contains(toolbarButtonStyleClass + "-highlighted"))
          button.getStyleClass.add(toolbarButtonStyleClass + "-highlighted")
      }
      else {
        button.getStyleClass.remove(toolbarButtonStyleClass + "-highlighted")
        if (!button.getStyleClass.contains(toolbarButtonStyleClass))
          button.getStyleClass.add(toolbarButtonStyleClass)
      }
    }

  def toolbarButtonClicked(event: ActionEvent, viewController: ViewController): Unit = {
    mainContentManager.updatePaneContent(viewController)
    updateToolbarButtonStyles(event.getSource.asInstanceOf[Button])

  }

}
