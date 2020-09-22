package cn.cqupt.taobao.bean

import cn.cqupt.taobao.bean.request.JsonAble

data class Good(val desc:String,val imgPath:String,val money:String):
    JsonAble()