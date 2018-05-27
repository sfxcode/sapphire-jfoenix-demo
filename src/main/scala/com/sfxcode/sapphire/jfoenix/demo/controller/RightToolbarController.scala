package com.sfxcode.sapphire.jfoenix.demo.controller

import com.jfoenix.controls.JFXPopup.{PopupHPosition, PopupVPosition}
import com.jfoenix.controls._
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.fxml.FXML
import javafx.scene.layout.StackPane
import scalafx.Includes._
import scalafx.scene.input.MouseEvent

/**
  * Created by tom on 20.10.15.
  */
class RightToolbarController extends AbstractViewController {

  @FXML var optionsBurger: StackPane = _
  @FXML var optionsRippler: JFXRippler = _

  var toolbarPopup: JFXPopup = _

  lazy val rightToolbarPopupController = getController[RightToolbarPopupController]()

  override def didGainVisibilityFirstTime(): Unit = {

    toolbarPopup = new JFXPopup(rightToolbarPopupController.rootPane)

    optionsBurger.onMouseClicked = (e: MouseEvent) => {
      toolbarPopup.show(optionsBurger, PopupVPosition.TOP, PopupHPosition.RIGHT, -12, 15)
    }

  }


}
