package com.example.labs1_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btnExplicit: Button
    private lateinit var btnImplicit: Button
    private lateinit var textView: TextView

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ res ->
        if(res.resultCode == Activity.RESULT_OK)
        {
            val data: Intent? = res.data
            textView.text = data?.getStringExtra("testVariable")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnExplicit = findViewById(R.id.explicitButton)
        btnImplicit = findViewById(R.id.implicitButton)
        textView = findViewById(R.id.mainText)

        btnExplicit.setOnClickListener{
            val intent: Intent = Intent(this, ExplicitActivity::class.java)
            startActivity(intent)
    }
        btnImplicit.setOnClickListener{_ ->
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { i -> i.putExtra("testVariable" ,"probvanje")
            //startActivity(Intent.createChooser(i, "Choose App"))
                resultLauncher.launch(i)
            }
        }


        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = packageManager.queryIntentActivities(mainIntent, 0)
        for (i in pkgAppsList.indices) {
            Log.d("Activity package", pkgAppsList[i].activityInfo.name)
        }
    }



}