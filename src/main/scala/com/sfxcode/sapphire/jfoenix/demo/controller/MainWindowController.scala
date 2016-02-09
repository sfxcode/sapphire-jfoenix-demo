package com.sfxcode.sapphire.jfoenix.demo.controller

import javafx.event.{Event, EventHandler}
import javafx.fxml.FXML
import javafx.scene.control.MenuBar
import javafx.scene.layout.StackPane
import javax.enterprise.event.Observes

import com.jfoenix.controls.{JFXDrawer, JFXHamburger}
import com.sfxcode.sapphire.core.controller.ViewController
import com.sfxcode.sapphire.core.scene.{ContentDidChangeEvent, ContentManager}
import com.typesafe.scalalogging.LazyLogging

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.input.MouseEvent


class MainWindowController extends ViewController with LazyLogging {

  @FXML var menuBar: MenuBar = _

  @FXML var root: StackPane = _

  @FXML var sideContentPane: StackPane = _
  @FXML var workspacePane: StackPane = _
  @FXML var statusPane: StackPane = _
  @FXML var rightToolbarPane: StackPane = _

  @FXML var titleBurgerContainer: StackPane = _
  @FXML var titleBurger: JFXHamburger = _

  @FXML var drawer: JFXDrawer = _


  lazy val workspaceController = getController[WorkspaceController]()
  lazy val sideMenuController = getController[SideMenuController]()
  lazy val statusBarController = getController[StatusBarController]()
  lazy val rightToolbarController = getController[RightToolbarController]()

  lazy val dialogController = getController[DialogController]()

  var workspaceManager: ContentManager = _
  var sideMenuManager: ContentManager = _
  var statusBarManager: ContentManager = _
  var rightToolbarManager: ContentManager = _

  private var counter: Int = 0

  override def didGainVisibilityFirstTime() {
    menuBar.setUseSystemMenuBar(true)


    workspaceManager = ContentManager(workspacePane, this, workspaceController)
    sideMenuManager = ContentManager(sideContentPane, this, sideMenuController)
    statusBarManager = ContentManager(statusPane, this, statusBarController)
    rightToolbarManager = ContentManager(rightToolbarPane, this, rightToolbarController)

    dialogController.closeDialog()


    drawer.setOnDrawingAction(new EventHandler[Event] {
      override def handle(event: Event): Unit = {
        titleBurger.getAnimation.setRate(1)
        titleBurger.getAnimation.onFinished = (e: ActionEvent) => {
          counter = 1
        }
        titleBurger.getAnimation.play()
      }
    })

    drawer.setOnHidingAction(new EventHandler[Event] {
      override def handle(event: Event): Unit = {
        titleBurger.getAnimation.setRate(-1)
        titleBurger.getAnimation.onFinished = (e: ActionEvent) => {
          counter = 0
        }
        titleBurger.getAnimation.play()
      }
    })

    titleBurgerContainer.onMouseClicked = (e: MouseEvent) => {
      if (counter == 0) {
        drawer.draw()
      }
      else if (counter == 1)
        drawer.hide()
      counter = -1
    }

  }

  def listenToChanges(@Observes event: ContentDidChangeEvent) {
    logger.debug(event.toString)
  }

}

