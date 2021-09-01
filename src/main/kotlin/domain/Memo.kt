package domain

import infrastructure.dao.MemoEntity

data class Memo(val id: Int, val body: String, val keywords: List<String> = listOf()) {
    companion object {
        fun convertFrom(memoEntity: MemoEntity): Memo = Memo(memoEntity.id.value, memoEntity.body)
    }
}