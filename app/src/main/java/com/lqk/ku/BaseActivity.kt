package com.lqk.ku

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author LQK
 * @time 2019/3/6 11:25
 * @remark
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindows()
        setContentView(getLayoutId())
        initWidget()
        initData()
    }

    protected fun initWindows() {}

    abstract fun initWidget()

    abstract fun initData()
}