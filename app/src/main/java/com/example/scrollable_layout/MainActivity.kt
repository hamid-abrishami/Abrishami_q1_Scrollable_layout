package com.example.scrollable_layout

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var fullName:EditText
    lateinit var userName:EditText
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var rePass:EditText
    lateinit var gender:RadioGroup
    lateinit var showFullname:TextView
    lateinit var showUsername:TextView
    lateinit var showEmail:TextView
    lateinit var showPass:TextView
    lateinit var showGender:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registerButton:Button = findViewById(R.id.registerButton)
        val showButton:Button = findViewById(R.id.showinfoButton)
        val hideInfo = findViewById<Button>(R.id.hideInfo)
        val myInfoGroup = findViewById<Group>(R.id.group)

         fullName = findViewById<EditText>(R.id.etFullname)
         userName = findViewById<EditText>(R.id.etUsername)
         email = findViewById<EditText>(R.id.etEmail)
         pass = findViewById<EditText>(R.id.etPassword)
         rePass = findViewById<EditText>(R.id.etRePassword)
         gender = findViewById<RadioGroup>(R.id.radioGroup)

        showFullname = findViewById<TextView>(R.id.showFullName)
        showUsername = findViewById<TextView>(R.id.showUserName)
        showEmail = findViewById<TextView>(R.id.showEmail)
        showPass = findViewById<TextView>(R.id.showPass)
        showGender = findViewById<TextView>(R.id.showGender)

        registerButton.setOnClickListener {
                saveData()
          }

        showButton.setOnClickListener{
            loadData()
            myInfoGroup.visibility = View.VISIBLE
        }
        hideInfo.setOnClickListener{
            myInfoGroup.visibility = View.GONE
        }
    }

    fun  saveData(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        if (pass.text.toString() == rePass.text.toString()){
            editor.apply {
                putString("fullName_KEY",fullName.text.toString())
                putString("userName_KEY",userName.text.toString())
                putString("email_KEY",email.text.toString())
                putString("pass_KEY",pass.text.toString())
                putString("gender_KEY",gender.textAlignment.toString())
            }.apply()
            Toast.makeText(this,"data saved in SharedPreferences ",Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this,"enter your password carefully",Toast.LENGTH_LONG).show()
    }

    fun loadData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedFullName = sharedPreferences.getString("fullName_KEY","Full Name")
        val savedUserName = sharedPreferences.getString("userName_KEY","Username")
        val savedEmail = sharedPreferences.getString("email_KEY","Email")
        val savedPass = sharedPreferences.getString("pass_KEY","Password")
        val savedGender = sharedPreferences.getString("gender_KEY","Gender")
        showFullname.text = savedFullName
        showUsername.text = savedUserName
        showEmail.text = savedEmail
        showPass.text = savedPass
        showGender.text = savedGender

    }

}