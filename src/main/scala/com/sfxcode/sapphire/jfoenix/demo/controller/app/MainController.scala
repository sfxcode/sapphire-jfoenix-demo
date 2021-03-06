package com.sfxcode.sapphire.jfoenix.demo.controller.app

import com.jfoenix.controls.events.JFXDrawerEvent
import com.jfoenix.controls.{JFXDrawer, JFXHamburger}
import com.sfxcode.sapphire.javafx.controller.ViewController
import com.sfxcode.sapphire.javafx.scene.ContentManager
import com.sfxcode.sapphire.jfoenix.demo.controller.page.person.{PersonDetailPageController, PersonMasterPageController}
import com.sfxcode.sapphire.jfoenix.demo.controller.page.{HomePageController, TabPageController}
import com.typesafe.scalalogging.LazyLogging
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.MenuBar
import javafx.scene.layout.StackPane
import scalafx.Includes._
import scalafx.scene.input.MouseEvent

class MainController extends ViewController with LazyLogging {

  @FXML var menuBar: MenuBar = _

  @FXML var root: StackPane = _

  @FXML var sideContentPane: StackPane  = _
  @FXML var workspacePane: StackPane    = _
  @FXML var statusPane: StackPane       = _
  @FXML var rightToolbarPane: StackPane = _

  @FXML var titleBurgerContainer: StackPane = _
  @FXML var titleBurger: JFXHamburger       = _

  @FXML var drawer: JFXDrawer = _

  lazy val homeController: HomePageController                 = getController[HomePageController]()
  lazy val personMasterController: PersonMasterPageController = getController[PersonMasterPageController]()
  lazy val personDetailController: PersonDetailPageController = getController[PersonDetailPageController]()
  lazy val viewNavigationController: TabPageController        = getController[TabPageController]()

  lazy val sideMenuController: SideMenuController         = getController[SideMenuController]()
  lazy val statusBarController: StatusBarController       = getController[StatusBarController]()
  lazy val rightToolbarController: RightToolbarController = getController[RightToolbarController]()

  var workspaceManager: ContentManager = _
  var sideMenuManager: ContentManager  = _

  private var counter: Int = 0

  override def didGainVisibilityFirstTime() {
    menuBar.setUseSystemMenuBar(true)

    workspaceManager = ContentManager(workspacePane, this, homeController)

    sideMenuManager = ContentManager(sideContentPane, this, sideMenuController)

    updatePaneContent(statusPane, statusBarController)
    updatePaneContent(rightToolbarPane, rightToolbarController)

    //dialogController.closeDialog()

    drawer.setOnDrawerOpening { (_: JFXDrawerEvent) =>
      titleBurger.getAnimation.setRate(1)
      titleBurger.getAnimation.onFinished = (_: ActionEvent) => {
        counter = 1
      }
      titleBurger.getAnimation.play()
    }

    drawer.setOnDrawerClosing { (_: JFXDrawerEvent) =>
      titleBurger.getAnimation.setRate(-1)
      titleBurger.getAnimation.onFinished = (_: ActionEvent) => {
        counter = 0
      }
      titleBurger.getAnimation.play()
    }

    titleBurgerContainer.onMouseClicked = (_: MouseEvent) => {
      if (counter == 0)
        drawer.open()
      else if (counter == 1)
        drawer.close()
      counter = -1
    }

  }

  def actionLoadHomeController(event: ActionEvent): Unit =
    updatePage(homeController)

  def actionLoadViewController(event: ActionEvent): Unit =
    updatePage(viewNavigationController)

  def actionLoadPersonMasterController(event: ActionEvent): Unit =
    updatePage(personMasterController)

  def updatePage(pageController: ViewController): Unit =
    workspaceManager.updatePaneContent(pageController)
}