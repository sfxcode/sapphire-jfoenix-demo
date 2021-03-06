package com.sfxcode.sapphire.jfoenix.demo.controller.base

import com.jfoenix.controls.JFXTabPane
import javafx.fxml.FXML
import javafx.scene.control._
import scalafx.Includes._

abstract class AbstractTabController extends AbstractViewController {

  @FXML var tabPane: JFXTabPane = _

  override def didGainVisibilityFirstTime() {
    super.didGainVisibilityFirstTime()
    tabPane.getSelectionModel.selectedItemProperty.onChange { (_, oldValue, newValue) =>
      tabPaneHasChanged(oldValue, newValue)
    }
  }

  def selectedTab: Tab = tabPane.getSelectionModel.getSelectedItem

  def tabPaneHasChanged(oldValue: Tab, newValue: Tab)

}
