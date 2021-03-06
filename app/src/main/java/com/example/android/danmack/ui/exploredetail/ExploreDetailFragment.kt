package com.example.android.danmack.ui.exploredetail

import android.content.*
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.android.danmack.MainActivity
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentExploreDetailBinding
import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.ui.explore.ExploreAdapter
import com.example.android.danmack.utils.playSongInBrowser
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Explore detail screen
 * */
@AndroidEntryPoint
class ExploreDetailFragment : Fragment() {

    private lateinit var ui : FragmentExploreDetailBinding

    private val viewModel : ExploreDetailViewModel by viewModels()



    private lateinit var trackSelected : Track

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_explore_detail, container, false)
        ui = FragmentExploreDetailBinding.inflate(inflater)



        coordinateMotion()

        ui.lifecycleOwner = this
        ui.exploreDetailViewModel = viewModel



       trackSelected = ExploreDetailFragmentArgs.fromBundle(requireArguments()).track

        ui.trackSelected = trackSelected

        val url = trackSelected.url

        ui.bottomSheetLayoutCancelButton.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)
        }

        ui.copyImg.setOnClickListener {
            Clip.clipReferral(url, requireActivity())
        }

        ui.bottomSheetLayoutPlayBtn.setOnClickListener {
            playSongInBrowser(url)
        }

        ui.shareImg.setOnClickListener {
            shareSongLinkIntent()
        }






       viewModel.getTrackSelected(trackSelected.key)

        val playListDrawable :  Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.playlist, null)



        ui.bottomSheetLayoutPlaylistBtn.setOnClickListener {

            if (playListDrawable != null) {
                DrawableCompat.setTint(playListDrawable, ContextCompat.getColor(requireContext(), R.color.accent_color))
            }
            Toast.makeText(requireContext(), "Play list added", Toast.LENGTH_SHORT).show()
            viewModel.idValue.observe(viewLifecycleOwner, Observer {
                viewModel.onPlayListClicked(it)
            })
        }





        Picasso.with(context).load(trackSelected.images.coverart).into(ui.artistCoverImg)
        Picasso.with(context).load(trackSelected.images.coverarthq).into(ui.artistImg)



        return ui.root
    }



    // share referral link with friends
    private fun shareSongLinkIntent () {
        val title = "Share link with friends and family"
        val linkToShare = trackSelected.url
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setText(linkToShare)
                .intent

        val chooser = Intent.createChooser(shareIntent, title)
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            //  Define what to happen if no activity can handle the intent.
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.theopen.android"))
            startActivity(intent)

        }

    }


    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout = ui.appbarLayout
        val motionLayout: MotionLayout = ui.motionLayout

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }

        appBarLayout.addOnOffsetChangedListener(listener)
    }


//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (activity as MainActivity).hideBottomNavigation()
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        (activity as MainActivity).showBottomNavigation()
//    }




}