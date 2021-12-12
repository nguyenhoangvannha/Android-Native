package com.nhvn.todoandroidnative.ui.observers

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyActivityLifecycleObserver : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        Log.i("MyActivityLCObserver", "onCreate")
        super.onCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.i("MyActivityLCObserver", "onStart")
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.i("MyActivityLCObserver", "onResume")
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i("MyActivityLCObserver", "onPause")
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.i("MyActivityLCObserver", "onStop")
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.i("MyActivityLCObserver", "onDestroy")
        super.onDestroy(owner)
    }
}