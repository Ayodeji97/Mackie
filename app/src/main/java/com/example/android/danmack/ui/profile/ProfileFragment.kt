package com.example.android.danmack.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var ui : FragmentProfileBinding
    private val REQUEST_IMAGE_CAPTURE = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_profile, container, false)

        ui = FragmentProfileBinding.inflate(inflater)


        ui.imageView.setOnClickListener {
            takePictureIntent()
        }

        return ui.root
    }


    /**
     * Capture and Image
     * */
    private fun takePictureIntent () {
        // Capture image with the help of this intent
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {pictureIntent->



        }

    }

}