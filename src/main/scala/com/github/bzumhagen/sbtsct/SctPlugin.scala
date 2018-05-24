package com.github.bzumhagen.sbtsct

import com.github.bzumhagen.sct.BuildChangelog
import sbt.Keys.baseDirectory
import sbt.complete.DefaultParsers._
import sbt.{Def, _}

object SctPlugin extends AutoPlugin {

  object autoImport {
    lazy val sctConfigPath: SettingKey[String] = settingKey[String]("Path to custom sct config")
    val changelog: InputKey[Unit] = inputKey[Unit]("Generate changelog")
  }

  import autoImport._

  override def trigger: PluginTrigger = allRequirements

  override lazy val projectSettings = Seq(
    sctConfigPath := "changelog.conf",
    changelog := changelogTask.evaluated
  )

  lazy val changelogTask: Def.Initialize[InputTask[Unit]] =
    Def.inputTask {
      val args: Seq[String] = spaceDelimited("<arg>").parsed

      if (args.nonEmpty) {
        BuildChangelog.main(Array("-r", baseDirectory.value.getAbsolutePath, "-c", sctConfigPath.value, "-v", args.head))
      } else {
        BuildChangelog.main(Array("-r", baseDirectory.value.getAbsolutePath, "-c", sctConfigPath.value))
      }
    }
}
