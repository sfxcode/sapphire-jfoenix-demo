package com.sfxcode.sapphire.jfoenix.demo.controller.page.person

import com.sfxcode.sapphire.javafx.controller.{BaseDetailController, BaseMasterController}
import com.sfxcode.sapphire.javafx.value.{FXBean, KeyBindings}
import com.sfxcode.sapphire.jfoenix.demo.controller.base.BaseController
import com.sfxcode.sapphire.jfoenix.demo.controller.widget.SaveBoxWidgetController
import com.sfxcode.sapphire.jfoenix.demo.model.Person
import com.sfxcode.sapphire.jfoenix.demo.sevices.{LogService, PersonServices}
import javafx.fxml.FXML
import javafx.scene.control.{Button, TitledPane}
import javafx.scene.layout.StackPane

import scala.reflect._

class PersonDetailPageController extends BaseDetailController with BaseController {

  @FXML var detailPane: TitledPane = _
  @FXML var saveButton: Button     = _
  @FXML var detailButton: Button   = _

  @FXML
  var savePane: StackPane = _

  lazy val saveController: SaveBoxWidgetController = getController[SaveBoxWidgetController]()

  type R = Person

  def ct: ClassTag[Person] = classTag[R]

  override def didGainVisibilityFirstTime(): Unit = {
    super.didGainVisibilityFirstTime()
    updatePaneContent(savePane, saveController)
  }

  override def navigateToMasterController(masterController: BaseMasterController): Unit =
    mainViewController.updatePage(masterController)

  def updateBindings(bindings: KeyBindings): Unit =
    formAdapter.addBindings(KeyBindings.forClass[Person]())

  override def updateBean(bean: FXBean[Person]): Unit = {
    super.updateBean(bean)
    detailPane.setText("Edit: %s".format(bean.wrappedData.name))
  }

  override def save(beanValue: R): Unit = {
    PersonServices.updatePerson(beanValue)
    LogService.addLogEntry("Person: %s (%s) updated".format(beanValue.name, beanValue.id), LogService.TopicDatabase)
  }
}
