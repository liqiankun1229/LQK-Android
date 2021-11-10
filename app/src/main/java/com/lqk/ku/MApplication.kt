package com.lqk.ku

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterLoader

/**
 * @author LQK
 * @time 2020/4/2 10:42
 * @remark
 */
class MApplication : Application() {

    companion object {
        const val engineId = "my_engine_id"
    }

    private lateinit var flutterEngine: FlutterEngine
    override fun onCreate() {
        super.onCreate()

        // 初始化 flutter
        val loader = FlutterLoader()
        val loaderSetting = FlutterLoader.Settings()
        loaderSetting.logTag = "MyFlutter"
        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(engineId, flutterEngine)
        loader.startInitialization(this, loaderSetting)

    }
}