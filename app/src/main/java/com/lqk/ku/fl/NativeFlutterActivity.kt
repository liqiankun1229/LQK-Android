package com.lqk.ku.fl

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lqk.ku.R
import com.lqk.ku.fl.call_flutter.FlutterCallNative
import com.lqk.ku.fl.call_flutter.NativeCallFlutter
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlinx.android.synthetic.main.activity_my_flutter.*
import java.nio.ByteBuffer


/**
 * @author LQK
 * @time 2020/4/3 1:49
 * @remark
 */
class NativeFlutterActivity : FlutterActivity() {


    companion object {

        const val TAG = "NativeFlutterActivity"
        fun show(context: Context) {
            context.startActivity(Intent(context, NativeFlutterActivity::class.java))
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {

        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor, FlutterCallNative.CHANNEL).setMethodCallHandler { call, result ->
            run {
                Log.d(TAG, "onCreate: ${call.method}")
                when (call.method) {
                    "oneAct" -> {
                        Toast.makeText(this@NativeFlutterActivity, "oneAct", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        result.success("123")
                    }
                }
            }
        }
        MethodChannel(flutterEngine.dartExecutor, NativeCallFlutter.CHANNEL).setMethodCallHandler { call, result ->
            run {
                Log.d(TAG, "onCreate: ${call.method}")
            }
        }
    }

//    @SuppressLint("VisibleForTests")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_my_flutter)
//        val flutterView = FlutterView(this)
//        if (flutterView.parent == null) {
//            fl.removeAllViews()
//            fl.addView(flutterView)
//        }
//    }

}