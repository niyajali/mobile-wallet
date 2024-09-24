package org.mifospay

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
internal fun Project.configureKotlinMultiplatform() {
    extensions.configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()

        jvm()
        androidTarget()
        iosSimulatorArm64()
        iosX64()
        iosArm64()

        // Suppress 'expect'/'actual' classes are in Beta.
        targets.configureEach {
            compilations.configureEach {
                compilerOptions.configure {
                    freeCompilerArgs.addAll("-Xexpect-actual-classes")
                }
            }
        }

        // Fixes Cannot locate tasks that match ':core:model:testClasses' as task 'testClasses'
        // not found in project ':core:model'. Some candidates are: 'jsTestClasses', 'jvmTestClasses'.
        project.tasks.create("testClasses") {
            dependsOn("allTests")
        }
    }
}