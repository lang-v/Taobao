package cn.cqupt.taobao.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import cn.cqupt.taobao.view.show
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.username
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), NetUtilResponse<PersonResponse> {

    companion object{
        fun start(context: Fragment){
            val intent = Intent(context.context, LoginActivity::class.java)
            val bundle: Bundle? = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context.requireActivity(),
                context.requireView().findViewById(R.id.mineLogin),
                "loginBtn"
            ).toBundle()
            context.startActivityForResult(intent,100,bundle)
        }

        fun start(context: Context){
            val intent = Intent(context,LoginActivity::class.java)
            (context as Activity).startActivityForResult(intent,100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init(){
        loginBack.setOnClickListener{
            onBackPressed()
        }
        login.setOnClickListener{
            val username:String = username.text.toString()
            val password:String = password.text.toString()
            if (username.isNullOrBlank() || password.isNullOrBlank()){
                show("请补全信息")
                return@setOnClickListener
            }
            NetUtil.login(username, password, this)
        }
    }

    override fun onSuccess(t: PersonResponse) {
        show("登录成功")
        Config.isLogin = true
        Config.username = username.text.toString()
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onFailure() {
        show("登录失败")
        GlobalScope.launch(Dispatchers.Main) {
            (password as TextView).text = ""
        }
    }
}

