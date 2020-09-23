package cn.cqupt.taobao.view.fragment.shop.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Goods
import cn.cqupt.taobao.bean.Order
import cn.cqupt.taobao.bean.response.GoodsResponse
import cn.cqupt.taobao.bean.response.OrderResponse
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.fragment.shop.adapter.GoodsListAdapter
import cn.cqupt.taobao.view.fragment.shop.adapter.OrderListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sl.view.elasticviewlibrary.ElasticLayout
import sl.view.elasticviewlibrary.base.BaseHeader

class OrderPage:Fragment(), ElasticLayout.OnEventListener {
    private lateinit var adapter: OrderListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var elasticLayout: ElasticLayout
    private val dataList: ArrayList<Order> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        elasticLayout = inflater.inflate(
            R.layout.fragment_order_data_list_layout,
            container,
            false
        ) as ElasticLayout
        elasticLayout.setHeaderAdapter(BaseHeader(requireContext(), 200))
        elasticLayout.setOnElasticViewEventListener(this)
        recyclerView = elasticLayout.findViewById(R.id.list)
        init()
        return elasticLayout
    }

    private fun init() {
        adapter = OrderListAdapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        if (Config.isLogin)
            elasticLayout.isRefreshing = true
    }

    override fun onLoad() {

    }

    override fun onRefresh() {
        synchronized(dataList) {
            dataList.clear()
        }
        if (!Config.isLogin) {
            GlobalScope.launch(Dispatchers.Main) {
                elasticLayout.isRefreshing = false
            }
            return
        }

        NetUtil.getRecord(Config.username,object :NetUtilResponse<OrderResponse>{
            override fun onSuccess(t: OrderResponse) {
                synchronized(dataList) {
                    dataList.addAll(t.data)
                }
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(this@OrderPage.requireContext(),"加载成功",Toast.LENGTH_SHORT).show()
                    elasticLayout.isRefreshing = false
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure() {
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(this@OrderPage.requireContext(),"加载失败",Toast.LENGTH_SHORT).show()
                    elasticLayout.isRefreshing = false
                }
            }
        })
    }
}