package cn.cqupt.taobao.bean.request

data class DeleteGoodBean(val state:String="deleteGoodsb", val data:Data){
    data class Data(val username: String,val name:String)
}