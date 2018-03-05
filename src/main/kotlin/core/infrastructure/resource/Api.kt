package core.infrastructure.resource

import spark.kotlin.port

/**
 * @author Clément Garbay
 */
class Api(private val resources: Set<Resource>) : Resource {

    private val PORT = 8080

    override fun run() {
        port(PORT)

        // TODO: add JSON support for body request and response

        resources.forEach { it.run() }

        print("API started on port: $PORT")
    }

}