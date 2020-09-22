package cn.cqupt.taobao.net

import android.util.Log
import cn.cqupt.taobao.bean.request.IsRegisterBean
import cn.cqupt.taobao.bean.response.IsRegisterResponse
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.net.service.Services
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetUtil {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://47.100.59.154:40000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun login(username: String,password:String,callback: NetUtilResponse<ResponseBody>){
        val request = retrofit.create(Services::class.java)
        val call = request.login(username,password)
        call.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val temp:ResponseBody? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun isRegister(username: String, callback: NetUtilResponse<IsRegisterResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.isRegister(IsRegisterBean(data = IsRegisterBean.Data(username)))

        call.enqueue(object :Callback<IsRegisterResponse>{
            override fun onResponse(call: Call<IsRegisterResponse>, response: Response<IsRegisterResponse>) {
                Log.e("test","$response")
                if (response.isSuccessful){
                    val temp:IsRegisterResponse? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<IsRegisterResponse>, t: Throwable) {
                Log.e("test"," call = $call t=$t")
                callback.onFailure()
            }
        })
    }


    fun register(username: String,password:String,phoneNumber:String,callback: NetUtilResponse<ResponseBody>){
        val request = retrofit.create(Services::class.java)
        val call = request.register(username,password,phoneNumber)
        call.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val temp:ResponseBody? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun getGood(username:String,callback:NetUtilResponse<ResponseBody>){
        val request = retrofit.create(Services::class.java)
        val call = request.getGood()

        call.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val temp:ResponseBody? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}