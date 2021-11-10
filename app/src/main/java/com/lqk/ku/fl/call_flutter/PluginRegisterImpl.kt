package com.lqk.ku.fl.call_flutter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.app.FlutterActivityDelegate
import io.flutter.app.FlutterActivityEvents
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.view.FlutterNativeView
import io.flutter.view.FlutterView

/**
 * @author LQK
 * @time 2019/8/10 21:29
 * @remark
 */
class PluginRegisterImpl : PluginRegistry, FlutterActivityDelegate.ViewFactory, FlutterView.Provider {

    private var nativeActivity: AppCompatActivity
    private var nativeFlutterView: FlutterView
    private var nativeFlutterNativeView: FlutterNativeView

    private var delegate: FlutterActivityDelegate
    private var eventDelegate: FlutterActivityEvents
    private var viewProvider: FlutterView.Provider
    private var pluginRegistry: PluginRegistry

    companion object {
        fun registerWith(pluginKey: String) {
        }
    }

    constructor(activity: AppCompatActivity) {
        nativeActivity = activity
        nativeFlutterView = FlutterView(activity)
//        nativeFlutterView = Flutter.createView(nativeActivity, nativeActivity.lifecycle, "login")
        nativeFlutterNativeView = FlutterNativeView(nativeActivity)
        delegate = FlutterActivityDelegate(nativeActivity, this)
        this.eventDelegate = this.delegate
        this.viewProvider = this.delegate
        this.pluginRegistry = this.delegate
        GeneratedPluginRegistrant.registerWith(FlutterEngine(nativeActivity))
//        initChannel()
    }

    fun onCreate(savedInstanceState: Bundle?) {
        this.delegate.onCreate(savedInstanceState)
    }

    private fun initChannel() {
//        GeneratedPluginRegistrant.registerWith(this)
        FlutterCallNative(nativeActivity)
        NativeCallFlutter(nativeActivity)
    }

    override fun <T : Any?> valuePublishedByPlugin(pluginKey: String?): T {
        return this.pluginRegistry.valuePublishedByPlugin(pluginKey)
    }

    override fun registrarFor(pluginKey: String?): PluginRegistry.Registrar {
        return this.pluginRegistry.registrarFor(pluginKey)
    }

    override fun hasPlugin(key: String?): Boolean {
        return this.pluginRegistry.hasPlugin(key)
    }

    override fun retainFlutterNativeView(): Boolean {
        return nativeFlutterView != null
    }

    override fun createFlutterNativeView(): FlutterNativeView {
        return nativeFlutterNativeView
    }

    override fun createFlutterView(p0: Context?): FlutterView {
        return nativeFlutterView
    }

    override fun getFlutterView(): FlutterView {
        return nativeFlutterView
    }

    fun registerWith(pluginKey: String) {
        pluginRegistry.registrarFor(pluginKey)
        pluginRegistry.registrarFor(FlutterCallNative.CHANNEL)
        pluginRegistry.registrarFor(NativeCallFlutter.CHANNEL)
    }
}