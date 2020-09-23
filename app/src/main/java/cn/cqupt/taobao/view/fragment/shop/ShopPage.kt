package cn.cqupt.taobao.view.fragment.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Goods
import cn.cqupt.taobao.view.FragmentAdapter
import cn.cqupt.taobao.view.fragment.shop.base.GoodsPage
import cn.cqupt.taobao.view.fragment.shop.base.OrderPage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_order_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShopPage : Fragment() {
    private lateinit var adapter: FragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            val dataList = ArrayList<Goods>()
            val list = ArrayList<Fragment>()
            list.add(GoodsPage())
            list.add(OrderPage())
            adapter = FragmentAdapter(requireActivity(), list)
            mineViewPager.adapter = adapter

            TabLayoutMediator(tabLayout,mineViewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "商品"
                    1 -> tab.text = "订单"
                }
            }.attach()

            mineViewPager.isUserInputEnabled = false
        }
    }
}