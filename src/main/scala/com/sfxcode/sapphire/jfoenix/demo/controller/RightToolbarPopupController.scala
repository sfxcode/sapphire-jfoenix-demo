package com.sfxcode.sapphire.jfoenix.demo.controller

import com.jfoenix.controls.JFXListView
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.event.ActionEvent
import javafx.fxml.FXML

class RightToolbarPopupController extends AbstractViewController {

  @FXML var toolbarPopupList: JFXListView[_] = _

  def actionReload(event: ActionEvent): Unit =
    applicationController.actionReload(event)

  def actionExit(event: ActionEvent): Unit =
    applicationController.exit()

}
