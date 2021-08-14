package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplication.HTTP.RetrofitClient
import com.example.myapplication.HTTP.RetrofitServieces
import com.example.myapplication.Json.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mService:RetrofitServieces

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

val TV = findViewById<TextView>(R.id.idTextView)

        val request = RetrofitClient.buildService(RetrofitServieces::class.java)
        Log.i("1233","fffffffffffff")
        val call = request.getMovies("popularity.desc","8123971648da4ff0ca36b9fed8f72ebd","2020","ru")

        call.enqueue(object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful){
                    Log.i("1233","ffffffffffffffaaaaaaaaaaa")
                    Log.i("1233","${response.body()}")
                    TV.text = response.body()?.results?.get(19)?.overview.toString()
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("1233","aaaaaaaaaaa")
            }
        })

    }
}