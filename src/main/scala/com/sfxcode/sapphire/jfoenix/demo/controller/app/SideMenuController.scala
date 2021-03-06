package com.sfxcode.sapphire.jfoenix.demo.controller.app

import com.jfoenix.controls.JFXListView
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import com.sfxcode.sapphire.jfoenix.demo.sevices.PersonServices._
import com.sfxcode.sapphire.jfoenix.demo.model.Person
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import scalafx.Includes._

/** Created by tom on 20.10.15.
  */
class SideMenuController extends AbstractViewController {

  @FXML var sideList: JFXListView[Label] = _

  def actionClickButton(event: ActionEvent) {
    logger.debug(event.toString)
  }

  override def didGainVisibilityFirstTime() {
    sideList.getItems.clear()
    personNames.foreach { s =>
      sideList.getItems.add(new Label(s))
    }

    sideList.getSelectionModel.selectedItemProperty.onChange((_, oldValue, newValue) =>
      itemSelected(oldValue, newValue)
    )
  }

  def itemSelected(oldValue: Label, newValue: Label) {
    logger.debug("changedFrom %s to %s".format(oldValue, newValue))

    val selectedPerson: Person = personForName(newValue.text()).get
    statusBarController.updateStatusBarText("%s selected (id=%s)".format(selectedPerson.name, selectedPerson.id))

  }

}
