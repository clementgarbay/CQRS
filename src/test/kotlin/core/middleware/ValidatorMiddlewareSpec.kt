package core.middleware

import core.command.FakeCommand
import core.exception.ValidationException
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import javax.validation.Validation

/**
 * @author Clément Garbay
 */
@RunWith(JUnitPlatform::class)
object ValidatorMiddlewareSpec: Spek({

    given("a validator middleware") {
        val factory = Validation.buildDefaultValidatorFactory()
        val validator = ValidatorMiddleware(factory.validator)

        on("validate an AddOrganization command") {
            val invoke = { validator.validate(FakeCommand("Name")) }

            it("should not throw an exception") {
                invoke shouldNotThrow ValidationException::class
            }
        }

        on("validate an AddOrganization command with empty name") {
            val invoke = { validator.validate(FakeCommand("")) }

            it("should throw an exception") {
                invoke shouldThrow ValidationException::class
            }
        }
    }

})

