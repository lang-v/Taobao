package cn.cqupt.taobao.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.show
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.username
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private var allowRegister = false

    companion object {
        fun start(context: Fragment) {
            val intent = Intent(context.context, RegisterActivity::class.java)
            val bundle: Bundle? = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context.requireActivity(),
                context.requireView().findViewById(R.id.mineRegister),
                "registerBtn"
            ).toBundle()
            context.startActivityForResult(intent, 100, bundle)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init() {
        back.setOnClickListener {
            onBackPressed()
        }
        var time = System.currentTimeMillis()
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (System.currentTimeMillis() - time < 100) {
                    time = System.currentTimeMillis()
                    return
                }

                NetUtil.isRegister(
                    username.text.toString(),
                    object : NetUtilResponse<PersonResponse> {
                        override fun onSuccess(t: PersonResponse) {
//                        if (t.data.result == 1)return
                            if (System.currentTimeMillis() - time > 100) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    tip.text = if (t.data.result == 1) {
                                        allowRegister = true
                                        "账户已存在"
                                    }
                                    else {
                                        allowRegister = false
                                        "账户可用"
                                    }
                                }
                            }
                        }

                        override fun onFailure() {
                            GlobalScope.launch(Dispatchers.Main) {
                                tip.text = "查询失败"
                            }
                        }
                    })
            }
        })

        register.setOnClickListener {
            val un: String = username.text.toString()
            val pwd: String = password.text.toString()
            val pn: String = phoneNumber.text.toString()

            if (un.isNullOrBlank() || pwd.isNullOrBlank() || pn.isNullOrBlank()) {
                show("请补全信息")
                return@setOnClickListener
            }
            if (!allowRegister){
                show("请重修改用户名")
                tip.text = "用户名已存在"
            }
            show("正在注册")
            NetUtil.register(un, pwd, pn, object : NetUtilResponse<PersonResponse> {
                override fun onSuccess(t: PersonResponse) {
                    if (t.data.result == 0) {
                        show("注册成功")
                        //跳转登录
                        LoginActivity.start(this@RegisterActivity)
                        finish()
                    } else {
                        show("注册失败")
                    }
                }

                override fun onFailure() {
                    show("注册失败")
                    GlobalScope.launch(Dispatchers.Main) {
                        (password as TextView).text = ""
                    }
                }
            })

        }
    }
}