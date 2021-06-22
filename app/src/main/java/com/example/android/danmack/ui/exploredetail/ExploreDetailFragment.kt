package com.example.android.danmack.ui.exploredetail

import android.content.*
import android.content.Context.CLIPBOARD_SERVICE
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat.getSystemService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentExploreDetailBinding
import com.squareup.picasso.Picasso


class ExploreDetailFragment : Fragment() {

    private lateinit var ui : FragmentExploreDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_explore_detail, container, false)
        ui = FragmentExploreDetailBinding.inflate(inflater)



        ui.lifecycleOwner = this



        val trackSelected = ExploreDetailFragmentArgs.fromBundle(requireArguments()).track

        ui.trackSelected = trackSelected

        val url = trackSelected.url

        ui.copyImg.setOnClickListener {
            Clip.clipReferral(url, requireActivity())
        }


        // share referral link with friends
//        private fun shareIntent () {
//            val title = "Share link with friends and family"
//            val linkToShare = url
//            val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
//                .setType("text/plain")
//                .setText(linkToShare)
//                .intent
//
//            val chooser = Intent.createChooser(shareIntent, title)
//            try {
//                startActivity(chooser)
//            } catch (e: ActivityNotFoundException) {
//                //  Define what to happen if no activity can handle the intent.
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.theopen.android"))
//                startActivity(intent)
//
//            }
//
//        }


        Picasso.with(context).load(trackSelected.images.coverart).into(ui.artistCoverImg)
        Picasso.with(context).load(trackSelected.images.coverarthq).into(ui.artistImg)



        return ui.root
    }




}