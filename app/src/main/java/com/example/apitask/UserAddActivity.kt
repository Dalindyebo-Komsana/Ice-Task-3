package com.example.apitask

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject

class UserAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_add)

        val Id = findViewById<EditText>(R.id.addID)
        val Amount = findViewById<EditText>(R.id.addAmount)
        val PracticeId = findViewById<EditText>(R.id.addPracticeID)
        val Display = findViewById<TextView>(R.id.txtDisplayData)


        // ON-SUBMIT
        var btnSubmit = findViewById(R.id.btnSubmit) as Button
        btnSubmit.setOnClickListener()
        {
            val client = OkHttpClient()
            // prepare to send data
            val payload = JSONObject().apply {
                put("ID", Id.text)
                put("Amount", Amount.text)
                put("PracticeID", PracticeId.text)
            }
            // prepare rest-api post
            val requestBody = payload.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val request = Request.Builder()
                .url("https://opsc.azurewebsites.net/Add.php")
                .method("POST", requestBody)
                .build()

            // get response message
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        Display.setText(responseBody)
                    } else {
                        Log.d("OUTPUT: ", "Error")
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("OUTPUT: ", e.message.toString())
                }
            })
        }
    }
}