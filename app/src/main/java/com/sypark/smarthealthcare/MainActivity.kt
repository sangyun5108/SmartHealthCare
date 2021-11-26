package com.sypark.smarthealthcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean{
        when(item.itemId){

            R.id.page_exercise -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,mainCountFragment()).commitAllowingStateLoss()
                return true
            }

            R.id.page_record -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,mainRecordFragment()).commitAllowingStateLoss()
                return true
            }

            R.id.page_calibrate -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,HomeFragment()).commitAllowingStateLoss()
                return true
            }

            R.id.page_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,mainSetingFragment()).commitAllowingStateLoss()
                return true
            }
            else -> {
                return false
            }
        }
    }
}