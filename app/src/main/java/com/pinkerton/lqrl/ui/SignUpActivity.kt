package com.pinkerton.lqrl.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.pinkerton.lqrl.databinding.ActivitySignupBinding
import com.pinkerton.lqrl.util.ApiUtils
import com.pinkerton.lqrl.util.CredentialsUtils
import com.pinkerton.lqrl.util.SharedPreferencesManager
import com.pinkertone.apiwrapper.Resource
import com.pinkertone.apiwrapper.Status
import com.pinkertone.apiwrapper.types.Token
import kotlinx.android.synthetic.main.activity_signup.view.*
import kotlinx.coroutines.runBlocking

class SignUpActivity : AppCompatActivity() {
    // region Binding
    private lateinit var binding: ActivitySignupBinding

    // endregion
    // region Views
    private lateinit var nicknameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var passwordConfirmInput: EditText
    private lateinit var signupButton: Button

    // endregion
    // region Data
    private var nickname: String = ""
    private var email: String = ""
    private var password: String = ""
    private var passwordConfirmation: String = ""
    // endregion

    private val apiUtils = ApiUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        // Init
        nicknameInput = root.nicknameInput
        emailInput = root.emailInput
        passwordInput = root.passwordInput
        passwordConfirmInput = root.passwordConfirmInput
        signupButton = root.loginButton

        // Setup
        nicknameInput.addTextChangedListener { text ->
            nickname = text.toString()
        }

        emailInput.addTextChangedListener { text ->
            email = text.toString()
        }

        passwordInput.addTextChangedListener { text ->
            password = text.toString()
        }

        passwordConfirmInput.addTextChangedListener { text ->
            passwordConfirmation = text.toString()
        }

        signupButton.setOnClickListener {
            if (validateCredentials()) {
                startMainActivity()
            }
        }
    }

    private fun validateCredentials(): Boolean {
        clearErrors()

        // TODO: constant error messages

        if (!CredentialsUtils.isValidNickname(nickname)) {
            nicknameInput.error = "Invalid Nickname!"
            nicknameInput.requestFocus()
            return false
        }

        if (!CredentialsUtils.isValidEmail(email)) {
            emailInput.error = "Invalid email!"
            emailInput.requestFocus()
            return false
        }

        if (!CredentialsUtils.isValidPassword(password)) {
            passwordInput.error = "Invalid password!"
            passwordInput.requestFocus()
            return false
        }

        if (password != passwordConfirmation) {
            passwordConfirmInput.error = "Passwords don't match!"
            passwordConfirmInput.requestFocus()
            return false
        }

        var value: Boolean = false

        // TODO: fix createUser
        runBlocking {
            val response: Resource<Token> = apiUtils.createUser(nickname, email, password)
            if (response.status == Status.SUCCESS) {
                val token = response.data?.string ?: "null"
                SharedPreferencesManager.CREDENTIALS.putToken(token)

                value = true
            } else if(response.status == Status.ERROR) {
                Log.d("API", response.message?: "No error message was provided!")
            }
        }

        return value
    }

    private fun clearErrors() {
        nicknameInput.error = null
        emailInput.error = null
        passwordInput.error = null
        passwordConfirmInput.error = null
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        this.finish()
    }
}