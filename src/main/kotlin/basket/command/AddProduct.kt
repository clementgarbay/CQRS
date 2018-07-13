package basket.command

import basket.domain.Basket
import core.command.Command

/**
 * @author Clément Garbay
 */
data class AddProduct(val basketId: String, val name: String, val quantity: Int) : Command<Basket>