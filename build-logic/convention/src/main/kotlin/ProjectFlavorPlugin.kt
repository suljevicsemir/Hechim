import com.android.build.api.dsl.ApplicationExtension
import com.semirsuljevic.gradleplugins.configureProjectFlavor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class ProjectFlavorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<ApplicationExtension>()
            configureProjectFlavor(extension)
        }
    }
}
