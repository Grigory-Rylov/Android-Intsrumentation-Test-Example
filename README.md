# Android-Intsrumentation-Test-Example

Steps: 

1) create buildSrc module folder in root project

2) create build.gradle in buildSrc module folder and put:

```
apply plugin: 'groovy'

repositories {
    google()
    jcenter()
    mavenLocal()
}
dependencies {
    implementation 'com.android.tools.build:gradle:4.0.2'

    implementation 'com.github.grishberg:android-instrumental-test-runner:1.6.14'
}

dependencies {
    implementation gradleApi()
}

```

3) create RunTestPlugin.groovy in buildSrc/main/groovy/YOUR_PACKAGE/

4) add next code to RunTestPlugin.gradle:
```
package com.github.grishberg.instrumentaltestsample

import com.github.grishberg.tests.InstrumentationTestTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class RunTestPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        InstrumentationTestTask testTask = project.tasks.findByName(InstrumentationTestTask.NAME)

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
         * Prepare builds and run instrumental tests.
         */
        project.tasks.create("startConnectedTest") {
            finalizedBy installApkTask
            finalizedBy 'assembleDebug'
            finalizedBy 'assembleAndroidTest'
            finalizedBy testTask // or 'instrumentalTests'
        }
    }
}
```

6) create `instrumental_config.gradle` :
```
instrumentalPluginConfig {
    flavorName = 'debug'
    instrumentalPackage = 'com.github.grishberg.instrumentaltestsample.test' // your test package
    applicationId = 'com.github.grishberg.instrumentaltestsample' // your appId
    instrumentalRunner = 'androidx.test.runner.AndroidJUnitRunner' // your test runner
    coverageEnabled = true // or false, if you don't need coverage
    makeScreenshotsWhenFail = true // or false, if you don't need fail screenshots
}

```

5) add ```
apply plugin: 'com.github.grishberg.instrumentalplugin'
apply plugin: RunTestPlugin
```
to module build.gradle

6) Run with ```./gradlew startConnectedTest```
   
 
   
