package com.sfxcode.sapphire.jfoenix.demo.controller

import com.jfoenix.controls.JFXListView
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import javafx.event.ActionEvent
import javafx.fxml.FXML

class RightToolbarPopupController extends AbstractViewController {

  @FXML var toolbarPopupList: JFXListView[_] = _

  def actionRefresh(event: ActionEvent): Unit = {
    applicationController.replacePrimarySceneContent()
  }

  def actionExit(event: ActionEvent): Unit = {
    applicationController.exit()
  }



  }
