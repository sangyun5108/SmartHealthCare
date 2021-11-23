package com.sypark.smarthealthcare.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.sypark.smarthealthcare.R

class AuthFragmen : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_auth, container, false)


        //화면 전환시 보여줄 프래그먼트 리스트 설정
        val fragmentList = arrayListOf<Fragment>(
            AuthFirstFragment(),
            AuthSecondFragment(),
            AuthThirdFragment()
        )

        //adapter 설정 넘겨야 하는 인수 -> 리스트, 프래그먼트매니저, 라이프사이클
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle,
            view
        )


        //viewPager를 찾아 adaper 연결
        val ViewPager2 = view.findViewById<ViewPager2>(R.id.AuthViewPager2)
        ViewPager2.adapter = adapter

        return view
    }
}