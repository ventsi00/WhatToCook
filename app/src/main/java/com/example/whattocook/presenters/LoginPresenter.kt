package com.example.whattocook.presenters

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.whattocook.HomeActivity
import com.example.whattocook.presenters.`interface`.ILoginPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.security.AccessControlContext

class LoginPresenter():ILoginPresenter {
    override fun UpdateUI(user: FirebaseUser,context: Context) {
        val mIntent = Intent(context,HomeActivity::class.java)
        mIntent.putExtra("UID", user.uid)
        startActivity(context,mIntent,null)
    }

    override fun AutoLogin(auth: FirebaseAuth,context: Context) {
        if (auth.currentUser != null) {
            UpdateUI(auth.currentUser!!,context)
        }
    }
}