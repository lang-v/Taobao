package cn.cqupt.taobao.view.fragment.order.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Good
import cn.cqupt.taobao.bean.response.GoodResponse
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.fragment.order.adapter.DataListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sl.view.elasticviewlibrary.ElasticLayout
import sl.view.elasticviewlibrary.base.BaseHeader

class DataPage(private val dataList: ArrayList<Good>):Fragment(), ElasticLayout.OnEventListener {
    private lateinit var adapter: DataListAdapter
    private lateinit var list:RecyclerView
    private lateinit var elasticLayout: ElasticLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        elasticLayout = inflater.inflate(R.layout.fragment_order_data_list_layout,container,false) as ElasticLayout
        elasticLayout.setHeaderAdapter(BaseHeader(requireContext(),200))
        elasticLayout.setOnElasticViewEventListener(this)
        list = elasticLayout.findViewById(R.id.list)
        init()
        return elasticLayout
    }

    private fun init(){
        adapter = DataListAdapter(dataList)
        list.adapter = adapter
        //瀑布流式布局
        list.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter.notifyDataSetChanged()
    }

    override fun onLoad() {

    }

    override fun onRefresh() {
        dataList.clear()
        if (!Config.isLogin) {
            GlobalScope.launch(Dispatchers.Main) {
                elasticLayout.isRefreshing = false
            }
            return
        }
        NetUtil.getGoods(Config.username,object :NetUtilResponse<GoodResponse>{
            override fun onSuccess(t: GoodResponse) {
                Log.e("DataPage","get record${t.data}")
                GlobalScope.launch(Dispatchers.Main) {
                    elasticLayout.isRefreshing = false
                }
            }

            override fun onFailure() {
                GlobalScope.launch(Dispatchers.Main) {
                    elasticLayout.isRefreshing = false
                }
            }
        })
    }
}