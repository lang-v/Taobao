package cn.cqupt.taobao.view

import android.app.Activity
import android.widget.Toast

infix fun Activity.show(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
