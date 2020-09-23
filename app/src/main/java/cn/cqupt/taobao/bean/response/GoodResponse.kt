package cn.cqupt.taobao.bean.response


data class GoodResponse(val state:String, val data:Data){
    class Data(val result:Int)
}