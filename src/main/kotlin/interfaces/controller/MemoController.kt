package interfaces.controller

import domain.Memo
import infrastructure.repository.IMemoRepository
import io.ktor.features.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.constraints.Size

class MemoController : KoinComponent {
    private val memoRepository: IMemoRepository by inject()

    private val logger: Logger = LoggerFactory.getLogger("MemoController")

    data class MemoInput(val id: Int)
    data class MemoPostInput(@field:Size(max = 20) val body: String)

    data class MemoOutput(val id: Int, val body: String, val keywords: List<String>)

    fun get(input: MemoInput): MemoOutput {
        logger.info("MemoController.get called.")
        val memoEntity = memoRepository.findById(input.id) ?: throw NotFoundException("memo is not found.")
        return MemoOutput(memoEntity.id, memoEntity.body, memoEntity.keywords)
    }

    fun post(input: MemoPostInput): MemoOutput {
        logger.info("MemoController.post called.")
        val memo: Memo = memoRepository.create(input.body)
        return MemoOutput(memo.id, memo.body, memo.keywords)
    }
}