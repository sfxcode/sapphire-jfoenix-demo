package com.sfxcode.sapphire.jfoenix.demo.controller.page.person

import com.sfxcode.sapphire.javafx.controller.{BaseDetailController, BaseMasterController}
import com.sfxcode.sapphire.javafx.filter.DataTableFilter
import com.sfxcode.sapphire.javafx.value.{BeanConversions, FXBean}
import com.sfxcode.sapphire.jfoenix.demo.control.JFoenixControlFactory.{createSearchComboBox, createSearchTextField}
import com.sfxcode.sapphire.jfoenix.demo.controller.base.BaseController
import com.sfxcode.sapphire.jfoenix.demo.model.Person
import com.sfxcode.sapphire.jfoenix.demo.sevices.PersonServices
import javafx.collections.ObservableList

import scala.reflect._

class PersonMasterPageController extends BaseMasterController with BaseController with BeanConversions {
  lazy val detailPageController = getController[PersonDetailPageController]()

  type R = Person

  def ct: ClassTag[Person] = classTag[R]

  def items: ObservableList[FXBean[Person]] = PersonServices.personAll()

  override def didGainVisibilityFirstTime(): Unit = {
    super.didGainVisibilityFirstTime()
    detailController = Some(detailPageController)
  }

  override def initTable(tableFilter: DataTableFilter[R]): Unit = {
    super.initTable(tableFilter)
    tableFilter.hideColumn("metaData")
    tableFilter.addSearchField("nameFilter", "name", searchField = createSearchTextField(promptText = "Name"))

    tableFilter.hideColumn("tags", "friends", "about", "guid", "picture")

    tableFilter.addSearchField("addressFilter", "address", searchField = createSearchTextField(promptText = "Address"))
    tableFilter.addSearchBox("genderFilter", "gender", "male/female", createSearchComboBox())
    tableFilter.addSearchBox("fruitFilter", "favoriteFruit", "all fruits", createSearchComboBox())
  }

  override def navigateToDetailController(detailController: BaseDetailController): Unit =
    mainViewController.updatePage(detailPageController)

}
