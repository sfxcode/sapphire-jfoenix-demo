package com.sfxcode.sapphire.jfoenix.demo

import com.jfoenix.assets.JFoenixResources
import com.sfxcode.sapphire.javafx.ConfigValues
import com.sfxcode.sapphire.javafx.application.ApplicationEnvironment
import com.sfxcode.sapphire.javafx.controller.BaseApplicationController
import com.sfxcode.sapphire.jfoenix.demo.controller.app.MainController
import javafx.event.ActionEvent
import javafx.scene.{Parent, Scene}

class ApplicationController extends BaseApplicationController with ConfigValues {

  var lastNavigationControllerName = ""

  var mainViewController: MainController = _

  def applicationDidLaunch() {
    logger.info("init " + this)
    reload()
  }

  def actionReload(event: ActionEvent): Unit = {
    logger.info("reload event called by %s".format(event.getSource))

    // change stage because of JFoenix Decorator
    val oldStage = stage
    setStage(ApplicationEnvironment.application.createDefaultStage())
    oldStage.close()

    reload()
  }

  private def reload(): Unit = {
    // reset bundles
    ApplicationEnvironment.clearResourceBundleCache()
    ApplicationEnvironment.loadResourceBundle("bundles/application")

    // replace scene content with new MainViewController instance
    reloadStyles()
    mainViewController = getController[MainController]()
    replaceSceneContent(mainViewController)

    // relod jfoenix helper css
    val stylesheets = mainViewController.scene.getStylesheets
    stylesheets.clear()
    val jfoenixFontsCSS: String  = JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm
    val jfoenixDesignCSS: String = JFoenixResources.load("css/jfoenix-design.css").toExternalForm
    stylesheets.addAll(jfoenixFontsCSS, jfoenixDesignCSS)

    logger.info("reload finished")

  }

  def applicationName: ApplicationName =
    ApplicationName(configStringValue("application.name"))

  override protected def replaceSceneContentWithNode(content: Parent, resize: Boolean = true) {
    val decorator = new JFoenixDecorator(stage, content, true, true)
    sceneProperty.set(new Scene(decorator, stage.widthProperty().get, stage.heightProperty().get))

    decorator.setContent(content)
    if (!stage.isShowing)
      stage.show()

    stage.setScene(scene)

    if (resize)
      stage.sizeToScene()
  }

}

case class ApplicationName(name: String)
