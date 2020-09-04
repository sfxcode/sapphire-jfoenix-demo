package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.core.{BuildInfo, ConfigValues}
import com.sfxcode.sapphire.core.application.BaseApplication
import com.sfxcode.sapphire.core.controller.BaseApplicationController

object Application extends BaseApplication with ConfigValues {

  override def title = "%s (%s)".format(configStringValue("project.name"), BuildInfo.version)

  override def height: Int = 800

  override def width: Int = 1000

  override val applicationController: BaseApplicationController = new ApplicationController
}
