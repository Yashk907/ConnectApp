package com.example.Authentication.Security.Hash

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA256HashService : HashService {
    override fun generateSaltedHash(value: String, SaltLength: Int): SaltedHash? {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SaltLength)
        val hexSalt = Hex.encodeHexString(salt)
        val hash =DigestUtils.sha256Hex("$hexSalt$value")
        return SaltedHash(
            hash = hash,
            salt = hexSalt
        )
    }

    override fun verify(value: String, saltedHash: SaltedHash): Boolean {
        return DigestUtils.sha256Hex(saltedHash.salt+value)==saltedHash.hash
    }
}