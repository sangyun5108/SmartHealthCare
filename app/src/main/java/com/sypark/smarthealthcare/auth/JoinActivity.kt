package com.sypark.smarthealthcare.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sypark.smarthealthcare.R
import com.sypark.smarthealthcare.exercisecount.exCountActivity

class JoinActivity : AppCompatActivity() {

    private var permission:Boolean = true

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val email = findViewById<EditText>(R.id.joinEmail)
        val name = findViewById<EditText>(R.id.joinName)
        val password = findViewById<EditText>(R.id.joinPassword)
        val passwordCheck = findViewById<EditText>(R.id.joinPasswordCheck)
        val joinButton = findViewById<Button>(R.id.joinButton)

        auth = Firebase.auth

        joinButton.setOnClickListener {
            checkJoin(email,name,password,passwordCheck)
        }

    }


    // 회원가입 오류 로직
    private fun checkJoin(email:EditText, name:EditText, password:EditText, passwordCheck:EditText){

        val emailTxt = email.text.toString()
        val nameTxt = name.text.toString()
        val passwordTxt = password.text.toString()
        val passwordCheckTxt = passwordCheck.text.toString()

        if(emailTxt.isEmpty()){
            Toast.makeText(this,"이메일을 입력하세요",Toast.LENGTH_SHORT).show()
            permission = false
        }else{
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
                Toast.makeText(this,"올바른 이메일 형식을 입력하세요",Toast.LENGTH_SHORT).show()
                permission = false
            }
        }

        if(nameTxt.isEmpty()){
            Toast.makeText(this,"이름을 입력하세요",Toast.LENGTH_SHORT).show()
            permission = false
        }

        if(passwordTxt.isEmpty()){
            Toast.makeText(this,"비밀번호를 입력하세요",Toast.LENGTH_SHORT).show()
            permission = false
        }else{
            if(password.length()<6){
                Toast.makeText(this,"비밀번호를 6자리 이상 입력하세요",Toast.LENGTH_SHORT).show()
                permission = false
            }
        }

        if(passwordCheckTxt.isEmpty()){
            Toast.makeText(this," 비밀번호 확인란을 입력하세요",Toast.LENGTH_SHORT).show()
            permission = false
        }else{
            if(passwordTxt!=passwordCheckTxt){
                Toast.makeText(this,"비밀번호가 다릅니다",Toast.LENGTH_SHORT).show()
                permission = false
            }
        }

        if(permission){
            Log.d("permission",permission.toString())
            createAcount(emailTxt,passwordTxt)
        }
    }

    //신규 사용자 가입 Firebase 이용
    private fun createAcount(email:String,password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, exCountActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }
}