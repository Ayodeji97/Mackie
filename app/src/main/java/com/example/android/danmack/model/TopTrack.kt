package com.example.android.danmack.model

data class TopTrack (
    val tracks : List<TopTrackData>
) {

    data class TopTrackData (

        val layout : String,
        val type : String,
        val key : String,
        val title : String,
        val subtitle : String,
        val share : ShareData,
        val images : ImageData,
        val hub : HubData,
        val artists : List<ArtistsData>,
        val url : String
    ) {

        data class ShareData (

            val subject : String,
            val text : String,
            val href : String,
            val image : String,
            val twitter : String,
            val html : String,
            val avatar : String,
            val snapchat : String,
        )


        data class ImageData (
            val background : String,
            val coverart : String,
            val coverarthq : String,
            val joecolor : String
        )

        data class HubData (

            val type : String,
            val image : String,
            val options : List<OptionsData>,
            val actions : List<ActionsData>,
            val explicit : Boolean,
            val displayname : String,
            val providers : List<ProvidersData>

        ) {

            data class ActionsData (
                val name : String?,
                val type : String?,
                val uri : String?,
                val id : String?

            )

            data class OptionsData (

                val captions : String,
                val beacondata : BeacondataData,
                val actions : List<OptionsActionsData>,
                val image : String,
                val type : String,
                val listcaption : String,
                val overflowimage : String,
                val colouroverflowimage : Boolean,
                val providername : String

            ) {

                data class OptionsActionsData (

                    val name : String,
                    val type : String,
                    val uri : String
                )

                data class BeacondataData (
                    val type : String,
                    val providername : String
                )
            }


            data class ProvidersData (

                val caption : String,
                val images : ProvidersImagesData,
                val actions : List<ProvidersActionsData>,
                val type : String
            ) {

                data class ProvidersImagesData (
                    val overflow : String,
                    val default : String
                )

                data class ProvidersActionsData (

                    val name : String,
                    val type : String,
                    val uri : String
                )
            }

        }

        data class ArtistsData (
            val id : String,
            val adamid : String
        )
    }


}