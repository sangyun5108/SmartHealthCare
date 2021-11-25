package com.sypark.smarthealthcare

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sypark.smarthealthcare.auth.AuthActivity

class mainCountFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_count,container,false)

        val progressbar = view.findViewById<ProgressBar>(R.id.progressbar)
        val button = view.findViewById<Button>(R.id.countButton)
        val percent = view.findViewById<TextView>(R.id.percent)
        val countNumber = view.findViewById<TextView>(R.id.countNumber)
        val logOutBtn = view.findViewById<Button>(R.id.logOutButton)

        var pStatus = progressbar.progress
        var count = 0

        button.setOnClickListener {
            kotlin.concurrent.timer(period = 7, initialDelay = 0) {
                activity?.runOnUiThread {
                    percent.text="${pStatus+1}%"
                }
                progressbar.setProgress(++pStatus)
                if(progressbar.progress==100){
                    cancel()
                    activity?.runOnUiThread {
                        countNumber.text="${++count}개"
                        percent.text="100%"
                    }
                    kotlin.concurrent.timer(period = 7, initialDelay = 0) {
                        progressbar.setProgress(pStatus--)
                        if(progressbar.progress==0){
                            cancel()
                        }
                    }
                }
            }
        }

        logOutBtn.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
        }

        return view

    }

}