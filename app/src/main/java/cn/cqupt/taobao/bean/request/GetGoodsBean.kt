package cn.cqupt.taobao.bean.request

data class GetGoodsBean(val state:String="getGoodsb", val data:Data){
    data class Data(val username: String)
}