package com.sypark.smarthealthcare.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sypark.smarthealthcare.R
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private var permission:Boolean = true
    // 로그인 입력 데이터 유형 확인

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.loginEmail)
        val password = findViewById<EditText>(R.id.loginPassword)
        val button = findViewById<Button>(R.id.login_loginButton)

        auth = Firebase.auth

        // EditText 값은 로그인 버튼을 누르는 시점에서 가지고 와야 한다

        button.setOnClickListener {
            checkLogin(email,password)
        }
    }

    private fun checkLogin(email:EditText,passowrd:EditText){
        val emailText = email.text.toString()
        val passwordText = passowrd.text.toString()

        if(emailText.isEmpty()){
            Toast.makeText(this,"이메일을 입력하세요",Toast.LENGTH_SHORT).show()
            permission = false
        }

        if(passwordText.isEmpty()){
            Toast.makeText(this,"비밀번호를 입력하세요",Toast.LENGTH_SHORT).show()
            permission = false
        }

        if(permission){
            Toast.makeText(this,"로그인이 완료되었습니다",Toast.LENGTH_SHORT).show()
            login(emailText,passwordText)
        }
    }

    private fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"로그인 완료",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                }
            }
    }

}