package com.example.ar_tistic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ar_tistic.MainActivity.Companion.manager
import com.example.classlib.Manager
import com.example.classlib.User
import com.example.clientapi.ClientAPI
import kotlinx.coroutines.*

class SearchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_users)
        createButtons()
    }
    fun createButtons(){
        val back = findViewById<ImageButton>(R.id.returnMapPage)
        back.setOnClickListener {
            finish()
        }
        val submitSearch = findViewById<ImageButton>(R.id.searchSubmit)
        submitSearch.setOnClickListener{

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(findViewById<EditText>(R.id.searchInput).windowToken, 0)

            val recycler = findViewById<RecyclerView>(R.id.recyclerViewSearch)
            recycler.layoutManager = LinearLayoutManager(this)

            GlobalScope.launch {
                if(findViewById<EditText>(R.id.searchInput).text.isEmpty()){
                    val data = getAllUsers().await()
                    runOnUiThread {
                        val adapter = UserAdapter(data)
                        recycler.adapter = adapter
                        findViewById<TextView>(R.id.loading).visibility = View.INVISIBLE
                    }
                }
                else{
                    val data = searchUsers(findViewById<EditText>(R.id.searchInput).text.toString()).await()
                    runOnUiThread {
                        val adapter = UserAdapter(data)
                        recycler.adapter = adapter
                        findViewById<TextView>(R.id.loading).visibility = View.INVISIBLE
                    }
                }

            }

        }

    }

    suspend fun searchUsers(pattern:String)= GlobalScope.async{
        runOnUiThread{
            findViewById<TextView>(R.id.loading).visibility = View.VISIBLE
        }
        val data = ArrayList<User>()
        var users = manager.persistence.patternRecognitionUsers(pattern)
        if (users != null) {
            for(user in users){
                println(user.email)
                data.add(user)
            }
        }
        return@async data
    }

    suspend fun getAllUsers()=GlobalScope.async{
        runOnUiThread{
            findViewById<TextView>(R.id.loading).visibility = View.VISIBLE
        }
        val data = ArrayList<User>()
        var users = manager.persistence.getAllUsers()
        if (users != null) {
            for(user in users){
                println(user.email)
                data.add(user)
            }
        }
        return@async data
    }
}
