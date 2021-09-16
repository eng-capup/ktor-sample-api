package infrastructure.framework

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.AuthResponse
import interfaces.controller.MemoController
import interfaces.controller.UserController
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.config.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject
import utils.extensions.validateAndThrow
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.validation.Validator

fun Routing.root(config: ApplicationConfig) {
    val validator: Validator by inject()

    route("/user") {
        val userController: UserController by inject()
        post("/auth") {
            call.receive<UserController.UserInput>().also { input ->
                validator.validateAndThrow(input)
                call.respond(transactionWrapper {
                    val user = userController.get(input)

                    AuthResponse(
                        JWT.create()
                            .withAudience(config.property("jwt.audience").getString())
                            .withExpiresAt(Date(System.currentTimeMillis() + 600000))
                            .withClaim("email", user.email)
                            .withIssuer(config.property("jwt.issuer").getString())
                            .sign(Algorithm.HMAC256(config.property("jwt.secret").getString()))
                    )
                })
            }
        }
        authenticate("auth-jwt") {
            get("/hello") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal?.payload?.getClaim("email")?.toString()
                val expiresAt = principal?.expiresAt?.time?.minus(System.currentTimeMillis())
                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
            }
        }
    }

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