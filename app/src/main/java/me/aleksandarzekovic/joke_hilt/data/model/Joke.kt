package me.aleksandarzekovic.joke_hilt.data.model

import com.google.gson.annotations.SerializedName

data class Joke (

    @SerializedName("error") val error : Boolean,
    @SerializedName("category") val category : String,
    @SerializedName("type") val type : String,
    @SerializedName("setup") val setup : String = "",
    @SerializedName("delivery") val delivery : String = "",
    @SerializedName("flags") val flags : Flags,
    @SerializedName("id") val id : Int,
    @SerializedName("lang") val lang : String,
    @SerializedName("joke") val joke : String = ""
)