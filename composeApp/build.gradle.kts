import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    kotlin("plugin.serialization") version libs.versions.kotlin
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.3.0") // For Charts KMP
            implementation("org.jetbrains.lets-plot:lets-plot-batik:4.3.0") // Required for rendering
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Custom
            implementation(libs.gitlive.firebase.firestore)
            implementation(libs.kotlinx.serialization.json)
            implementation("io.coil-kt.coil3:coil-compose:3.0.4")
            implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")
            implementation("com.russhwolf:multiplatform-settings:1.0.0")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

compose.desktop {
    application {
        mainClass = "az.hamburg.management.MainKt"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Deb
                //TargetFormat.Exe,
                //TargetFormat.Msi
            )
            packageName = "Hamburg Management"
            packageVersion = "1.0.0"
            jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
            includeAllModules = true
            macOS {
                iconFile.set(project.file("src/desktopMain/resources/hamburg_logo_512.icns"))
            }
            windows {
                iconFile.set(project.file("src/desktopMain/resources/hamburg_logo_512.ico"))
            }
        }
    }
}
