package basket.infrastructure.resource

import basket.command.AddProduct
import basket.command.DeleteProduct
import basket.domain.Product
import basket.query.GetBasket
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.bus.query.QueryBus
import core.infrastructure.resource.Resource
import core.infrastructure.type.GsonSerializer
import spark.Request
import spark.Response
import spark.Spark.*

/**
 * @author Clément Garbay
 */
class BasketResource(private val queryBus: QueryBus, private val commandBus: CommandBus) : Resource {

    override fun run() {
        path("/basket") {
            get("/:id", getBasket)
            post("/:id", addProduct)
            delete("/:id", deleteProduct)
        }
    }

    private val getBasket = { request: Request, response: Response ->
        val basketId = request.params("id")
        val getBasketResult = queryBus.dispatch(GetBasket(basketId))
        val responseBody = GsonSerializer.toJson(getBasketResult.result)

        response.header("Content-Type", "application/json")
        responseBody
    }

    private val addProduct = { request: Request, response: Response ->
        val basketId = request.params("id")
        val body = request.body()
        val product = GsonSerializer.fromJson<Product>(body)

        commandBus.dispatch(AddProduct(basketId, product.name, product.quantity))

        response.status(200)
        Unit
    }

    private val deleteProduct = { request: Request, response: Response ->
        val basketId = request.params("id")
        val body = request.body()
        val product = GsonSerializer.fromJson<Product>(body)

        commandBus.dispatch(DeleteProduct(basketId, product.name, product.quantity))

        response.status(200)
        Unit
    }

}


