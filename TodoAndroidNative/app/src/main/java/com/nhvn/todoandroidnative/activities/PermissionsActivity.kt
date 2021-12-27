package com.nhvn.todoandroidnative.activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.nhvn.todoandroidnative.R

class PermissionsActivity : AppCompatActivity() {
    lateinit var btnStartRuntimePermissionFlow: Button
    lateinit var tvHasCamera: TextView
    lateinit var tvPermissionStatus: TextView
    lateinit var btnShouldShowRequestPermissionRationale: Button

    //    lateinit var activityResultLauncher: ActivityResultLauncher<String>
    lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    lateinit var btnRequestPermisson: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        btnStartRuntimePermissionFlow = findViewById<Button>(R.id.btnStartRuntimePermissionFlow);
        tvHasCamera = findViewById<TextView>(R.id.tvHasCamera);
        tvPermissionStatus = findViewById<TextView>(R.id.tvPermissionStatus);
        btnShouldShowRequestPermissionRationale =
            findViewById<Button>(R.id.btnShouldShowRequestPermissionRationale);
        btnRequestPermisson = findViewById<Button>(R.id.btnRequestPermisson);

//        activityResultLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission(),
//            MyActivityResultCallback
//        )

        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    btnRequestPermisson.text = "Permission is granted."
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    btnRequestPermisson.text = "Include photo feature is unavailable"
                }
            }

        btnStartRuntimePermissionFlow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                checkDeviceHasAFeature()
            }
        })
    }

    private fun checkDeviceHasAFeature() {
        // Check whether your app is running on a device that has a front-facing camera.
        if (applicationContext.packageManager.hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT
            )
        ) {
            // Continue with the part of your app's workflow that requires a
            // front-facing camera.
            tvHasCamera.text = "Have FEATURE_CAMERA_FRONT";
            checkSelfPermissionGranted()
        } else {
            // Gracefully degrade your app experience.
            tvHasCamera.text = "Dont Have FEATURE_CAMERA_FRONT";
        }
    }

    private fun checkSelfPermissionGranted() {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        when (result) {
            (PackageManager.PERMISSION_GRANTED) -> {
                tvPermissionStatus.text = "Manifest.permission.CAMERA PERMISSION_GRANTED";
            }
            (PackageManager.PERMISSION_DENIED) -> {
                tvPermissionStatus.text = "Manifest.permission.CAMERA PERMISSION_DENIED";
                checkShouldShowRequestPermissionRationale()
            }
        }
    }

    private fun checkShouldShowRequestPermissionRationale() {
        val shouldShow = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
        if (shouldShow) {
            btnShouldShowRequestPermissionRationale.text =
                "shouldShowRequestPermissionRationale true"
            btnShouldShowRequestPermissionRationale.setOnClickListener(object :
                View.OnClickListener {
                override fun onClick(v: View?) {
                    showRequestPermissionRationale()
                }
            })
        } else {
            btnShouldShowRequestPermissionRationale.text =
                "shouldShowRequestPermissionRationale false"
            btnShouldShowRequestPermissionRationale.setOnClickListener(object :
                View.OnClickListener {
                override fun onClick(v: View?) {
                    requestPermission()
                }
            })
        }
    }

    private fun showRequestPermissionRationale() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("RequestPermissionRationale")
            .setMessage("Need camera permission to include a photo to your note")
            .setPositiveButton("Ok", { dialog, which ->
                requestPermission()
            })
        builder.create().show()
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA);
    }
}

//class MyActivityResultCallback : ActivityResultCallback<Boolean> {
//    override fun onActivityResult(result: Boolean?) {
//        TODO("Not yet implemented")
//    }
//
//    companion object MyActivityResultCallback {
//
//    }
//}