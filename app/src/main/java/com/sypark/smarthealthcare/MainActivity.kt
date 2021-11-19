package com.sypark.smarthealthcare

import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Mainfest에서 uses-feature를 지정하더라도 Google Play 스토어를 이용하지 않고, 수동으로 앱을 설치할 경우
//        uses-feature 정의가 적용되지 않기 때문에, NFC가 지원되지 않은 디바이스에 연결이 될 수 있다.
//        즉, 디바이스가 NFC를 지원하더라도 설정에서 비활성화가 되어 있을 가능성이 있기 때문에, 앱이 시작될 때 NFC 지원을 확인해야 한다.

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        //안드로이드 장치의 기본 NFC 어댑터를 가져온다.
        Log.d("NFC supported",(nfcAdapter!=null).toString())
        Log.d("NFC enabled",(nfcAdapter?.isEnabled).toString())
        //nfcAdapter -> null인경우, 디바이스는 NFC지원 x
        //nfcAdapter.isEnabled -> true, NFC 준비완료
    }
}