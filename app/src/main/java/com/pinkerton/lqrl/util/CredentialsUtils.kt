package com.pinkerton.lqrl.util

class CredentialsUtils {
    companion object {
        // TODO: implement checks

        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty()
        }

        fun isValidPassword(password: String): Boolean {
            return password.isNotEmpty()
        }

        fun isValidNickname(nickname: String): Boolean {
            return nickname.isNotEmpty()
        }
    }
}