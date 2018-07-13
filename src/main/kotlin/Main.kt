
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import core.configuration.injector.CoreModule
import core.infrastructure.resource.Api
import basket.configuration.injector.BasketModule

/**
 * @author Clément Garbay
 */
fun main(args: Array<String>) {
    val kodein = Kodein {
        import(CoreModule.bindings)
        import(BasketModule.bindings)
    }

    val api: Api = kodein.instance()

    api.run()
}