package com.github.bzumhagen.sbtsct

import com.github.bzumhagen.sct.BuildChangelog
import sbt.Keys.baseDirectory
import sbt.{Def, _}

object SctPlugin extends AutoPlugin {

  object autoImport {
    lazy val sctConfigPath: SettingKey[String] = settingKey[String]("Path to custom sct config")
    val changelog: TaskKey[Unit] = taskKey[Unit]("Generate changelog")
  }

  import autoImport._

  override def trigger: PluginTrigger = allRequirements

  override lazy val buildSettings = Seq(
    sctConfigPath := "",
    changelog := changelogTask.value
  )

  lazy val changelogTask: Def.Initialize[Task[Unit]] =
    Def.task {
      if(sctConfigPath.value.nonEmpty) {
        BuildChangelog.main(Array("-r", baseDirectory.value.getAbsolutePath, "-c", sctConfigPath.value))
      } else {
        BuildChangelog.main(Array("-r", baseDirectory.value.getAbsolutePath, "-c", "changelog.conf"))
      }
    }
}
