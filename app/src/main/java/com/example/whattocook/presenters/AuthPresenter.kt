package com.example.whattocook.presenters

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.`interface`.IAuthPresenter
import com.example.whattocook.repository.DataBase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthPresenter:IAuthPresenter {
    private var loginPresenter:LoginPresenter = LoginPresenter()
    private var db = DataBase()
    override fun CreateUser(activity:AppCompatActivity,  auth: FirebaseAuth, email: String, password: String,name:String) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(activity){ task ->
                    if (task.isSuccessful) {

                        Log.d("TAG", "createUserWithEmail:success")
                        val user = auth.currentUser
                        loginPresenter.UpdateUI(user!!,activity)
                        db.createUser(user.uid,name)
                    } else {

                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(activity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }

                }

    }

    override fun SignIn(activity: AppCompatActivity, auth: FirebaseAuth, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity,
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success")
                                val user: FirebaseUser = auth.currentUser!!
                                loginPresenter.UpdateUI(user,activity)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                        activity,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT
                                ).show()
                                return@OnCompleteListener
                            }
                        })
    }
}