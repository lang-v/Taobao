package cn.cqupt.taobao.view.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Good
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.show
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_submit_good.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileDescriptor
import kotlin.concurrent.thread

class SubmitGoodActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        fun start(context: Activity, imgPath: String) {
            val intent = Intent(context, SubmitGoodActivity::class.java)
            intent.putExtra("imgPath", imgPath)
            context.startActivityForResult(intent, 200)
        }
    }

    private val imgPath: String by lazy {
        intent.getStringExtra("imgPath")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_good)
        init()
    }

    private fun init() {
        val imgFile = File(imgPath)
        Glide.with(submitImg)
            .load(imgFile)
            .into(submitImg)

        cancel.setOnClickListener(this)
        submit.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.cancel -> {
                finish()
            }

            R.id.submit -> {
                show("开始上传")
                GlobalScope.launch {
                    val bean = getContent()
                    NetUtil.addGood(bean, object : NetUtilResponse<PersonResponse> {
                        override fun onSuccess(t: PersonResponse) {
                            if (t.data.result == 0)
                                GlobalScope.launch(Dispatchers.Main) {
                                    show("商品发布成功")
                                    setResult(RESULT_OK)
                                    finish()
                                }
                            else
                                GlobalScope.launch(Dispatchers.Main) {
                                    show("发生错误，请重试")
                                }
                        }

                        override fun onFailure() {
                            GlobalScope.launch(Dispatchers.Main) {
                                show("发生错误，请重试")
                            }
                        }
                    })
                    //netutil

                }
            }
        }
    }


    private fun getContent(): Good {
        val description: String = desc.text.toString()
        val price: String = money.text.toString()
        val count: String = submitCont.text.toString()
        return Good(description, imgPath, price, count)
    }

}