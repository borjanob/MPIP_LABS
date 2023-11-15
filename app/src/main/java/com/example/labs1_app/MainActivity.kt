package com.example.labs1_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var btnExplicit: Button
    private lateinit var btnImplicit: Button
    private lateinit var textView: TextView
    private lateinit var imageButton: Button

//    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ res ->
//        if(res.resultCode == Activity.RESULT_OK)
//        {
//            //val data: Intent? = res.data
//            //textView.text = data?.getStringExtra("testVariable")
//        }
//    }

    var explicitResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == Activity.RESULT_OK)
        {
            val data: Intent? = result.data
            textView.text = data?.getStringExtra("returnMessage")
        }
        if(result.resultCode == Activity.RESULT_CANCELED)
        {}
    }

    private lateinit var selectedImage: AppCompatImageView

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                selectedImage.setImageURI(imgUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnExplicit = findViewById(R.id.explicitButton)
        btnImplicit = findViewById(R.id.implicitButton)
        textView = findViewById(R.id.mainText)
        imageButton = findViewById(R.id.imageButton)
        //selectedImage = findViewById(R.id.selected_image)


        btnExplicit.setOnClickListener{
            val intent: Intent = Intent(this, ExplicitActivity::class.java)
            explicitResult.launch(intent)
    }


        var sb = StringBuilder()

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = packageManager.queryIntentActivities(mainIntent, 0)
        for (i in pkgAppsList.indices) {
            sb.append(pkgAppsList[i].activityInfo.name + '\n')
            //Log.d("Activity package", pkgAppsList[i].activityInfo.name)
        }

        btnImplicit.setOnClickListener{_ ->
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                //type = "text/plain"
            }.let { i -> i.putExtra("activities" ,sb.toString())
            startActivity(i)
                //resultLauncher.launch(i)
            }
        }

        imageButton.setOnClickListener{
//            val pickImgIntent:Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//            startActivity(pickImgIntent)
            val pickImgIntent:Intent = Intent(this, ImageActivity::class.java)
            startActivity(pickImgIntent)
        }
    }

}