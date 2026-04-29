package com.avfeaster.androidlearnings.lesson1.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.avfeaster.androidlearnings.R

class HomeFragment : Fragment() {

    private val TAG = "LESSON_1"//"HomeFragment"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "HomeFragment : onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "HomeFragment : onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "HomeFragment : onCreateView")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "HomeFragment : onViewCreated")

        // UI setup here
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = "Home Fragment"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "HomeFragment : onSaveInstanceState")
    }

    override fun setInitialSavedState(state: SavedState?) {
        super.setInitialSavedState(state)
        Log.d(TAG, "HomeFragment : setInitialSavedState")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "HomeFragment : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "HomeFragment: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "HomeFragment : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "HomeFragment : onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "HomeFragment : onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "HomeFragment: onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "HomeFragment: onDetach")
    }
}