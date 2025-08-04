package com.github.v2ray.ng.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.github.v2ray.ng.R

class ApiKeyActivity : AppCompatActivity() {

    private lateinit var editApiKey: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_key)

        editApiKey = findViewById(R.id.edit_api_key)
        btnSave = findViewById(R.id.btn_save_api_key)

        val sharedPref = getSharedPreferences("v2ray_settings", Context.MODE_PRIVATE)
        editApiKey.setText(sharedPref.getString("api_key", ""))

        btnSave.setOnClickListener {
            sharedPref.edit().putString("api_key", editApiKey.text.toString()).apply()
            finish() // close activity after saving
        }
    }
}
