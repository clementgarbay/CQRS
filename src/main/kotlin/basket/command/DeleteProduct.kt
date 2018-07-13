package basket.command

import core.command.Command
import basket.domain.Basket

/**
 * @author Clément Garbay
 */
data class DeleteProduct(val basketId: String, val name: String, val quantity: Int) : Command<Basket>