import com.android.build.api.dsl.ApplicationExtension
import com.semirsuljevic.gradleplugins.configureBuildType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class BuildTypePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<ApplicationExtension>()
            configureBuildType(extension)
        }
    }
}
