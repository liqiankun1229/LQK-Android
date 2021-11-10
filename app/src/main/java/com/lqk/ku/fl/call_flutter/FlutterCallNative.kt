package com.lqk.ku.fl.call_flutter

import android.app.Activity
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry

/**
 * @author LQK
 * @time 2019/8/10 10:53
 * @remark Flutter 界面 -> 原生 数据交互
 */
class FlutterCallNative(private var activity: Activity) : MethodChannel.MethodCallHandler {

    companion object {
        const val TAG = "FlutterCallNative"
        const val CHANNEL = "plugin_call_native"
        var channel: MethodChannel? = null

        fun registerWith(register: PluginRegistry.Registrar) {
            channel = MethodChannel(register.messenger(), CHANNEL)


            val instance = FlutterCallNative(register.activity())
            channel?.setMethodCallHandler(instance)
        }

//        fun registerWith(plugin:FlutterPlugin){
//            channel = MethodChannel()
//        }
    }

    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "oneAct" -> {
                Log.d(TAG, "oneAct Response")
                result.success("One Success")
            }
            "twoAct" -> {
                Log.d(TAG, "twoAct Response")
                val json = methodCall.argument<String>("data")
                Log.d(TAG, "$json")
                result.success("Two Success")
            }
            "jump" -> {
                Log.d(TAG, "Flutter Call jump")
                result.success("Jump Success")
            }
            else -> {
                result.notImplemented()
            }
        }
    }
}