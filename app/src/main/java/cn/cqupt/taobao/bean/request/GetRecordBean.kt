package cn.cqupt.taobao.bean.request

data class GetRecordBean(val state:String="getRecordb", val data:Data){
    data class Data(val username: String)
}