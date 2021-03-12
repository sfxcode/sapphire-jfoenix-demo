package com.sfxcode.sapphire.jfoenix.demo

import com.jfoenix.controls.JFXDecorator
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.stage.Stage

class JFoenixDecorator(stage: Stage, node: Node, fullScreen: Boolean, min: Boolean)
    extends JFXDecorator(stage, node, fullScreen, false, min) {

  var offsetX    = 0.0
  var offsetY    = 0.0
  var shouldDrag = false

  this.setOnMouseDragged((mouseEvent: MouseEvent) => handleDragEvent(mouseEvent))
  this.setOnMousePressed((mouseEvent: MouseEvent) => handleClickEvent(mouseEvent))

  def handleClickEvent(mouseEvent: MouseEvent): Unit = {
    offsetX = mouseEvent.getSceneX
    offsetY = mouseEvent.getSceneY
    shouldDrag = mouseEvent.getTarget.asInstanceOf[Node].getParent.isInstanceOf[JFoenixDecorator]
  }

  def handleDragEvent(mouseEvent: MouseEvent): Unit = {
    if (shouldDrag) {
      stage.setX(mouseEvent.getScreenX - offsetX)
      stage.setY(mouseEvent.getScreenY - offsetY)
    }
    mouseEvent.consume()
  }

}
