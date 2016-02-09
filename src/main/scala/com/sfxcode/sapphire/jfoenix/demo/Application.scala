package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.core.cdi.FXApp
import com.typesafe.config.ConfigFactory

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.StackPane
import scalafx.stage.Stage

object Application extends FXApp {

  JFXApp.AUTO_SHOW = true

  override def applicationStage:Stage  = {

    val conf = ConfigFactory.load()
    val stage = new PrimaryStage {
      title = "%s (%s)".format(conf.getString("project.name"), conf.getString("project.version"))
      minHeight = 800
      minWidth = 1000
      scene = new Scene {
      }
    }

    val scene = new Scene(new StackPane(), 800,800)
    scene.stylesheets.add(Application.getClass.getResource("/resources/css/jfoenix-design.css").toExternalForm)
    scene.stylesheets.add(Application.getClass.getResource("/resources/css/jfoenix-fonts.css").toExternalForm)
    stage.scene = scene
    stage

  }


}


