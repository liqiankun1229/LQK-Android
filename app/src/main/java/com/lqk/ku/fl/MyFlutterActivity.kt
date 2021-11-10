package com.lqk.ku.fl

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lqk.ku.*
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import kotlinx.android.synthetic.main.activity_flutter.*

class MyFlutterActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MyFlutterActivity"
        var callStart = 0
    }


    private lateinit var flutterEngine: FlutterEngine
    private lateinit var flutterChannel: BasicMessageChannel<String>
    private lateinit var flutterView: FlutterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter)
        Log.d(TAG, "onCreate: ")
        initListener()
        initEvent()

        flutterEngine = FlutterEngine(this)
        flutterView = FlutterView(this)


        if (flutterView.parent != null) {
            fl.removeAllViews()
            fl.addView(flutterView)
        }
    }

    private fun initEvent() {
        LiveEventBus.get(TAG, String::class.java).observeForever {
            Log.d(TAG, "initEvent: $it")
            if (it == "succeed"){
                callPhone()
            }
        }
    }

    private fun callPhone(){
        call()
    }

    @SuppressLint("HandlerLeak")
    private var mainHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1000) {
                call()
            } else {

            }
        }
    }

    private fun initListener() {
        tv.setOnClickListener {
            NativeFlutterActivity.show(this)
        }
        call_start.setOnClickListener {
            // 20 s 后停止
//            val msg = Message()
//            msg.what = 1000
//            mainHandler.sendMessageDelayed(msg, 10000)
//            startService(Intent(this, CallStopIntentService::class.java))
            //
//            call("17367078677")
//            call("18815596698")
        }
        call_stop.setOnClickListener {
            Toast.makeText(this, "停止", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: callStart: $callStart callStop: ${CallStopIntentService.callStop}")
        if (callStart == 0) {
            val msg = Message()
            msg.what = 1000
            mainHandler.sendMessageDelayed(msg, 5000)
        } else {

        }
    }

    inner class Time {
        var time = ""
    }

//    private fun call(phoneNum: String = "17367078677") {
    private fun call(phoneNum: String = "18815596698") {
//        callStart++
//        Log.d(TAG, "call: ${callStart}")
//        // 延迟通知服务器关闭
//        startService(Intent(this, CallStopIntentService::class.java))
//        // 开始拨打电话
//        val intent = Intent(Intent.ACTION_CALL)
//        val data: Uri = Uri.parse("tel:$phoneNum")
//        intent.setData(data)
//        startActivity(intent)
    }
}