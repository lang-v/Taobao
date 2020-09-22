package cn.cqupt.taobao.bean.response

import cn.cqupt.taobao.bean.request.JsonAble

data class IsRegisterResponse(val state:String,val data:Data){
    class Data(val result:Int)
}