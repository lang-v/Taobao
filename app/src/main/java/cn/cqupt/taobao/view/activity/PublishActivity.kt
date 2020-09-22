package cn.cqupt.taobao.view.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.cqupt.taobao.R
import cn.cqupt.taobao.view.show
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycl.chooseavatar.library.OnChoosePictureListener
import com.ycl.chooseavatar.library.UpLoadHeadImageDialog
import com.ycl.chooseavatar.library.YCLTools
import kotlinx.android.synthetic.main.activity_publish.*

class PublishActivity : AppCompatActivity(), View.OnClickListener, OnChoosePictureListener {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PublishActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        init()
    }

    private fun init() {
        YCLTools.getInstance().setOnChoosePictureListener(this)
        choosePicture.setOnClickListener(this)
        publishClose.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.publishClose -> {
                finish()
            }

            R.id.choosePicture -> {
                checkPermission()
            }
        }
    }

    private fun checkPermission() {
        var count = 0
        RxPermissions(this).request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe {
            if (!it) {
                show("没有此权限App无法正常运行")
            }else{
                UpLoadHeadImageDialog(this).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        YCLTools.getInstance().upLoadImage(requestCode, resultCode, data)
        if (requestCode == 200 && resultCode == RESULT_OK){
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun OnChoose(filePath: String?) {
        if (filePath == null){
            show("发生未知错误")
            return
        }
        show("选中$filePath")
        SubmitGoodActivity.start(this, filePath)
    }

    override fun OnCancel() {

    }
}