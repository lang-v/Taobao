package cn.cqupt.taobao.bean.request

data class RegisterBean(val state:String="registerb", val data: Data){
    data class Data(val username:String,val password:String,val phonenumber:String)
}
