package infrastructure.framework

import infrastructure.repository.IMemoRepository
import infrastructure.repository.MemoRepository
import interfaces.controller.MemoController
import org.koin.dsl.module
import usecase.MemoService
import javax.validation.Validation
import javax.validation.Validator

val koinModules = module {
    single { MemoController() }
    single { MemoService() }
    factory<IMemoRepository> { MemoRepository() }
    factory<Validator> { Validation.buildDefaultValidatorFactory().validator }
}