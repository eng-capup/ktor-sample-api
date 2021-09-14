package interfaces.controller

import infrastructure.repository.IUserRepository
import io.ktor.features.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.constraints.Email
import javax.validation.constraints.Size

class UserController : KoinComponent {
    private val userRepository: IUserRepository by inject()
    private val logger: Logger = LoggerFactory.getLogger("UserController")

    data class UserInput(@field:Email
                         @field:Size(min = 1, max = 255)
                         val email: String,
                         @field:Size(min = 1, max = 255)
                         val password: String)

    data class UserOutput(val email: String)

    fun get(input: UserInput): UserOutput {
        logger.info("UserController.get called.")
        val userEntity = userRepository.findByIdAndPassword(input.email, input.password) ?: throw NotFoundException("User is not found")
        return UserOutput(userEntity.email)
    }

}