package com.example.labs1_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView

class ImageActivity : AppCompatActivity() {

    private lateinit var selectedImage: AppCompatImageView
    private lateinit var backButton: Button


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
        setContentView(R.layout.activity_image)

        selectedImage = findViewById(R.id.selectedImage)
        backButton =findViewById(R.id.backButton)

        val pickImgIntent: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImgIntent)

        backButton.setOnClickListener{
            finish()
        }
    }
}