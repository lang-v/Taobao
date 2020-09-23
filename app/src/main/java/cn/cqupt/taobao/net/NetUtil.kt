package cn.cqupt.taobao.net

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import cn.cqupt.taobao.bean.Goods
import cn.cqupt.taobao.bean.request.*
import cn.cqupt.taobao.bean.response.GoodsResponse
import cn.cqupt.taobao.bean.response.OrderResponse
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.net.service.Services
import cn.cqupt.taobao.view.toBase64
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetUtil {
    private val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS).writeTimeout(1, TimeUnit.SECONDS).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://47.100.59.154:40000")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun login(username: String, password: String, callback: NetUtilResponse<PersonResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.login(LoginBean(data = LoginBean.Data(username, password)))
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

    fun isRegister(
        username: String,
        callback: NetUtilResponse<cn.cqupt.taobao.bean.response.PersonResponse>
    ){
        val request = retrofit.create(Services::class.java)
        val call = request.isRegister(IsRegisterBean(data = IsRegisterBean.Data(username)))

        call.enqueue(object : Callback<cn.cqupt.taobao.bean.response.PersonResponse> {
            override fun onResponse(
                call: Call<cn.cqupt.taobao.bean.response.PersonResponse>,
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

            override fun onFailure(
                call: Call<cn.cqupt.taobao.bean.response.PersonResponse>,
                t: Throwable
            ) {
                Log.e("test", " call = $call t=$t")
                callback.onFailure()
            }
        })
    }

    fun register(
        username: String,
        password: String,
        phoneNumber: String,
        callback: NetUtilResponse<PersonResponse>
    ){
        val request = retrofit.create(Services::class.java)
        val call = request.register(
            RegisterBean(data = RegisterBean.Data(username, password, phoneNumber))
        )
        call.enqueue(object : Callback<PersonResponse> {
            override fun onResponse(
                call: Call<PersonResponse>,
                personResponse: Response<PersonResponse>
            ) {
                if (personResponse.isSuccessful) {
                    val temp: PersonResponse? = personResponse.body()
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

    fun addGood(goods: Goods, callback: NetUtilResponse<PersonResponse>){
        val request = retrofit.create(Services::class.java)
        val bitmap :Bitmap
        try {
            bitmap = BitmapFactory.decodeFile(goods.pic)
        }catch (e: Exception){
            e.printStackTrace()
            callback.onFailure()
            return
        }
        val call = request.addGoods(
            AddGoodBean(
                data = AddGoodBean.Data(
                    Config.username, goods.name, bitmap.toBase64(
                        bitmap
                    ), goods.price, goods.count
                )
            )
        )
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

    fun getGoods(username: String, callback: NetUtilResponse<GoodsResponse>){
        val request = retrofit.create(Services::class.java)
        val call = request.getGoods(GetGoodsBean(data = GetGoodsBean.Data(username)))
        call.enqueue(object : Callback<GoodsResponse> {
            override fun onResponse(
                call: Call<GoodsResponse>,
                response: Response<GoodsResponse>
            ) {
                if (response.isSuccessful) {
                    val temp: GoodsResponse? = response.body()
                    if (temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<GoodsResponse>, t: Throwable) {
                callback.onFailure()
            }

        })
    }

    fun delGood(username: String, name: String, callback: NetUtilResponse<PersonResponse>) {
        val request = retrofit.create(Services::class.java)
        val call = request.delGoods(DeleteGoodsBean(data = DeleteGoodsBean.Data(username, name)))
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

    fun getRecord(username: String, callback: NetUtilResponse<OrderResponse>) {
        val request = retrofit.create(Services::class.java)
        val call = request.getRecord(GetRecordBean(data = GetRecordBean.Data(username)))
        call.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(
                call: Call<OrderResponse>,
                response: Response<OrderResponse>
            ) {
                if (response.isSuccessful) {
                    val temp: OrderResponse? = response.body()
                    if (temp == null)
                        callback.onFailure()
                    else
                        callback.onSuccess(temp)
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}