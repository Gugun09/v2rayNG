package com.github.v2ray.ng.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.v2ray.ang.R

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
            val inputKey = editApiKey.text.toString().trim()

            if (inputKey.isEmpty()) {
                Toast.makeText(this, "API Key tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sharedPref.edit().putString("api_key", inputKey).apply()
            Toast.makeText(this, "API Key berhasil disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
