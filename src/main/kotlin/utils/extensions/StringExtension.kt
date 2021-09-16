package utils.extensions

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigInteger
import java.security.MessageDigest

fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

fun String.encryptedPassword(): String {
    val md5Text = this.toMd5()
    val encoder = BCryptPasswordEncoder()
    return encoder.encode(md5Text)
}

fun String.authenticate(raw: String): Boolean {
    val encoder = BCryptPasswordEncoder()
    return encoder.matches(raw, this)
}