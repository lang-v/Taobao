package cn.cqupt.taobao.view

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.IOException


infix fun Activity.show(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

infix fun Bitmap.toBase64(bitmap: Bitmap?): String {
    var result: String = ""
    var baos: ByteArrayOutputStream? = null
    try {
        if (bitmap != null) {
            baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            baos.flush()
            baos.close()
            val bitmapBytes: ByteArray = baos.toByteArray()
            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            if (baos != null) {
                baos.flush()
                baos.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return result
}

infix fun String.toBitmap(json: String):Bitmap{
    val bytes: ByteArray = Base64.decode(json, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}