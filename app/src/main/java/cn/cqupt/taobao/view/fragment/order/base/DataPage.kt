package cn.cqupt.taobao.view.fragment.order.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Good
import cn.cqupt.taobao.view.fragment.order.adapter.DataListAdapter

class DataPage(private val dataList: ArrayList<Good>):Fragment() {
    private lateinit var list:RecyclerView
    private lateinit var adapter: DataListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_data_list_layout,container,false)
        list = view.findViewById(R.id.dataList)
        init()
        return view
    }

    private fun init(){
        adapter = DataListAdapter(dataList)
        list.adapter = adapter
        //瀑布流式布局
        list.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter.notifyDataSetChanged()
    }
}