package cn.cqupt.taobao.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.show
import cn.cqupt.taobao.view.toBitmap
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_good_info.*

class GoodsInfoActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun start(context: Context, desc:String, img:String, price:String, count:String){
            val intent = Intent(context,GoodsInfoActivity::class.java)
            intent.putExtra("desc",desc)
            intent.putExtra("img",img)
            intent.putExtra("price",price)
            intent.putExtra("count",count)
            context.startActivity(intent)
        }
    }

    private val description:String by lazy { intent.getStringExtra("desc")!! }
    private val imgPath:String by lazy { intent.getStringExtra("img")!! }
    private val moneyValue:String by lazy { intent.getStringExtra("price")!! }
    private val countValue:String by lazy { intent.getStringExtra("count")!! }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_info)
        init()
    }

    private fun init(){
        (desc as TextView).text = description
        if (imgPath != "") {
            Glide.with(this)
                .load("".toBitmap(imgPath))
                .into(infoImg)
        }
        (infoMoney as TextView).text = moneyValue
        (infoCount as TextView).text = countValue

        infoCancel.setOnClickListener(this)
        infoDelete.setOnClickListener(this)
        infoModify.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            infoCancel->{
                onBackPressed()
            }

            infoDelete->{
                NetUtil.delGood(Config.username,description,object :NetUtilResponse<PersonResponse>{
                    override fun onSuccess(t: PersonResponse) {
                        show("删除成功")
                        finish()
                    }

                    override fun onFailure() {
                        show("删除失败")
                    }
                })
            }

            infoModify ->{
                show("修改商品")
            }
        }
    }
}