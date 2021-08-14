package com.example.homework13_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import com.example.homework13_1.JSON.FilmsList
//import com.example.homework13_1.JSON.FilmsList
//import com.example.homework13_1.JSON.Movie
import com.example.homework13_1.Retrofit2.RetrofitServieces
import com.example.homework13_1.Retrofit2.RetrofitClient
import com.example.homework13_1.db.AppBase
import com.example.homework13_1.db.FilmDataO
import com.example.homework13_1.db.Films
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

  //  lateinit var filmData: FilmDataO
    private var newList: ArrayList<Films> = arrayListOf<Films>()
    private var launcher: ActivityResultLauncher<Intent>? = null
 //   lateinit var filmsList: ArrayList<Films>
    var tempFilms: ArrayList<Films> = arrayListOf<Films>()
    lateinit var mService:RetrofitServieces

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleViewID)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, 1))
        val searchV = findViewById<SearchView>(R.id.searchViewID)

        Log.i("1233","ffffffffffffffaaaaaaaaaaa")

   /*      val db = Room.databaseBuilder(
                applicationContext,
                AppBase::class.java, "my-database"
        ).build()
        filmData = db.filmDataO()


       lifecycleScope.launch(Dispatchers.IO) {

            filmsList = filmData.getAll() as ArrayList<Films>

            if (filmsList.isNullOrEmpty()) {
                filmData.insertAll(ItemsDB[0], ItemsDB[1], ItemsDB[2], ItemsDB[3], ItemsDB[4],
                        ItemsDB[5], ItemsDB[6], ItemsDB[7], ItemsDB[8], ItemsDB[9])
                filmsList = filmData.getAll() as ArrayList<Films>
            }
            withContext(Dispatchers.Main)
            {
                recyclerView.adapter = RecyclerAdapter(filmsList)
            }
        }          */

        val request = RetrofitClient.buildService(RetrofitServieces::class.java)
        Log.i("1233","fffffffffffff")
        val call = request.getMovies("popularity.desc","8123971648da4ff0ca36b9fed8f72ebd","2020","ru")

        call.enqueue(object : Callback<FilmsList>{

            override fun onResponse(call: Call<FilmsList>, response: Response<FilmsList>) {
                if (response.isSuccessful){
                    Log.i("1233","ffffffffffffffaaaaaaaaaaa")
                    Log.i("1233", response.body()?.movies?.get(0)?.title.toString())
                    tempFilms.clear()

                    (0..(response.body()?.movies?.lastIndex!!))
                        .forEach { n ->
                            tempFilms.add(Films(response.body()?.movies?.get(n)?.title.toString(),
                                response.body()?.movies?.get(n)?.overview.toString(),
                                response.body()?.movies?.get(n)?.voteAverage.toString()))
                        }
                    recyclerView.adapter = RecyclerAdapter(tempFilms)
                }
            }
            override fun onFailure(call: Call<FilmsList>, t: Throwable) {
                Log.i("1233","aaaaaaaaaaa")
            }
        })

    }
}


public class RecyclerAdapter(private val items: ArrayList<Films>) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val filmTitleView: TextView = view.findViewById(R.id.textTitle)
        val filmVoteAverageView: TextView = view.findViewById(R.id.textVoteAverage)
        val filmOverviewView: TextView = view.findViewById(R.id.textOverView)
  //      val filmPosterView: ImageView = view.findViewById(R.id.posterImageView)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycle_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

      //  val currentItem = items[position]
        /*Glide.with(viewHolder.filmPosterView.context)
            .load(items[position].filmPoster)
            .override(50, 50)
            .into(viewHolder.filmPosterView)  */

        items[position].apply {
            viewHolder.filmTitleView.text = filmTitle
            viewHolder.filmVoteAverageView.text = filmVoteAverage
            viewHolder.filmOverviewView.text = filmOverview




        }
    }

}
