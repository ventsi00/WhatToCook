package com.example.whattocook

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.LoginPresenter
import com.example.whattocook.presenters.UiPresenter
import com.google.firebase.auth.FirebaseAuth

private var backPressCounter = 0

private lateinit var buttSignIn: Button
private lateinit var buttSignUp: Button
private lateinit var imageView: ImageView
private lateinit var auth: FirebaseAuth

private lateinit var loginPresenter: LoginPresenter
private val uiPresenter = UiPresenter()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!isInternetAvailable(this))
        {
            uiPresenter.changeUi(this,NoInternetActivity())
        }

        loginPresenter = LoginPresenter()
        buttSignIn = findViewById(R.id.buttonSignIn)
        buttSignUp = findViewById(R.id.buttonSignUp)
        imageView = findViewById(R.id.imageViewLogOrReg1)
        auth = FirebaseAuth.getInstance()

        buttSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        buttSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        backPressCounter++
        if (backPressCounter == 1)
        {
           Toast.makeText(this,R.string.LeaveWarning,Toast.LENGTH_LONG).show()
        }
        else
        {
            backPressCounter = 0
            finishAffinity()
        }
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.AutoLogin(auth,this)
    }

}

private fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
    }

    return result
}