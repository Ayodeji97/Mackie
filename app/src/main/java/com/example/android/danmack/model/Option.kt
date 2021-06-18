package com.example.android.danmack.model

data class Option(
    val actions: List<ActionX>,
    val beacondata: Beacondata,
    val caption: String,
    val colouroverflowimage: Boolean,
    val image: String,
    val listcaption: String,
    val overflowimage: String,
    val providername: String,
    val type: String
)