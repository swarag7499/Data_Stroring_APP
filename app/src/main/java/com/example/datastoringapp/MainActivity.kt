package com.example.datastoringapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    var mDatabaseHelper: DatabaseHelper? = null
    private val btnAdd: Button? = null
    private  var btnViewData: Button? = null

    private val editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}