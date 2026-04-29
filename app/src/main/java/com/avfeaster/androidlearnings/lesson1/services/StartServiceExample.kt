package com.avfeaster.androidlearnings.lesson1.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.Provider


class StartServiceExample: Service() {

    val TAG = "LESSON_1_StartServiceExample"

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onBind(p0: Intent?): IBinder? {
        return  null;
    }

    /**
     * Return values from onStartCommand():
     * - START_NOT_STICKY: Service will NOT restart if killed
     * - START_STICKY: Service restarts, but intent is null
     * - START_REDELIVER_INTENT: Service restarts and receives last intent again
     * - START_STICKY_COMPATIBILITY: Legacy behavior where the system may decide
     *   whether to restart the service; not guaranteed and generally not recommended
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)

        // Do background work (not on main thread in real apps)
        serviceScope.launch {
            try {
                for (i in 1..5) {
                    Log.e(TAG,"Running task $i")
                    delay(1000) // non-blocking (replaces Thread.sleep)
                }
            } finally {
                stopSelf(startId) // stop only this start request
            }
        }

        return START_NOT_STICKY
    }

}