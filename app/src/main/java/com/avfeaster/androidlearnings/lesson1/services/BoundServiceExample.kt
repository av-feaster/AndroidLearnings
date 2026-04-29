package com.avfeaster.androidlearnings.lesson1.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * Minimal bound service: clients get an [IBinder] from [onBind] and call into this instance.
 * Started services use [onStartCommand]; bound services primarily use [onBind]/[onUnbind].
 */
class BoundServiceExample : Service() {

    private val tag = "LESSON_1_BoundServiceExample"

    private val binder = LocalBinder()

    /** Exposed to the same process via cast: (IBinder) as LocalBinder */
    inner class LocalBinder : Binder() {
        fun getService(): BoundServiceExample = this@BoundServiceExample
    }

    private var bindCount = 0

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "onCreate")
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(tag, "onBind: ${intent?.action}")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(tag, "onUnbind")
        // Return true if you want onRebind() the next time a client binds after all unbind.
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(tag, "onDestroy")
        super.onDestroy()
    }

    /** Example API the activity calls through the binder. */
    fun nextCounter(): Int {
        bindCount++
        Log.d(tag, "nextCounter() -> $bindCount")
        return bindCount
    }
}
