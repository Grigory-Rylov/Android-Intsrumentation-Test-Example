package com.github.grishberg.instrumentaltestsample

import com.github.grishberg.androidemulatormanager.CreateAndRunEmulatorsTask
import com.github.grishberg.androidemulatormanager.StopEmulatorsTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class RunTestPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        CreateAndRunEmulatorsTask createAndRunEmulatorsTask = project.tasks.findByName(CreateAndRunEmulatorsTask.NAME)
        StopEmulatorsTask stopEmulatorsTask = project.tasks.findByName(StopEmulatorsTask.NAME)
        stopEmulatorsTask.mustRunAfter "connectedAndroidTest"

        /**
         * Setup install apk and test apk
         */
        def installApkTask = project.tasks.create("installApk") {
            dependsOn('installDebug', 'installDebugAndroidTest')
            finalizedBy(stopEmulatorsTask, "connectedAndroidTest")
            mustRunAfter createAndRunEmulatorsTask
        }

        /**
         * Starts creating emulators and running instrumental tests.
         */
        project.tasks.create("startConnectedTest") {
            finalizedBy createAndRunEmulatorsTask
            finalizedBy installApkTask
            finalizedBy 'assembleDebug'
            finalizedBy 'assembleAndroidTest'
        }
    }
}
