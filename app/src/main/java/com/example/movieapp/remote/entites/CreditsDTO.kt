package com.example.movieapp.remote.entites

data class CreditsDTO(
    val cast: List<CastDTO>,
    val crew: List<CrewDTO>
)