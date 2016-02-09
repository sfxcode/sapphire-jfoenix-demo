package com.sfxcode.sapphire.jfoenix.demo.controller

import javafx.fxml.FXML
import javafx.scene.control.Label

import com.jfoenix.controls.JFXDialog.DialogTransition
import com.jfoenix.controls._
import com.sfxcode.sapphire.jfoenix.demo.controller.DialogController._
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController

import scalafx.Includes._
import scalafx.scene.input.MouseEvent

/**
  * Created by tom on 20.10.15.
  */
class DialogController extends AbstractViewController {

  @FXML var dialog: JFXDialog = _
  @FXML var acceptButton: JFXButton = _
  @FXML var denyButton: JFXButton = _

  @FXML var dialogHeading: Label = _
  @FXML var dialogText: Label = _

  var acceptCallback: () => Unit = CallbackEmpty
  var denyCallback: () => Unit = CallbackEmpty

  var hasOpenDialog = false

  override def mainWindowController = applicationEnvironment.getController[MainWindowController].get

  override def didInitialize(): Unit = {

    acceptButton.onMouseClicked = (e: MouseEvent) => {
      acceptCallback()
      closeDialog()
    }

    denyButton.onMouseClicked = (e: MouseEvent) => {
      denyCallback()
      closeDialog()
    }
  }

  def showDialog(heading: String, text: String, acceptButtonText: String = "OK", transition: JFXDialog.DialogTransition = DialogTransition.CENTER, onAccept: () => Unit = CallbackEmpty, onDeny: () => Unit = CallbackEmpty): Unit = {
    dialogHeading.setText(heading)
    dialogText.setText(text)
    acceptButton.setText(acceptButtonText)
    acceptCallback = onAccept
    denyCallback = onDeny

    dialog.show(mainWindowController.workspaceManager.contentPane)
    hasOpenDialog = true
  }

  def closeDialog(): Unit = {
    if (hasOpenDialog) {
      dialog.close()
      hasOpenDialog = false
    }
  }

}

object DialogController {
  val CallbackEmpty: () => Unit = () => Unit
}
