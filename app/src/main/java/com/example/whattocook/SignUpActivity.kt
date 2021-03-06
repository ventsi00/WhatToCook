package com.example.whattocook

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.AuthPresenter
import com.example.whattocook.presenters.LoginPresenter
import com.google.firebase.auth.FirebaseAuth


private lateinit var auth: FirebaseAuth

private lateinit var username:EditText
private lateinit var password:EditText
private lateinit var email:EditText
private lateinit var buttRegister:Button
private lateinit var imageView: ImageView
private lateinit var authPresenter:AuthPresenter

class SignUpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        authPresenter = AuthPresenter()
        auth = FirebaseAuth.getInstance()
        username = findViewById(R.id.editTextUsername_Up)
        password= findViewById(R.id.editTextPassword_Up)
        email= findViewById(R.id.editTextEmail_Up)
        imageView = findViewById(R.id.imageViewSignUp)
        buttRegister= findViewById(R.id.buttonRegister)
        buttRegister.setOnClickListener {
            if (username.text.isEmpty())
            {
                Toast.makeText(this,"No username entered",Toast.LENGTH_LONG).show()
            }
            else if (email.text.isEmpty())
            {
                Toast.makeText(this,"No email entered",Toast.LENGTH_LONG).show()
            }
            else if(password.text.isEmpty())
            {
                Toast.makeText(this,"No password entered",Toast.LENGTH_LONG).show()
            }
            else {
                authPresenter.CreateUser(this,auth,email.text.toString(), password.text.toString(),
                    username.text.toString())
            }
        }

    }
}