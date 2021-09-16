package infrastructure.framework

import infrastructure.repository.IMemoRepository
import infrastructure.repository.IUserRepository
import infrastructure.repository.MemoRepository
import infrastructure.repository.UserRepository
import interfaces.controller.MemoController
import interfaces.controller.UserController
import org.koin.dsl.module
import usecase.MemoService
import javax.validation.Validation
import javax.validation.Validator

val koinModules = module {
    single { MemoController() }
    single { UserController() }
    single { MemoService() }
    factory<IMemoRepository> { MemoRepository() }
    factory<IUserRepository> { UserRepository() }
    factory<Validator> { Validation.buildDefaultValidatorFactory().validator }
}