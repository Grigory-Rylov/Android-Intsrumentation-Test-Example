package com.github.grishberg.instrumentaltestsample

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

        String projectName = project.name
        testTask.setCommandProvider(
                new CustomCommandsProvider(
                        projectName,
                        project.logger,
                        new ArgsProvider(project.logger)
                )
        )

        /**
         * Setup install apk and test apk
         */
        def installApkTask = project.tasks.create("installApk") {
            dependsOn('installDebug', 'installDebugAndroidTest')

            doLast {
                println 'init instrumental task'
            }
        }

        /**
         * Starts creating emulators and running instrumental tests.
         */
        project.tasks.create("startConnectedTest") {
            finalizedBy installApkTask
            finalizedBy 'assembleDebug'
            finalizedBy 'assembleAndroidTest'
            finalizedBy testTask
        }
    }
}
