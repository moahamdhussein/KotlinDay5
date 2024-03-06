package com.example.kotlinday5

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class ActivityTwo : AppCompatActivity() {

    private lateinit var manger: FragmentManager
    private lateinit var fragmentInfo: InfoFragment
    private lateinit var data: String
    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        intent = getIntent()
        manger = supportFragmentManager

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            this.finish()
        }else{
            if (savedInstanceState ==null){
                fragmentInfo = InfoFragment()
                val transaction : FragmentTransaction = manger.beginTransaction()
                transaction.add(R.id.dynamicFragmentContainerView,fragmentInfo,"myFragmentTwo")
                transaction.commit()
            }else{
                fragmentInfo = manger.findFragmentByTag("myFragmentTwo") as (InfoFragment)
            }

        }

    }

    override fun onResume() {
        super.onResume()
        data = intent.extras?.getString("description","null") ?: "null"
        fragmentInfo.changeData(data)
    }
}