package infrastructure.framework

import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

fun <T> transactionWrapper(statement: Transaction.() -> T): T = transaction {
    try {
        statement()
    } catch (e: Exception) {
        rollback()
        throw e
    }
}