package com.avfeaster.androidlearnings.lesson1.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleEventObserver
import com.avfeaster.androidlearnings.R
import com.avfeaster.androidlearnings.lesson1.services.BoundServiceExample
import com.avfeaster.androidlearnings.lesson1.services.StartServiceExample

class HomeFragment : Fragment() {

    private val TAG = "LESSON_1"//"HomeFragment"

    /** Start once per fragment instance (not from MainActivity). */
    private var startedStartServiceExample = false

    private var boundService: BoundServiceExample? = null
    /** True after [bindService] returned true for this start cycle (pair with [unbindService] in [onStop]). */
    private var needUnbind = false

    private val boundConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundServiceExample.LocalBinder
            boundService = binder.getService()

            Log.d(TAG, "HomeFragment onServiceConnected -> ${boundService?.nextCounter()}")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            boundService = null
            Log.d(TAG, "HomeFragment onServiceDisconnected")
        }
    }

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

        // Fragment lifecycle (this) vs view lifecycle — e.g. with back stack, view is destroyed
        // in onDestroyView while the fragment instance often stays alive.
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(TAG, "Fragment LifecycleOwner → $event")
        })
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(TAG, "viewLifecycleOwner → $event")
        })

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
        if (!startedStartServiceExample) {
            startedStartServiceExample = true
            requireContext().startService(
                Intent(requireContext(), StartServiceExample::class.java)
            )
            Log.d(TAG, "HomeFragment: startService(StartServiceExample) from fragment")
        }
        val bindIntent = Intent(requireContext(), BoundServiceExample::class.java)
        needUnbind = requireContext().bindService(bindIntent, boundConnection, Context.BIND_AUTO_CREATE)
        Log.d(TAG, "HomeFragment: bindService(BoundServiceExample) ok=$needUnbind")
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
        if (needUnbind) {
            requireContext().unbindService(boundConnection)
            needUnbind = false
            boundService = null
            Log.d(TAG, "HomeFragment: unbindService(BoundServiceExample)")
        }
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