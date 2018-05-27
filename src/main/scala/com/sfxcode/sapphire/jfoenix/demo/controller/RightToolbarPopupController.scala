package com.sfxcode.sapphire.jfoenix.demo.controller

import com.jfoenix.controls.JFXListView
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.application.Platform
import javafx.fxml.FXML

class RightToolbarPopupController extends AbstractViewController {

  @FXML var toolbarPopupList: JFXListView[_] = _


  def actionSubmit() {
    if (toolbarPopupList.getSelectionModel.getSelectedIndex == 0) {
      applicationController.replacePrimarySceneContent()
    }
    else if (toolbarPopupList.getSelectionModel.getSelectedIndex == 1) {
      Platform.exit()
    }
  }

}
