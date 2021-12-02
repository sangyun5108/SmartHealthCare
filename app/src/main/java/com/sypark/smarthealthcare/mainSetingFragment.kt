package com.sypark.smarthealthcare

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sypark.smarthealthcare.auth.AuthActivity

class mainSetingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_seting,container,false)

        val logOutBtn = view.findViewById<Button>(R.id.logOutBtn)
        val closeBtn = view.findViewById<Button>(R.id.closeBtn)


        logOutBtn.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
        }

        closeBtn.setOnClickListener {
            getActivity()?.finishAffinity();
        }

        return view
    }
}