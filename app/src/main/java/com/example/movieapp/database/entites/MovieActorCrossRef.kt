package com.example.movieapp.database.entites

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "actorId"])
data class MovieActorCrossRef(
    val movieId: Long,
    val actorId: Long
)