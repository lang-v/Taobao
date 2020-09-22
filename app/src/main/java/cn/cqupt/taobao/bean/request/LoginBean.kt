package cn.cqupt.taobao.bean.request

data class LoginBean(val state:String="loginb",val data: Data){
    data class Data(val username:String,val password:String)
}