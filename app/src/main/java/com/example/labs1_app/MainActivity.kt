package com.example.labs1_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnExplicit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnExplicit = findViewById(R.id.explicitButton)

        btnExplicit.setOnClickListener{
            val intent: Intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
    }
    }



}