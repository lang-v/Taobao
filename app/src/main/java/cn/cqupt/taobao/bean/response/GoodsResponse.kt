package cn.cqupt.taobao.bean.response

import cn.cqupt.taobao.bean.Goods


data class GoodsResponse(
    val `data`: List<Goods>,
    val state: String
)