package com.sfxcode.sapphire.jfoenix.demo

import com.sfxcode.sapphire.core.ConfigValues
import com.sfxcode.sapphire.core.application.FXApp

object Application extends FXApp with ConfigValues{

  override def title = "%s (%s)".format(configStringValue("project.name"), BuildInfo.version)

  override def height: Int = 800

  override def width: Int = 1000

}


