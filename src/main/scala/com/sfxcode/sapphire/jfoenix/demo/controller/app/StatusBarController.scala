package com.sfxcode.sapphire.jfoenix.demo.controller.app

import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.fxml.FXML
import javafx.scene.control.Label

class StatusBarController extends AbstractViewController {

  @FXML var messageLabel: Label = _

  override def didGainVisibilityFirstTime() {
    messageLabel.setText("Sapphire - JFoenix - Scalafx - JavaFX - MongoDB - Application loaded ...")
  }

  def updateStatusBarText(newValue: String) =
    messageLabel.setText(newValue)

}
