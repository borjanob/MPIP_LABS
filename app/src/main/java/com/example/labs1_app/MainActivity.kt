package com.example.labs1_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnExplicit: Button
    private lateinit var btnImplicit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnExplicit = findViewById(R.id.explicitButton)
        btnImplicit = findViewById(R.id.implicitButton)

        btnExplicit.setOnClickListener{
            val intent: Intent = Intent(this, ExplicitActivity::class.java)
            startActivity(intent)
    }
        btnImplicit.setOnClickListener{_ ->
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { i -> i.putExtra("testVariable" ,"TEST")
            startActivity(Intent.createChooser(i, "Choose App"))
            }
        }

    }



}