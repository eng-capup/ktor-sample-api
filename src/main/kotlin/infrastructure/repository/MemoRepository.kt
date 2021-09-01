package infrastructure.repository

import domain.Memo
import infrastructure.dao.MemoEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import usecase.MemoService

interface IMemoRepository {
    fun create(body: String): Memo

    fun findById(id: Int): Memo?
}

class MemoRepository : IMemoRepository, KoinComponent {
    private val memoService: MemoService by inject()

    override fun create(body: String): Memo {
        val memoEntity: MemoEntity = MemoEntity.new {
            this.body = body
        }
        return convertToMemo(memoEntity)
    }

    override fun findById(id: Int): Memo? {
        val memoEntity: MemoEntity = MemoEntity.findById(id) ?: return null
        return convertToMemo(memoEntity)
    }


    private fun convertToMemo(memoEntity: MemoEntity): Memo {
        val memo = Memo.convertFrom(memoEntity)
        return memoService.attachKeywords(memo)
    }
}