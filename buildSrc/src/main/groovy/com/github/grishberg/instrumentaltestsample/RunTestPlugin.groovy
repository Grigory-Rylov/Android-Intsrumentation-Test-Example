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

        /**
         * Setup install apk and test apk
         */
        def installApkTask = project.tasks.create("installApk") {
            dependsOn('installDebug', 'installDebugAndroidTest')
            finalizedBy(/*stopEmulatorsTask,*/ testTask)
            mustRunAfter createAndRunEmulatorsTask

            doLast {
                println 'init instrumental task'
                testTask.instrumentationArgsProvider = new ArgsProvider(project.logger);
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
