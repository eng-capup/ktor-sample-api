package infrastructure.dao

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

open class StringIdTable(name: String = "", columnName: String = "id", length: Int, defaultValueFun: (() -> String)? = null) : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = if (defaultValueFun != null) {
        varchar(columnName, length).clientDefault( defaultValueFun).uniqueIndex().entityId()
    } else {
        varchar(columnName, length).uniqueIndex().entityId()
    }
    override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }
}
