package cn.cqupt.taobao.view.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.cqupt.taobao.R
import cn.cqupt.taobao.view.show
import com.bumptech.glide.Glide
import com.jhworks.library.ImageSelector
import com.jhworks.library.core.MediaSelectConfig
import com.jhworks.library.core.vo.MediaUiConfigVo
import com.jhworks.library.engine.IEngine
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_publish.*

class PublishActivity : AppCompatActivity(), View.OnClickListener {
    val REQUEST_IMAGE = 500
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
        choosePicture.setOnClickListener(this)
        publishClose.setOnClickListener(this)
        ImageSelector.setImageEngine(GlideEngine())
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

    class GlideEngine : IEngine {
        override fun loadImage(imageView: ImageView, uiConfig: MediaUiConfigVo) {
            Glide.with(imageView)
                .load(uiConfig.path)
                .placeholder(uiConfig.placeholderResId)
                .error(uiConfig.errorResId)
                .override(uiConfig.width, uiConfig.height)
                .centerCrop()
                .into(imageView)
        }
    }

    private fun checkPermission() {
        ImageSelector.startImageAction(
            this, REQUEST_IMAGE, MediaSelectConfig.Builder()
                .setShowCamera(true) //是否展示打开摄像头拍照入口，只针对照片，视频列表无效
                .setOpenCameraOnly(false) //是否只是打开摄像头拍照而已
                .setMaxCount(1)//选择最大集合，默认9
                .setImageSpanCount(4) //自定义列表展示个数，默认3
                .setPlaceholderResId(R.mipmap.ic_launcher) //预览图
                .build()
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 200 && resultCode == RESULT_OK){
            finish()
        }
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                OnChoose(ImageSelector.getSelectResults(data)?.get(0))
            }else
                OnCancel()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun OnChoose(filePath: String?) {
        if (filePath == null){
            show("发生未知错误")
            return
        }
        show("选中$filePath")
        SubmitGoodActivity.start(this, filePath)
    }

    fun OnCancel() {

    }
}