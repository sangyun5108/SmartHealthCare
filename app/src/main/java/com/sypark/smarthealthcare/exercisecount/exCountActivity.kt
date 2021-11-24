package com.sypark.smarthealthcare.exercisecount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.sypark.smarthealthcare.R

class exCountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex_count)

        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val button = findViewById<Button>(R.id.countButton)
        val percent = findViewById<TextView>(R.id.percent)
        val countNumber = findViewById<TextView>(R.id.countNumber)

        var pStatus = progressbar.progress
        var count = 0

        button.setOnClickListener {
            kotlin.concurrent.timer(period = 7, initialDelay = 0) {
                runOnUiThread {
                    percent.text="${pStatus+1}%"
                }
                progressbar.setProgress(++pStatus)
                if(progressbar.progress==100){
                    cancel()
                    runOnUiThread {
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
    }
}