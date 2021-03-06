package com.sfxcode.sapphire.jfoenix.demo.controller.app

import com.jfoenix.controls.events.JFXDrawerEvent
import com.jfoenix.controls.{JFXDrawer, JFXHamburger}
import com.sfxcode.sapphire.javafx.controller.SFXViewController
import com.sfxcode.sapphire.javafx.scene.SFXContentManager
import com.sfxcode.sapphire.jfoenix.demo.controller.base.ToolbarStyling
import com.sfxcode.sapphire.jfoenix.demo.controller.page.person.{PersonDetailPageController, PersonMasterPageController}
import com.sfxcode.sapphire.jfoenix.demo.controller.page.{
  HomePageController,
  LogEntriesPageController,
  UISamplePageController
}
import com.sfxcode.sapphire.jfoenix.demo.sevices.LogService
import com.typesafe.scalalogging.LazyLogging
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{Button, MenuBar}
import javafx.scene.layout.StackPane
import scalafx.Includes._
import scalafx.scene.input.MouseEvent

class MainController extends SFXViewController with ToolbarStyling with LazyLogging {

  @FXML var menuBar: MenuBar = _

  @FXML var root: StackPane = _

  @FXML var homeButton: Button = _

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
  lazy val samplePageController: UISamplePageController       = getController[UISamplePageController]()
  lazy val logEntriesPageController: LogEntriesPageController = getController[LogEntriesPageController]()

  lazy val sideMenuController: SideMenuController         = getController[SideMenuController]()
  lazy val statusBarController: StatusBarController       = getController[StatusBarController]()
  lazy val rightToolbarController: RightToolbarController = getController[RightToolbarController]()

  var workspaceManager: SFXContentManager = _
  var sideMenuManager: SFXContentManager  = _

  private var counter: Int = 0

  override def toolbarButtonStyleClass: String = "main-menu"

  override def mainSFXContentManager: SFXContentManager = workspaceManager

  override def didGainVisibilityFirstTime() {
    menuBar.setUseSystemMenuBar(true)
    updateToolbarButtonStyles(homeButton)

    workspaceManager = SFXContentManager(workspacePane, this, homeController)

    sideMenuManager = SFXContentManager(sideContentPane, this, sideMenuController)

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
    updatePage(homeController, event)

  def actionLoadSampleController(event: ActionEvent): Unit =
    updatePage(samplePageController, event)

  def actionLoadLogsController(event: ActionEvent): Unit =
    updatePage(logEntriesPageController, event)

  def actionLoadPersonMasterController(event: ActionEvent): Unit =
    updatePage(personMasterController, event)

  def updatePage(pageController: SFXViewController, event: ActionEvent): Unit = {
    toolbarButtonClicked(event, pageController)
    LogService.addLogEntry(
      "controller: %s was loaded".format(pageController.getClass.getSimpleName),
      severity = "debug"
    )
  }

  def updatePage(pageController: SFXViewController): Unit =
    workspaceManager.updatePaneContent(pageController)
}
