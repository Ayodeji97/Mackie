package com.example.android.danmack.local.localmodel

import com.example.android.danmack.model.Action
import com.example.android.danmack.model.Option
import com.example.android.danmack.model.Provider

data class HubEntitty (
        val actions: List<Action>,
        val displayname: String,
        val explicit: Boolean,
        val image: String,
        val options: List<Option>,
        val providers: List<Provider>,
        val type: String
)