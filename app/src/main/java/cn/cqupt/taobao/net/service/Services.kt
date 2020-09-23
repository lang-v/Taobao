package cn.cqupt.taobao.net.service

import cn.cqupt.taobao.bean.request.*
import cn.cqupt.taobao.bean.response.GoodsResponse
import cn.cqupt.taobao.bean.response.OrderResponse
import cn.cqupt.taobao.bean.response.PersonResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HTTP

interface Services {
    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun login(@Body bean: LoginBean): Call<PersonResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun isRegister(@Body json: IsRegisterBean): Call<PersonResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun register(@Body bean: RegisterBean): Call<PersonResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun getGoods(@Body bean:GetGoodsBean):Call<GoodsResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun addGoods(@Body bean:AddGoodBean):Call<PersonResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun delGoods(@Body bean:DeleteGoodsBean):Call<PersonResponse>

    @HTTP(method = "POST", path = "deal", hasBody = true)
    fun getRecord(@Body bean:GetRecordBean):Call<OrderResponse>



}