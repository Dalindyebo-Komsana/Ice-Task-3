package com.example.apitask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NAVIGATE TO ADD NEW USER
        var btnEnterUser = findViewById(R.id.btnEnterUser) as Button
        btnEnterUser.setOnClickListener()
        {
            val intent = Intent(this, UserAddActivity::class.java)
            startActivity(intent)
        }

        // RECYCLERVIEW SETUP
        newRecyclerView = findViewById(R.id.recyclerEntries)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        // FETCH DATA
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val url = URL("https://opsc.azurewebsites.net/Dis.php")
            val json = url.readText()
            val userList = Gson().fromJson(json, Array<User>::class.java).toList()

            Handler(Looper.getMainLooper()).post {
                // Log.d("Output", userList.toString())
                var adapter = UserAdapter(userList)
                newRecyclerView.adapter = adapter
                // PAGE NAVIGATE
                adapter.setOnClickListener(object : UserAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val i = Intent(this@MainActivity, UserActivity::class.java)
                        i.putExtra("_PlanID", userList[position].PlanID)
                        i.putExtra("_Name", userList[position].Name)
                        i.putExtra("_Surname", userList[position].Surname)
                        i.putExtra("_Amount", userList[position].Amount)
                        startActivity(i)
                    }
                })
            }
        }
    }
}