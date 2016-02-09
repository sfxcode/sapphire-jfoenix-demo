package com.sfxcode.sapphire.jfoenix.demo.controller

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.StackPane

import com.jfoenix.controls.JFXDialog.DialogTransition
import com.jfoenix.controls.JFXPopup.{PopupHPosition, PopupVPosition}
import com.jfoenix.controls._
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController

import scalafx.Includes._
import scalafx.scene.input.MouseEvent

/**
  * Created by tom on 20.10.15.
  */
class RightToolbarController extends AbstractViewController {

  @FXML var optionsBurger: StackPane = _
  @FXML var optionsRippler: JFXRippler = _
  @FXML var toolbarPopup: JFXPopup = _

  @FXML var exit: Label = _
  @FXML var contactLabel: Label = _

  override def didGainVisibilityFirstTime(): Unit = {

    // init Popup
    toolbarPopup.setPopupContainer(parent.rootPane)
    toolbarPopup.setSource(optionsRippler)
    optionsBurger.onMouseClicked = (e: MouseEvent) => {
      toolbarPopup.show(PopupVPosition.TOP, PopupHPosition.RIGHT, -12, 15)
    }

    // close application
    exit.onMouseClicked = (e: MouseEvent) => {
      Platform.exit()
    }

    contactLabel.onMouseClicked = (e: MouseEvent) => {
      // dialog.setTransitionType(DialogTransition.CENTER)
      //dialog.show(mainWindowController.workspaceManager.contentPane)

      val onAccept = () =>  println("accept clicked")
      val onDeny = () =>  println("deny clicked")

      dialogController.showDialog("Contact", "Should we contact you ?", "OK", DialogTransition.CENTER, onAccept, onDeny)
      toolbarPopup.close()
    }


  }




}
