package infrastructure.repository

import domain.User
import infrastructure.dao.UserEntity
import infrastructure.dao.UserTable
import org.jetbrains.exposed.sql.and

interface IUserRepository {
    fun findByIdAndPassword(email: String, password: String): User?
}

class UserRepository : IUserRepository {
    override fun findByIdAndPassword(email: String, password: String): User? {
        val userEntity: UserEntity = UserEntity.find{
            (UserTable.id eq email).and(UserTable.password eq password)
        }.firstOrNull() ?: return null
        return User(userEntity.id.value, userEntity.password)
    }
}