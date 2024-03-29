package com.example.practicleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initWidget()
    }

    private fun initWidget() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, AddRecordActivity::class.java))
            finish()
        }, 3000)
    }
}