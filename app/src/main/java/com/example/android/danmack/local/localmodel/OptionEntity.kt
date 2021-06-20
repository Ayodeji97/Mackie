package com.example.android.danmack.local.localmodel

import com.example.android.danmack.model.ActionX
import com.example.android.danmack.model.Beacondata

data class OptionEntity (
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