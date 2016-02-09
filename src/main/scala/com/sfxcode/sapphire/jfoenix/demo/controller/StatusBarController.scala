package com.sfxcode.sapphire.jfoenix.demo.controller


import javafx.fxml.FXML


import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import org.controlsfx.control.StatusBar


class StatusBarController extends AbstractViewController {

  @FXML var statusBar: StatusBar = _


  override def didGainVisibilityFirstTime() {
    statusBar.setText("Application loaded ...")
  }

  def setText(value:String) = {
    statusBar.setText(value)
  }

}
