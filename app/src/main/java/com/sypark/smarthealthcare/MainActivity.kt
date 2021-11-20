package com.sypark.smarthealthcare

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 디바이스의 NFC 상태를 확인하기 위한 NFC adapter
    private var nfcAdapter:NfcAdapter?=null

    // Pending intent for NFC intent foreground dispatch.
    // Used to read all NDEF tags while the app is running in the foreground.
    private var nfcPendingIntent:PendingIntent?=null
    // Optional: filter NDEF tags this app receives through the pending intent.
    // private var nfcintentFilters: Array<IntentFilter>?=null
    private val KEY_LOG_TEXT = "logText"

    val tv_messages = findViewById<TextView>(R.id.tv_message)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Restore saved text if available
        if(savedInstanceState!=null){
            findViewById<TextView>(R.id.tv_message).text = savedInstanceState.getCharSequence(KEY_LOG_TEXT)
        }

        logMessage("Welcome","App started")


//        Mainfest에서 uses-feature를 지정하더라도 Google Play 스토어를 이용하지 않고, 수동으로 앱을 설치할 경우
//        uses-feature 정의가 적용되지 않기 때문에, NFC가 지원되지 않은 디바이스에 연결이 될 수 있다.
//        즉, 디바이스가 NFC를 지원하더라도 설정에서 비활성화가 되어 있을 가능성이 있기 때문에, 앱이 시작될 때 NFC 지원을 확인해야 한다.

        // Check if NFC is supported and enabled
        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        //안드로이드 장치의 기본 NFC 어댑터를 가져온다.
        logMessage("NFC supported",(nfcAdapter!=null).toString())
        logMessage("NFC enabled",(nfcAdapter?.isEnabled).toString())

        //Read all tags when app is running and in the foreground
        //Cretae a genric PendingIntent that will be deliver to this activity
        //The NFC stack will fill in the intent with the details of
        // the discovered tag before delivering to this activity.
        nfcPendingIntent = PendingIntent.getActivity(this,0,Intent(this,javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0)
        //nfcAdapter -> null인경우, 디바이스는 NFC지원 x
        //nfcAdapter.isEnabled -> true, NFC 준비완료


        //인텐트가 앱에 도달하기 위해 어떤 방법을 사용하든지, 중앙 집중식으로 처리하는 것이 제일 좋기 때문에, processIntent()라는 새함수를
        //작성하여서 두 진입 점에서 호출해라

        //Incoming NFC 인텐트 처리
        if(intent!=null){
            processIntent(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //Get all NDEF discovered intents.
        //Make sure the app gets all discovered NDEF messages as Long as
        //it's in the foreground
        nfcAdapter?.enableForegroundDispatch(this,nfcPendingIntent,null,null)
        //Alternative: only get specific HTTP NDEF intent
        //nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent.
        //nfcIntentFilters, null);
    }


    override fun onPause() {
        super.onPause()
        //Disable foreground dispatch, as this activity is no longer
        //in the foreground
        nfcAdapter?.disableForegroundDispatch(this)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        logMessage("Found intent in onNewIntent", intent?.action.toString())
        // If we got an intent while the app is running, also check
        // if it's a new NDEF message that was discovered

        if(intent!=null){
            processIntent(intent)
        }
    }

    // 사용자 정의 processIntent 함수에서 NFC태그의 내용을 조사한다.
    // NDEF 메시지 배열을 추출한다.

    // Check if the Intent has the action "ACTION_NDEF_DISCOVERED".
    // If yes, handle it accordingly and parse the NDEF messages.
    // @param checkIntent the intent to parse and handle if it's the right type

    private fun processIntent(checkIntent:Intent){

        //Check if intent has the action of a discovered NFC tag
        //with NDEF formatted contents
        if(checkIntent.action == NfcAdapter.ACTION_NDEF_DISCOVERED){
            logMessage("New NDEF intent",checkIntent.toString())

            //Retrieve the row NDEF message from the tag
            //Tag에서 메세지 값 받아오기
            val rawMessages = checkIntent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES
            )
            logMessage("Raw message", rawMessages?.size.toString())

            //Compelete variant: parse NDEF messages
            if(rawMessages!=null){
                val messages = arrayOfNulls<NdefMessage?>(rawMessages.size)
                //Array<NdefMessage>(rawMessages.size,{})

                //메세지 값에서 record 값 받아오기
                for(i in rawMessages.indices){
                    messages[i] = rawMessages[i] as NdefMessage;
                }
                // Process the message array.
                processNdefMessages(messages)
            }
        }
    }

    // Parse the NDEF message contents and print these to the on-screen big.
    private fun processNdefMessages(ndefMessages:Array<NdefMessage?>){
        for(curMsg in ndefMessages){
            if(curMsg!=null){
                // Print generic infromation found on the NFC tag
                logMessage("Message",curMsg.toString())
                // The NDEF message usually contains 1+ records - print the number of records
                logMessage("Records", curMsg.records.size.toString())

                // Loop through all the records contained in the message
                for(curRecord in curMsg.records){
                    if(curRecord.toUri()!=null){
                        //URI NDEF Tag
                        logMessage("- URI", curRecord.toUri().toString())
                    }else{
                        //Other NDEF Tags - simply print the payload
                        logMessage("- Contents", curRecord.payload.contentToString())
                    }
                }
            }
        }
    }

    // Utility functions

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.putCharSequence(KEY_LOG_TEXT,tv_messages.text)
        super.onSaveInstanceState(outState)
    }

    private fun logMessage(header:String, text:String?){
        tv_messages.append(if(text.isNullOrBlank())fromHtml("<b>$header</b><br>")
        else fromHtml("<b>$header</b>:$text<br>"))
    }

    private fun fromHtml(html:String): Spanned {
        return if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.N){
            Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY)
        }else{
            @Suppress("DEPRECATION")
            Html.fromHtml(html)
        }
    }


}