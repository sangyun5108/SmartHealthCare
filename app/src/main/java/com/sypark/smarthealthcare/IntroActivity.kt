package com.sypark.smarthealthcare

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sypark.smarthealthcare.auth.AuthActivity


class IntroActivity : AppCompatActivity() {

    private lateinit var nfcAdapter: NfcAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if(nfcAdapter!==null){
            onNewIntent(intent)
        }

        Handler().postDelayed({
            val intent = Intent(this,AuthActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val tag: Tag? = intent?.getParcelableExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

        if(NfcAdapter.ACTION_NDEF_DISCOVERED == intent?.action){
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map {it as NdefMessage}
                Log.d("nfc","nfc")
            }
        }
    }

}