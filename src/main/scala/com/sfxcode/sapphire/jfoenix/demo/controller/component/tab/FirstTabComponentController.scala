package com.sfxcode.sapphire.jfoenix.demo.controller.component.tab

import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}

class FirstTabComponentController extends AbstractViewController {

  @FXML var infoLabel: Label          = _
  @FXML var inputTextField: TextField = _

  override def didGainVisibilityFirstTime() {}

  def actionUpdateLabel(event: ActionEvent): Unit = {
    if (inputTextField.getText.isEmpty)
      infoLabel.setText("Empty Input")
    else
      infoLabel.setText(inputTextField.getText)
    inputTextField.clear()
  }

}
