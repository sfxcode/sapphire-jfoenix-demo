package com.sfxcode.sapphire.jfoenix.demo.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label

import com.jfoenix.controls.JFXListView
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController

import scalafx.Includes._

/**
  * Created by tom on 20.10.15.
  */
class SideMenuController extends AbstractViewController {

  @FXML var sideList: JFXListView[Label] = _

  def actionClickButton(event: ActionEvent) {
    logger.debug(event.toString)
  }

  override def didGainVisibilityFirstTime() {
    sideList.getSelectionModel.selectedItemProperty.onChange((_, oldValue, newValue) =>
      itemSelected(oldValue, newValue)
    )
  }

  def itemSelected(oldValue: Label, newValue: Label) {
    logger.debug("changedFrom %s to %s".format(oldValue, newValue))

    statusBarController.updateStatusBarText(newValue.getText)

  }

}
