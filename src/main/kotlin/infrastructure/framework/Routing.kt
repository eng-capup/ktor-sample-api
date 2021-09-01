package infrastructure.framework

import interfaces.controller.MemoController
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject
import utils.extensions.validateAndThrow
import javax.validation.Validator

fun Routing.root() {
    val validator: Validator by inject()

    route("/memos") {
        val memoController: MemoController by inject()

        post("/new") {
            val input = MemoController.MemoPostInput(call.receiveParameters().getOrFail("body"))
            validator.validateAndThrow(input)
            call.respond(transactionWrapper{ memoController.post(input) })
        }

        get("/{id}") {
            val input = MemoController.MemoInput(call.parameters.getOrFail<Int>("id"))
            call.respond(transactionWrapper { memoController.get(input) })
        }
    }
}