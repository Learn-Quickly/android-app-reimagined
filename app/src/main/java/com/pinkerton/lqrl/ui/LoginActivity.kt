package com.pinkerton.lqrl.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.pinkerton.lqrl.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {
    // region Binding
    private lateinit var binding: ActivityLoginBinding

    // endregion
    // region Views
    private lateinit var nicknameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var signupTextButton: TextView

    // endregion
    // region Data
    private var nickname: String = ""
    private var password: String = ""
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        // Init
        nicknameInput = root.nicknameInput
        passwordInput = root.passwordInput
        loginButton = root.loginButton
        signupTextButton = root.signupTextButton

        // Setup
        nicknameInput.addTextChangedListener { text ->
            nickname = text.toString()
        }

        passwordInput.addTextChangedListener { text ->
            password = text.toString()
        }

        loginButton.setOnClickListener {
            if (validateCredentials()) {
                startMainActivity()
            }
        }

        signupTextButton. setOnClickListener {
            startSignUpActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        this.finish()
    }

    private fun validateCredentials(): Boolean {
        // TODO: implement API checks

        return true
    }

    private fun startSignUpActivity() {
        val intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
    }

}