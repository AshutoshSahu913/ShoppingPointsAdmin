package com.shopping.shoppingpointsadmin.utils

import android.util.Base64
import com.shopping.shoppingpointsadmin.common.containts.SECRET_KEY
import java.security.Key
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

// Helper function for password strength check
fun isPasswordStrong(password: String): Boolean {
    val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()
    return password.matches(passwordRegex)
}

fun checkPasswordStrength(password: String): String? {
    if (password.length < 8) {
        return "Password must be at least 8 characters long"
    }
    if (!password.any { it.isUpperCase() }) {
        return "Password must contain at least one uppercase letter"
    }
    if (!password.any { it.isLowerCase() }) {
        return "Password must contain at least one lowercase letter"
    }
    if (!password.any { it.isDigit() }) {
        return "Password must contain at least one digit"
    }
    if (!password.any { "!@#\$%^&*()_+=<>?/".contains(it) }) {
        return "Password must contain at least one special character"
    }
    return null
}

// Helper function for encrypting the password
fun encryptPassword(password: String): String {
     // You should store this securely!
    val key = generateSecretKey(SECRET_KEY)

    val cipher = Cipher.getInstance("AES")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val encryptedValue = cipher.doFinal(password.toByteArray())
    return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
}

// Generate a key using the secret key
fun generateSecretKey(secret: String): Key {
    val keyBytes = secret.toByteArray(Charsets.UTF_8)
    val sha = MessageDigest.getInstance("SHA-256")
    val digest = sha.digest(keyBytes).copyOf(16)
    return SecretKeySpec(digest, "AES")
}


sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val exception: String) : ResultState<Nothing>()
    object  Loading : ResultState<Nothing>()
}


