package cn.cqupt.taobao.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.cqupt.taobao.R
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.view.FragmentAdapter
import cn.cqupt.taobao.view.fragment.mine.MinePage
import cn.cqupt.taobao.view.fragment.shop.ShopPage
import cn.cqupt.taobao.view.show
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherUI : AppCompatActivity(), View.OnClickListener {
    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        init()
    }

    private fun init(){
        val list = ArrayList<Fragment>()
        //订单、发布、我的信息
        list.add(ShopPage())
        list.add(MinePage())
        adapter = FragmentAdapter(this,list)
        viewPager.adapter = adapter
        //禁止滑动
        viewPager.isUserInputEnabled = false

        order.setOnClickListener(this)
        publishImg.setOnClickListener(this)
        publishText.setOnClickListener(this)
        mine.setOnClickListener(this)

        order.callOnClick()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.order -> {
                viewPager.currentItem = 0

                order.isSelected = true
                publishImg.isSelected = false
                publishText.isSelected = false
                mine.isSelected = false
            }

            R.id.publishImg,R.id.publishText ->{
                if (!Config.isLogin){
                    show("请先登录")
                    viewPager.currentItem = 1
                    LoginActivity.start(this)
                    return
                }
                PublishActivity.start(this)
            }

            R.id.mine ->{
                viewPager.currentItem = 1

                mine.isSelected = true
                publishImg.isSelected = false
                publishText.isSelected = false
                order.isSelected = false
            }
        }
    }

}