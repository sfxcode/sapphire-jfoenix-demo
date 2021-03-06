package com.sfxcode.sapphire.jfoenix.demo.controller

import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.fxml.FXML
import org.controlsfx.control.StatusBar

class StatusBarController extends AbstractViewController {

  @FXML var statusBar: StatusBar = _

  override def didGainVisibilityFirstTime() {
    statusBar.setText("Sapphire - JFoenix - Scalafx - JavaFX - Application loaded ...")
  }

  def updateStatusBarText(newValue: String) =
    statusBar.setText(newValue)

}
