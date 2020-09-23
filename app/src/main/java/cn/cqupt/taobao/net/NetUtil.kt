package cn.cqupt.taobao.net

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import cn.cqupt.taobao.bean.Good
import cn.cqupt.taobao.bean.request.*
import cn.cqupt.taobao.bean.response.GoodResponse
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.net.service.Services
import cn.cqupt.taobao.view.toBase64
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object NetUtil {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://47.100.59.154:40000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun login(username: String,password:String,callback: NetUtilResponse<PersonResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.login(LoginBean(data = LoginBean.Data(username, password)))
        call.enqueue(object :Callback<PersonResponse>{
            override fun onResponse(call: Call<PersonResponse>, response: Response<PersonResponse>) {
                if (response.isSuccessful){
                    val temp:PersonResponse? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun isRegister(username: String, callback: NetUtilResponse<cn.cqupt.taobao.bean.response.PersonResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.isRegister(IsRegisterBean(data = IsRegisterBean.Data(username)))

        call.enqueue(object :Callback<cn.cqupt.taobao.bean.response.PersonResponse>{
            override fun onResponse(call: Call<cn.cqupt.taobao.bean.response.PersonResponse>, response: Response<PersonResponse>) {
                if (response.isSuccessful){
                    val temp: PersonResponse? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<cn.cqupt.taobao.bean.response.PersonResponse>, t: Throwable) {
                Log.e("test"," call = $call t=$t")
                callback.onFailure()
            }
        })
    }


    fun register(username: String,password:String,phoneNumber:String,callback: NetUtilResponse<PersonResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.register(
            RegisterBean(data =RegisterBean.Data(username,password, phoneNumber)))
        call.enqueue(object :Callback<PersonResponse>{
            override fun onResponse(call: Call<PersonResponse>, personResponse: Response<PersonResponse>) {
                if (personResponse.isSuccessful){
                    val temp:PersonResponse? = personResponse.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun addGood(good: Good,callback: NetUtilResponse<PersonResponse>){
        val request = retrofit.create(Services::class.java)
        val bitmap :Bitmap
        try {
            bitmap = BitmapFactory.decodeFile(good.pic)
        }catch (e:Exception){
            callback.onFailure()
            return
        }
        val call = request.addGoods(AddGoodBean(data = AddGoodBean.Data(Config.username,good.name,bitmap.toBase64(bitmap),good.price,good.count)))
        call.enqueue(object :Callback<PersonResponse>{
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {
                if (response.isSuccessful){
                    val temp:PersonResponse? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                callback.onFailure()
            }

        })
    }

    fun getGoods(username: String,callback: NetUtilResponse<GoodResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.getGoods(GetGoodsBean(data = GetGoodsBean.Data(username)))
        call.enqueue(object :Callback<GoodResponse>{
            override fun onResponse(
                call: Call<GoodResponse>,
                response: Response<GoodResponse>
            ) {
                if (response.isSuccessful){
                    val temp:GoodResponse? = response.body()
                    if ( temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<GoodResponse>, t: Throwable) {
                callback.onFailure()
            }

        })
    }

    fun delGood(username: String,name:String,callback: NetUtilResponse<PersonResponse>) {
        val request = retrofit.create(Services::class.java)
        val call = request.delGoods(DeleteGoodBean(data = DeleteGoodBean.Data(username, name)))
        call.enqueue(object : Callback<PersonResponse> {
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {
                if (response.isSuccessful) {
                    val temp: PersonResponse? = response.body()
                    if (temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}