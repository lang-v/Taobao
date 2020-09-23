package cn.cqupt.taobao.view.fragment.mine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.cqupt.taobao.R
import cn.cqupt.taobao.config.Config
import cn.cqupt.taobao.view.activity.LoginActivity
import cn.cqupt.taobao.view.activity.RegisterActivity
import kotlinx.android.synthetic.main.fragment_mine_layout.*
import kotlin.math.log

class MinePage:Fragment(), View.OnClickListener {
    private var isLogin = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mine_layout,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init(){
        mineLogin.setOnClickListener(this)
        mineRegister.setOnClickListener(this)
    }

    //requestCode = 100
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && resultCode == Activity.RESULT_OK){
            isLogin = true
            shopName.text = Config.username
            mineLogin.text = "退出"
            mineRegister.visibility = View.GONE
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.mineLogin->{
                if (isLogin)
                    logout()
                else
                    login()
            }
            R.id.mineRegister->{
                RegisterActivity.start(this)
            }
        }
    }

    private fun login(){
        LoginActivity.start(this)
    }

    private fun logout(){
        isLogin = false
        Config.isLogin = false
        Config.username = ""
        mineLogin.text = "登录"
        mineRegister.visibility = View.VISIBLE
    }
}