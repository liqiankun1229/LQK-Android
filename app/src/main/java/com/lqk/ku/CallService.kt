package com.lqk.ku

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.lqk.ku.fl.MyFlutterActivity
import java.util.HashMap

class CallService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

//        return super.onStartCommand(intent, flags, startId)
        callServerStop()
        return START_STICKY
    }

    private fun callServerStop() {
        val params: HashMap<String, Any> = hashMapOf()
        params["time"] = "LQK"
        OkHttpUtil.instance.doPost("http://192.168.31.71:5000/callTime", params, MyFlutterActivity.Time::class.java, object : NetWorkCallback<MyFlutterActivity.Time> {
            @SuppressLint("SetTextI18n")
            override fun onSucceed(data: MyFlutterActivity.Time) {

            }

            override fun onFailed(msg: String) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}