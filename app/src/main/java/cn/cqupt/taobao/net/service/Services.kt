package cn.cqupt.taobao.net.service

import cn.cqupt.taobao.bean.request.IsRegisterBean
import cn.cqupt.taobao.bean.request.LoginBean
import cn.cqupt.taobao.bean.request.RegisterBean
import cn.cqupt.taobao.bean.response.IsRegisterResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Path

interface Services {
    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun getGood(): Call<ResponseBody>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun login(@Body bean: LoginBean): Call<ResponseBody>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun isRegister(@Body json: IsRegisterBean): Call<IsRegisterResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun register(@Body bean: RegisterBean): Call<ResponseBody>
}