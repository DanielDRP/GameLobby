package com.example.jp1.model

data class Games(
    val id:Int,
    val title:String,
    val worth:String,
    val thumbnail:String,
    val image:String,
    val description:String,
    val instructions:String,
    val open_giveaway_url:String,
    val published_date:String,
    val type:String,
    val platforms:String,
    val end_date:String,
    val users:Int,
    val status:String,
    val gamerpower_url:String,
    val open_giveaway:String) {
}