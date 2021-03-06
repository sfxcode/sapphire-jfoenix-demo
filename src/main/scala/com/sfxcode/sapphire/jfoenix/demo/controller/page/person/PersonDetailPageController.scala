package com.sfxcode.sapphire.jfoenix.demo.controller.page.person

import com.sfxcode.sapphire.javafx.controller.{BaseDetailController, BaseMasterController}
import com.sfxcode.sapphire.javafx.value.{FXBean, KeyBindings}
import com.sfxcode.sapphire.jfoenix.demo.controller.base.BaseController
import com.sfxcode.sapphire.jfoenix.demo.model.Person
import javafx.fxml.FXML
import javafx.scene.control.TitledPane

import scala.reflect._

class PersonDetailPageController extends BaseDetailController with BaseController {

  @FXML var detailPane: TitledPane = _

  type R = Person

  def ct: ClassTag[Person] = classTag[R]

  override def navigateToMasterController(masterController: BaseMasterController): Unit =
    mainViewController.updatePage(masterController)

  def updateBindings(bindings: KeyBindings): Unit =
    formAdapter.addBindings(KeyBindings.forClass[Person]())

  override def updateBean(bean: FXBean[Person]): Unit = {
    super.updateBean(bean)
    detailPane.setText("Edit: %s".format(bean.wrappedData.name))
  }

  override def save(beanValue: Person): Unit = {}
}
