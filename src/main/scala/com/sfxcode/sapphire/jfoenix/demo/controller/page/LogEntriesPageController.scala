package com.sfxcode.sapphire.jfoenix.demo.controller.page

import com.sfxcode.sapphire.javafx.controller.SFXDataTableController
import com.sfxcode.sapphire.javafx.filter.SFXDataTableFilter
import com.sfxcode.sapphire.javafx.value.{SFXBean, SFXBeanConversions, SFXKeyBindings}
import com.sfxcode.sapphire.jfoenix.demo.control.JFoenixControlFactory.createSearchTextField
import com.sfxcode.sapphire.jfoenix.demo.model.LogEntry
import com.sfxcode.sapphire.jfoenix.demo.sevices.LogService
import javafx.collections.ObservableList

import scala.reflect.{classTag, ClassTag}

class LogEntriesPageController extends SFXDataTableController with SFXBeanConversions {
  type R = LogEntry

  def ct: ClassTag[LogEntry] = classTag[R]

  def items: ObservableList[SFXBean[LogEntry]] = LogService.logEntries()

  override def initTable(tableFilter: SFXDataTableFilter[R]): Unit = {
    super.initTable(tableFilter)

    tableFilter.addSearchField(
      "severityFilter",
      "severity",
      searchField = createSearchTextField(promptText = "Severity")
    )
    tableFilter.addSearchField("topicFilter", "topic", searchField = createSearchTextField(promptText = "Topic"))
    tableFilter.addSearchField("messageFilter", "message", searchField = createSearchTextField(promptText = "Message"))

  }

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    tableFilter.getItems.clear()
    tableFilter.getItems.addAll(LogService.logEntries())
  }

  /*
      normally columns are added by reflection - here we use columns from fxml
   */
  override def shouldAddColunns: Boolean = false
}
