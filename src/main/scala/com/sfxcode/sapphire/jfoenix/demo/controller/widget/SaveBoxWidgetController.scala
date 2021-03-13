package com.sfxcode.sapphire.jfoenix.demo.controller.widget

import com.sfxcode.sapphire.javafx.controller.{SFXDetailController, SFXViewController}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button

class SaveBoxWidgetController extends SFXViewController {

  @FXML
  var saveButton: Button = _

  @FXML
  var cancelButton: Button = _

  @FXML
  var revertButton: Button = _

  private def detailController: SFXDetailController = parent.asInstanceOf[SFXDetailController]

  private def beanAdapter = detailController.formAdapter

  override def didGainVisibilityFirstTime(): Unit = {
    super.didGainVisibilityFirstTime()
    cancelButton.setVisible(true)
    saveButton.setVisible(false)
    revertButton.setVisible(false)
    beanAdapter.addBinding(saveButton.visibleProperty(), "_hasChanges")
    beanAdapter.addBinding(revertButton.visibleProperty(), "_hasChanges")
  }

  override def willLooseVisibility(): Unit =
    super.willLooseVisibility()

  def actionSave(event: ActionEvent): Unit =
    detailController.actionSaveAndReturn(event)

  def actionReturn(event: ActionEvent): Unit =
    detailController.actionRevertAndReturn(event)

  def actionRevert(event: ActionEvent): Unit =
    detailController.actionRevert(event)

}
