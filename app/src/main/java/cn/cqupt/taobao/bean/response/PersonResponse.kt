package cn.cqupt.taobao.bean.response


data class PersonResponse(val state:String, val data:Data){
    class Data(val result:Int)
}