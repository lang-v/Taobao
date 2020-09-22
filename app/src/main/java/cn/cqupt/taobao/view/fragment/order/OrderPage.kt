package cn.cqupt.taobao.view.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Good
import cn.cqupt.taobao.view.FragmentAdapter
import cn.cqupt.taobao.view.fragment.order.base.DataPage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.fragment_order_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

class OrderPage : Fragment(), View.OnClickListener {
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
            val dataList = ArrayList<Good>()
            val list = ArrayList<Fragment>()
            list.add(DataPage(dataList))
            list.add(DataPage(dataList))
            list.add(DataPage(dataList))
            adapter = FragmentAdapter(requireActivity(), list)
            mineViewPager.adapter = adapter
//            mineLine.layoutParams.apply { width = getDeviceWidth() }
//            mineViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
//                }
//
//                override fun onPageSelected(position: Int) {
//                    //selectItem(position)
//
//                }
//
//                override fun onPageScrollStateChanged(state: Int) {
//
//                }
//            })

            TabLayoutMediator(tabLayout,mineViewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "全部"
                    1 -> tab.text = "进行中"
                    else -> tab.text = "已完成"
                }
            }.attach()

            mineViewPager.isUserInputEnabled = false

//            overData.setOnClickListener(this@OrderPage)
//            allData.setOnClickListener(this@OrderPage)
//            inData.setOnClickListener(this@OrderPage)
        }
    }

    private fun calcWidthScale(offset: Float): Float {
        var temp = offset
        return abs((temp * temp + 1) /2)
    }

    private fun selectItem(index: Int) {
        when (index) {
            0 -> {
                allData.isSelected = true
                overData.isSelected = false
                inData.isSelected = false
//                mineLine.x = 0f
            }

            1 -> {
                overData.isSelected = true
                allData.isSelected = false
                inData.isSelected = false
//                mineLine.x = getDeviceWidth().toFloat()
            }

            2 -> {
                inData.isSelected = true
                overData.isSelected = false
                allData.isSelected = false
//                mineLine.x = (getDeviceWidth() * 2).toFloat()
            }
        }
    }


//    private fun getDeviceWidth(): Int {
//        return allData.width
//    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.allData -> {
                mineViewPager.currentItem = 0
            }
            R.id.overData -> {
                mineViewPager.currentItem = 1
            }
            R.id.inData -> {
                mineViewPager.currentItem = 2
            }
        }
    }
}