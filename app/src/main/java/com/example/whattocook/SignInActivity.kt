package com.example.whattocook

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.AuthPresenter
import com.example.whattocook.presenters.LoginPresenter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private lateinit var auth: FirebaseAuth

private lateinit var imageView: ImageView
private lateinit var email: EditText
private lateinit var password:EditText
private lateinit var buttLogin: Button
private lateinit var authPresenter:AuthPresenter

lateinit var UID:String

class SignInActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        authPresenter = AuthPresenter()
        auth = FirebaseAuth.getInstance()
        imageView = findViewById(R.id.imageViewSignIn)
        email = findViewById(R.id.editTextEmail_In)
        password = findViewById(R.id.editTextPassword_In)
        buttLogin = findViewById(R.id.buttonLogin)
        buttLogin.setOnClickListener {
            if (email.text.isEmpty())
            {
                Toast.makeText(this, "No email entered", Toast.LENGTH_LONG).show()
            }
            else if(password.text.isEmpty())
            {
                Toast.makeText(this, "No password entered", Toast.LENGTH_LONG).show()
            }
            else
            {
                authPresenter.SignIn(this, auth,email.text.toString(), password.text.toString())
            }
        }
    }
}