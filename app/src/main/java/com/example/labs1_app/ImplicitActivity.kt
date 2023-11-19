package com.example.labs1_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ImplicitActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)


        textView = findViewById(R.id.textView)
        backButton = findViewById(R.id.backButton)

        var bundle: Bundle? = intent.extras
        textView.text = bundle?.getString("activities")


        backButton.setOnClickListener{
            v ->
            Intent().let { i ->
                setResult(RESULT_OK, i)
                finish()
            }
        }

    }
}