package com.github.grishberg.instrumentaltestsample

import com.github.grishberg.androidemulatormanager.CreateAndRunEmulatorsTask
import com.github.grishberg.androidemulatormanager.StopEmulatorsTask
import com.github.grishberg.tests.InstrumentationTestTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Main plugin for tests.
 * Usage: ./gradlew clean startConnectedTest
 */
class RunTestPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        InstrumentationTestTask testTask = project.tasks.findByName(InstrumentationTestTask.NAME)
        CreateAndRunEmulatorsTask createAndRunEmulatorsTask = project.tasks.findByName(CreateAndRunEmulatorsTask.NAME)
        StopEmulatorsTask stopEmulatorsTask = project.tasks.findByName(StopEmulatorsTask.NAME)
        stopEmulatorsTask.mustRunAfter "connectedAndroidTest"

        String projectName = project.name
        testTask.setCommandProvider(new CustomCommandsProvider(
                projectName, project.logger, new ArgsProvider(project.logger)))

        /**
         * Setup install apk and test apk
         */
        def installApkTask = project.tasks.create("installApk") {
            dependsOn('installDebug', 'installDebugAndroidTest')
            finalizedBy(/*stopEmulatorsTask,*/ testTask)
            mustRunAfter createAndRunEmulatorsTask

            doLast {
                println 'init instrumental task'
            }
        }

        /**
         * Starts creating emulators and running instrumental tests.
         */
        project.tasks.create("startConnectedTest") {
            //finalizedBy createAndRunEmulatorsTask
            finalizedBy installApkTask
            finalizedBy 'assembleDebug'
            finalizedBy 'assembleAndroidTest'
        }
    }
}
