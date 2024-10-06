import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.mifospay.libs


class KMPKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                val bom = libs.findLibrary("koin-bom").get()
                add("commonMainImplementation", platform(bom))
                add("commonMainImplementation", libs.findLibrary("koin.core").get())

                add("commonMainImplementation", libs.findLibrary("koin.annotations").get())
                add("kspCommonMainMetadata", libs.findLibrary("koin.ksp.compiler").get())
//                add("kspAndroid", libs.findLibrary("koin.ksp.compiler").get())
//                add("kspWasmJs", libs.findLibrary("koin.ksp.compiler").get())
//                add("kspJvm", libs.findLibrary("koin.ksp.compiler").get())
//                add("kspIosX64", libs.findLibrary("koin.ksp.compiler").get())
//                add("kspIosArm64", libs.findLibrary("koin.ksp.compiler").get())
//                add("kspIosSimulatorArm64", libs.findLibrary("koin.ksp.compiler").get())

                add("commonTestImplementation", libs.findLibrary("koin.test").get())
            }

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK","true")
            }
        }
    }
}
