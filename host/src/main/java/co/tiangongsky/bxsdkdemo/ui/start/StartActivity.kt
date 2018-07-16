package co.tiangongsky.bxsdkdemo.ui.start

import android.content.Intent
import co.bxvip.android.commonlib.utils.CommonInit.ctx
import co.bxvip.sdk.ui.BxStartActivityImpl
import co.tiangongsky.bxsdkdemo.ui.main.MainTestActivity
import com.qihoo360.replugin.RePlugin

class StartActivity : BxStartActivityImpl() {
    override fun toYourMainActivity() {
        // 方式 一启动 activity
//        startActivity(Intent(this, MainTestActivity::class.java))

//        // 方式二 启动插件 activity 界面
//        // 跳到主页 demo
//        val startActivity = RePlugin.startActivity(ctx,
//                RePlugin.createIntent("com.tiangong.android.plugin.demo",
//                        "com.tiangong.android.plugin.demo.MainActivity"))
//        if (startActivity) {
//            finish()
//        } else {
//            println("进入app失败！")
//        }

        // 方式三不使用sdk共有的库
        val startActivity = RePlugin.startActivity(ctx,
                RePlugin.createIntent("com.tiangong.plugin.nosdklib",
                        "com.tiangong.plugin.nosdklib.MainActivity"))
        if (startActivity) {
            finish()
        } else {
            println("进入app失败！")
        }

        finish()
    }
}
