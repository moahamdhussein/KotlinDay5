package com.example.kotlinday5.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kotlinday5.ActivityTwo
import com.example.kotlinday5.InfoFragment
import com.example.kotlinday5.model.Products
import com.example.kotlinday5.R

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var manger: FragmentManager
    private lateinit var infoFragment: InfoFragment
    private var description: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manger = supportFragmentManager
        if (savedInstanceState == null) {
            infoFragment = InfoFragment()
            val transaction: FragmentTransaction = manger.beginTransaction()
            transaction.add(R.id.dynamicFragmentContainerView, infoFragment, "myFragmentTwo")
            transaction.commit()
        } else {
            description = savedInstanceState.getString("description")
            infoFragment = manger.findFragmentByTag("myFragmentTwo") as InfoFragment
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val fragmentContainer = findViewById<View>(R.id.dynamicFragmentContainerView)
            fragmentContainer.visibility = View.INVISIBLE
        }
    }

    override fun respond(data: Products) {
        description = data.title
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(this, ActivityTwo::class.java)
            intent.putExtra("description", description)
            startActivity(intent)
        } else {
            infoFragment.changeData(description!!)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("description", description)

    }

    override fun onStart() {
        super.onStart()
        if (description != null) {
            infoFragment.changeData(description!!)
        }
    }


}