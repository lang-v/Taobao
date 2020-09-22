package cn.cqupt.taobao.net.callback

interface NetUtilResponse<T>{
    fun onSuccess(t:T)
    fun onFailure()
}