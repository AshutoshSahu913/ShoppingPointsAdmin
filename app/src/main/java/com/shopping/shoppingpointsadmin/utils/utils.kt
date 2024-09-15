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

