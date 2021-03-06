package com.example.whattocook.presenters.`interface`

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.security.AccessControlContext

interface ILoginPresenter {
    fun UpdateUI(user: FirebaseUser,context: Context)
    fun AutoLogin(auth: FirebaseAuth, context: Context)
}