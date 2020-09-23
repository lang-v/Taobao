package cn.cqupt.taobao.bean.request

data class AddGoodBean(val state:String="addGoodsb", val data:Data){
    data class Data(val username: String,val name:String, val pic:String, val price:String, val count:String, val mod:String="")
}