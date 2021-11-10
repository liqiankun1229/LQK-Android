package com.lqk.ku

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lqk.ku.fl.MyFlutterActivity
import java.util.HashMap
import kotlin.math.log

class CallStopIntentService : IntentService("CallStopIntentService") {

    companion object {
        const val TAG = "CallStopIntentService"
        var callStop = 0
    }

    private fun callServerStop() {
        Log.d(TAG, "callServerStop: ")
        val params: HashMap<String, Any> = hashMapOf()
        params["time"] = "LQK"

        OkHttpUtil.instance.doPost(
            "http://192.168.31.71:5000/callTime",
            params,
            MyFlutterActivity.Time::class.java,
            object : NetWorkCallback<MyFlutterActivity.Time> {
                @SuppressLint("SetTextI18n")
                override fun onSucceed(data: MyFlutterActivity.Time) {
                    callStop++
                    Log.d(TAG, "onSucceed: ${callStop}")
                    Log.d(TAG, "onSucceed: ${data.time}")
                    LiveEventBus.get<String>(MyFlutterActivity.TAG).postDelay("succeed", 1000 * 10L)
                }

                override fun onFailed(msg: String) {
                    Log.d(TAG, "onFailed: $msg")
                }
            })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(10000)
            callServerStop()
        } catch (e: Exception) {

        }
    }
}