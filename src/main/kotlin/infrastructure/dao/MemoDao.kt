package infrastructure.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object MemoTable : IntIdTable("Memos") {
    val body = text("body")
}

class MemoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemoEntity>(MemoTable)
    var body by MemoTable.body
}