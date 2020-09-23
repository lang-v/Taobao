package cn.cqupt.taobao.bean.request

data class DeleteGoodsBean(val state:String="deleteGoodsb", val data:Data){
    data class Data(val username: String,val name:String)
}