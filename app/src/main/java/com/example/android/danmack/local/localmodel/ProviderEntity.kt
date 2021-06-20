package com.example.android.danmack.local.localmodel

import com.example.android.danmack.model.ActionXX
import com.example.android.danmack.model.Images

data class ProviderEntity (
        val actions: List<ActionXX>,
        val caption: String,
        val images: Images,
        val type: String
)