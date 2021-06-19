package com.example.android.danmack.sample

data class PrimaryArtist(
    val api_path: String,
    val header_image_url: String,
    val id: Int,
    val image_url: String,
    val iq: Int,
    val is_meme_verified: Boolean,
    val is_verified: Boolean,
    val name: String,
    val url: String
)