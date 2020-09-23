package cn.cqupt.taobao.bean.response

import cn.cqupt.taobao.bean.Order

data class OrderResponse (val state:String,val data: List<Order>)