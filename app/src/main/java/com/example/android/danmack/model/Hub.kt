package com.example.android.danmack.model

data class Hub(
    val actions: List<Action>,
    val displayname: String,
    val explicit: Boolean,
    val image: String,
    val options: List<Option>,
    val providers: List<Provider>,
    val type: String
)