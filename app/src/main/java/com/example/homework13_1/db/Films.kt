package com.example.homework13_1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Films(
    @ColumnInfo(name = "film_title")
    val filmTitle: String?,
    @ColumnInfo(name = "film_vote_average")
    val filmVoteAverage: String?,
    @ColumnInfo(name = "film_overview")
    val filmOverview: String?,
 //   @ColumnInfo(name = "film_poster")
  //  val filmPoster: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}