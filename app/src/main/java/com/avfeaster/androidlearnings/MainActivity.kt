package com.avfeaster.androidlearnings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import androidx.fragment.app.Fragment.SavedState
import com.avfeaster.androidlearnings.lesson1.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private val TAG = "Lesson_1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Activity: onCreate")

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add Fragment (only first time)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, " Activity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Activity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Activity: onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "Activity : onSaveInstanceState")
    }



    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Activity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Activity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Activity: onDestroy")
    }
}