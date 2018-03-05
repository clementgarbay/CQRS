
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import core.configuration.injector.CoreModule
import core.infrastructure.resource.Api
import organization.configuration.injector.OrganizationModule

/**
 * @author Clément Garbay
 */
fun main(args: Array<String>) {
    val kodein = Kodein {
        import(CoreModule.bindings)
        import(OrganizationModule.bindings)
    }

    val api: Api = kodein.instance()

    api.run()
}