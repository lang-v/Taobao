package cn.cqupt.taobao

import cn.cqupt.taobao.bean.response.PersonResponse
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.net.callback.NetUtilResponse
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        print("start")
        NetUtil.isRegister("sl",object :NetUtilResponse<PersonResponse>{
            override fun onSuccess(t: PersonResponse) {
                print("$t")
            }
            override fun onFailure() {
                print("查询失败")
            }
        })
    }
}