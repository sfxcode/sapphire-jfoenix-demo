package com.sfxcode.sapphire.jfoenix.demo.controller.page

import com.sfxcode.nosql.mongo.database.{CollectionStatus, DatabaseInfo}
import com.sfxcode.sapphire.jfoenix.demo.controller.base.AbstractViewController
import com.sfxcode.sapphire.jfoenix.demo.database.Database
import com.sfxcode.sapphire.jfoenix.demo.database.Database.{LogDAO, PersonDAO}
import javafx.fxml.FXML
import javafx.scene.control.Label

class HomePageController extends AbstractViewController {

  @FXML var databaseLabel: Label       = _
  @FXML var databaseLogLabel: Label    = _
  @FXML var databasePersonLabel: Label = _

  override def didGainVisibility(): Unit = {
    super.didGainVisibility()
    val databaseInfo: DatabaseInfo = Database.provider.databaseInfos.head
    databaseLabel.setText("databaseName: %s".format(databaseInfo.name))
    val collectionStatusLog: CollectionStatus = LogDAO.collectionStatus.result()
    databaseLogLabel.setText(statusString(collectionStatusLog))
    val collectionStatusPerson: CollectionStatus = PersonDAO.collectionStatus.result()
    databasePersonLabel.setText(statusString(collectionStatusPerson))

  }

  def statusString(collectionStatus: CollectionStatus) =
    "%s - count: %s".format(collectionStatus.ns, collectionStatus.count)

}
