package com.sfxcode.sapphire.jfoenix.demo.control

import com.jfoenix.controls.{JFXComboBox, JFXTextField}

object JFoenixControlFactory {

  def createSearchTextField(promptText: String = "", maxWidth: Double = 150): JFXTextField = {
    val result = new JFXTextField()
    result.setMaxWidth(maxWidth)
    result.setPrefWidth(maxWidth)
    result.setPromptText(promptText)
    result
  }

  def createSearchComboBox(): JFXComboBox[String] = {
    val result = new JFXComboBox[String]()
    result
  }
}
