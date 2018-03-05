package organization.infrastructure.resource

import core.failure.ValidationFailure
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.resource.Resource
import core.infrastructure.type.fold
import organization.command.DeleteOrganization
import spark.Request
import spark.Response
import spark.Spark.*
import java.util.*

/**
 * @author Clément Garbay
 */
class OrganizationResource(private val commandBus: CommandBus) : Resource {

    override fun run() {
        path("/organization") {
            get("/", getOrganizations)
            post("/", addOrganization)
            put("/:id/rename", renameOrganization)
            delete("/:id/delete", deleteOrganization)
        }
    }

    private val getOrganizations = { request: Request, response: Response ->
        "It works!"
    }

    private val addOrganization = { request: Request, response: Response ->

        TODO("not implemented")
    }

    private val renameOrganization = { request: Request, response: Response ->
        val organizationId = request.params("id").toInt()

        TODO("not implemented")
    }

    private val deleteOrganization = { request: Request, response: Response ->
        val organizationId = UUID.fromString(request.params("id"))

        val result = commandBus.dispatch(DeleteOrganization(organizationId))

        result.fold({ failure ->
            when (failure) {
                is ValidationFailure -> response.status(400)
                else -> response.status(500)
            }
        }, { _ -> response.status(200) })
    }

}


