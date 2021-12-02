package com.sypark.smarthealthcare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class Calibration : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_calibration, container, false)

        val pinLoad = view.findViewById<Button>(R.id.pinLoad)
        val plateLoad = view.findViewById<Button>(R.id.plateLoad)

        pinLoad.setOnClickListener {
            Toast.makeText(requireContext(),"핀로드 머신 조정을 하겠습니다",Toast.LENGTH_SHORT).show()
        }

        plateLoad.setOnClickListener {
            Toast.makeText(requireContext(),"플레이트 로드 머신 조정을 하겠습니다",Toast.LENGTH_SHORT).show()
        }

        return view
    }

}