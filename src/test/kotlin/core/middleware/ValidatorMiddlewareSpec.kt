package core.middleware

import core.command.FakeCommand
import core.infrastructure.type.isLeft
import core.infrastructure.type.isRight
import core.infrastructure.type.left
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldContain
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
            val validationResult = validator.validate(FakeCommand("Name"))

            it("should not throw an failure") {
                validationResult.isRight() shouldBe true
            }
        }

        on("validate an AddOrganization command with empty name") {
            val validationResult = validator.validate(FakeCommand(""))

            it("should throw an failure") {
                validationResult.isLeft() shouldBe true
                validationResult.isRight() shouldBe false
                validationResult.left()!!.messages shouldContain "must not be empty"
            }
        }
    }

})

