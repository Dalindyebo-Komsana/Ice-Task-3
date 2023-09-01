package com.example.apitask

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // USER-DATA
        var planID = intent.getStringExtra("_PlanID")
        var name = intent.getStringExtra("_Name")
        var surname = intent.getStringExtra("_Surname")
        var amount = intent.getStringExtra("_Amount")

        // RENDER USER-DATA
        findViewById<TextView>(R.id.txtsPlanId).apply{
            text = "Plan ID: "+planID.toString()
        }
        findViewById<TextView>(R.id.txtsName).apply{
            text = "Name: "+name.toString()
        }
        findViewById<TextView>(R.id.txtsSurname).apply{
            text = "Surname: "+surname.toString()
        }
        findViewById<TextView>(R.id.txtsAmount).apply{
            text = "Amount: R "+amount.toString()
        }
    }
}