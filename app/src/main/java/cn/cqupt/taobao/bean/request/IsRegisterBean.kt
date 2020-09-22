package cn.cqupt.taobao.bean.request

data class IsRegisterBean(val state:String="isRegisterb", val data: Data){
    data class Data(val username:String)
}