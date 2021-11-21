package com.sypark.smarthealthcare.auth

import android.view.View
import android.widget.ImageView
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.sypark.smarthealthcare.R

class ViewPagerAdapter(
    list:ArrayList<Fragment>,
    fm:FragmentManager,
    lifecycle:Lifecycle,
    val view: View
):FragmentStateAdapter(fm,lifecycle) {

    val fragmentlist:ArrayList<Fragment> = list

    override fun getItemCount(): Int {
        return fragmentlist.size
    }

    override fun createFragment(position: Int): Fragment {

        val viewPager2 = view.findViewById<ViewPager2>(R.id.AuthViewPager2)

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val firstCircle = view.findViewById<ImageView>(R.id.firstCircle)
                val secondCircle = view.findViewById<ImageView>(R.id.secondCircle)
                val thirdCircle = view.findViewById<ImageView>(R.id.thirdCircle)

                val white:Int = R.drawable.white_circle
                val grey:Int = R.drawable.circle

                when(position){
                    0 -> {
                        Handler().postDelayed({
                            firstCircle.setImageResource(white)
                            secondCircle.setImageResource(grey)
                            thirdCircle.setImageResource(grey)
                            Log.d("time","1")
                        },200)
                    }
                    1 -> {
                        Handler().postDelayed({
                            firstCircle.setImageResource(grey)
                            secondCircle.setImageResource(white)
                            thirdCircle.setImageResource(grey)
                            Log.d("time","2")
                        },200)
                    }
                    2 -> {
                        Handler().postDelayed({
                            firstCircle.setImageResource(grey)
                            secondCircle.setImageResource(grey)
                            thirdCircle.setImageResource(white)
                            Log.d("time","3")
                        },200)
                    }
                }

            }
        })

        return fragmentlist[position]
    }


}