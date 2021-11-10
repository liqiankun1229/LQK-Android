package com.lqk.ku

/**
 * @author LQK
 * @time 2019/4/15 8:45
 * @remark 网络请求类型操作
 */
interface NetWorkOperation {
    fun <T> doGet(url: String, params: HashMap<String, Any>, resultClass: Class<T>, callback: NetWorkCallback<T>? = null)

    fun <T> doPost(url: String, params: HashMap<String, Any>, resultClass: Class<T>, callback: NetWorkCallback<T>? = null)

    fun doPut()

    fun doDelete()
}