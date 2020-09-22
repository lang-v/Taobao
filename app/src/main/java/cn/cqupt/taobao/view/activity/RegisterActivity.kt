package cn.cqupt.taobao.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.response.IsRegisterResponse
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.show
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.username
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class RegisterActivity : AppCompatActivity(), NetUtilResponse<ResponseBody> {
    companion object{
        fun start(context: Fragment){
            val intent = Intent(context.context, RegisterActivity::class.java)
            val bundle: Bundle? = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context.requireActivity(),
                context.requireView().findViewById(R.id.mineRegister),
                "registerBtn"
            ).toBundle()
            context.startActivityForResult(intent,100,bundle)

        }
    }
    private var isRegister = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init(){
        back.setOnClickListener{
            onBackPressed()
        }
        username.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                var time = System.currentTimeMillis()
                NetUtil.isRegister(p0.toString(),object :NetUtilResponse<IsRegisterResponse>{
                    override fun onSuccess(t: IsRegisterResponse) {
//                        if (t.data.result == 1)return
                        if (System.currentTimeMillis()-time > 100) {
                            GlobalScope.launch(Dispatchers.Main) {
                                tip.text = if (t.data.result == 0) "账户已存在" else ""
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

        register.setOnClickListener{
            val username:String = username.text.toString()
            val password:String = password.text.toString()
            val phoneNumber:String = phoneNumber.text.toString()

            if (username.isNullOrBlank() || password.isNullOrBlank()||phoneNumber.isNullOrBlank()){
                show("请补全信息")
                return@setOnClickListener
            }
//            NetUtil.register(username, password, phoneNumber,this)
//            跳转登录
            LoginActivity.start(this)
            finish()
        }
    }

    override fun onSuccess(t: ResponseBody) {

    }

    override fun onFailure() {

    }
}