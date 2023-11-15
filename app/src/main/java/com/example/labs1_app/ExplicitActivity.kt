package com.example.labs1_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ExplicitActivity : AppCompatActivity() {

    private lateinit var buttonConfirm: Button
    private lateinit var buttonBack: Button
    private lateinit var textView: TextView
    private lateinit var userInput: String
    private lateinit var userChoice: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        buttonConfirm = findViewById(R.id.buttonConfirm)
        buttonBack = findViewById(R.id.buttonBack)
        textView = findViewById(R.id.textView)
        userChoice = findViewById(R.id.editTextChoice)
//        textView.addTextChangedListener { newText ->
//            textViewResult.text = viewModel.winner(textViewComputerChoice.text.toString())
//        }


        buttonConfirm.setOnClickListener{
            v ->
            userInput = userChoice.text.toString()
            if(userInput =="")
            {
                Toast.makeText(this,"Please enter a value in the text input",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Intent().let { i ->
                    i.putExtra("returnMessage",userInput)
                    setResult(RESULT_OK,i)
                    finish()
                }
            }

        }

        buttonBack.setOnClickListener{
            v ->
            Intent().let { i ->
                setResult(RESULT_CANCELED,i)
                finish()
            }
        }
    }
}