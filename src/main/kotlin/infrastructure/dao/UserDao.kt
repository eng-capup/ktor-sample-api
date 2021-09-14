package infrastructure.dao

import org.jetbrains.exposed.dao.id.EntityID

object UserTable : StringIdTable("Users", "email", 255) {
    val password = varchar("password", 255)
}

class UserEntity(id: EntityID<String>) : StringEntity(id) {
    companion object : StringEntityClass<UserEntity>(UserTable)
    var password by UserTable.password
}