package com.example.scrollable_layout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {
    lateinit var fullName: EditText
    lateinit var userName: EditText
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var rePass: EditText
    lateinit var gender: RadioGroup
    lateinit var maleButton: RadioButton
    lateinit var femaleButton: RadioButton
    lateinit var showFullname: TextView
    lateinit var showUsername: TextView
    lateinit var showEmail: TextView
    lateinit var showPass: TextView
    lateinit var showGender: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registerButton: Button = findViewById(R.id.registerButton)
        val showButton: Button = findViewById(R.id.showinfoButton)
        val hideInfo = findViewById<Button>(R.id.hideInfo)
        val myInfoGroup = findViewById<Group>(R.id.group)

        fullName = findViewById<EditText>(R.id.etFullname)
        userName = findViewById<EditText>(R.id.etUsername)
        email = findViewById<EditText>(R.id.etEmail)
        pass = findViewById<EditText>(R.id.etPassword)
        rePass = findViewById<EditText>(R.id.etRePassword)
        gender = findViewById<RadioGroup>(R.id.radioGroup)
        maleButton = findViewById<RadioButton>(R.id.maleButton)
        femaleButton = findViewById(R.id.femaleButton)
        showFullname = findViewById<TextView>(R.id.showFullName)
        showUsername = findViewById<TextView>(R.id.showUserName)
        showEmail = findViewById<TextView>(R.id.showEmail)
        showPass = findViewById<TextView>(R.id.showPass)
        showGender = findViewById<TextView>(R.id.showGender)

        registerButton.setOnClickListener {
            saveData()
        }

        showButton.setOnClickListener {
            loadData()
            myInfoGroup.visibility = View.VISIBLE
        }
        hideInfo.setOnClickListener {
            myInfoGroup.visibility = View.GONE
        }
    }

    fun saveData() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        if (pass.text.toString() == rePass.text.toString()) {
            editor.apply {
                if (!fullName.text.toString().isBlank())
                    putString("fullName_KEY", fullName.text.toString())
                if (!userName.text.toString().isBlank())
                    putString("userName_KEY", userName.text.toString())
                if (!email.text.toString().isBlank())
                    putString("email_KEY", email.text.toString())
                if (!pass.text.toString().isBlank())
                    putString("pass_KEY", pass.text.toString())
                if (maleButton.isChecked) {
                    putString("gender_KEY", maleButton.text.toString())
                } else if (femaleButton.isChecked) {
                    putString("gender_KEY", femaleButton.text.toString())
                }
            }.apply()
            Toast.makeText(this, "data saved in SharedPreferences ", Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(this, "enter your password carefully", Toast.LENGTH_LONG).show()
    }

    fun loadData() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedFullName = sharedPreferences.getString("fullName_KEY", "Full Name")
        val savedUserName = sharedPreferences.getString("userName_KEY", "Username")
        val savedEmail = sharedPreferences.getString("email_KEY", "Email")
        val savedPass = sharedPreferences.getString("pass_KEY", "Password")
        val savedGender = sharedPreferences.getString("gender_KEY", "Gender")
        showFullname.text = savedFullName
        showUsername.text = savedUserName
        showEmail.text = savedEmail
        showPass.text = savedPass
        showGender.text = savedGender

    }
}