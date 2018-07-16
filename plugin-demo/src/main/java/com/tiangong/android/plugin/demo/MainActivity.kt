package com.tiangong.android.plugin.demo

import android.annotation.SuppressLint
import android.view.View
import co.bxvip.ui.tocleanmvp.base.BaseActivity
import com.tiangong.android.plugin.demo.fragment.HomeFragment
import com.tiangong.android.plugin.demo.fragment.Test2Fragment
import com.tiangong.android.plugin.demo.fragment.Test3Fragment
import com.tiangong.android.plugin.demo.fragment.Test4Fragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * ┌───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│ │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│ ┌┐    ┌┐    ┌┐
 * └───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘ └┘    └┘    └┘
 * ┌──┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐┌───┬───┬───┐┌───┬───┬───┬───┐
 * │~`│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp ││Ins│Hom│PUp││N L│ / │ * │ - │
 * ├──┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤├───┼───┼───┤├───┼───┼───┼───┤
 * │Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ ││Del│End│PDn││ 7 │ 8 │ 9 │   │
 * ├────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤└───┴───┴───┘├───┼───┼───┤ + │
 * │Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │             │ 4 │ 5 │ 6 │   │
 * ├─────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤    ┌───┐    ├───┼───┼───┼───┤
 * │Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │    │ ↑ │    │ 1 │ 2 │ 3 │   │
 * ├────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤┌───┼───┼───┐├───┴───┼───┤ E││
 * │Ctrl│Ray │Alt │         Space         │ Alt│code│fuck│Ctrl││ ← │ ↓ │ → ││   0   │ . │←─┘│
 * └────┴────┴────┴───────────────────────┴────┴────┴────┴────┘└───┴───┴───┘└───────┴───┴───┘
 *
 * <pre>
 *     author: vic
 *     time  : 18-5-7
 *     desc  : MainActivity
 * </pre>
 */
class MainActivity : BaseActivity() {


    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("CommitTransaction")
    override fun initView(p0: View?) {
        val fragmentManager = supportFragmentManager
        val beginTransaction = fragmentManager.beginTransaction()
        beginTransaction.replace(R.id.mainContent, HomeFragment())
        beginTransaction.commit()
        subordinate_title_pay.setTitleText("首页")
    }

    override fun initEvent() {
        super.initEvent()
        ll_home_tab1.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val beginTransaction = fragmentManager.beginTransaction()
            beginTransaction.replace(R.id.mainContent, HomeFragment())
            beginTransaction.commit()
            subordinate_title_pay.setTitleText("首页")
        }
        ll_home_tab2.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val beginTransaction = fragmentManager.beginTransaction()
            beginTransaction.replace(R.id.mainContent, Test2Fragment())
            beginTransaction.commit()
            subordinate_title_pay.setTitleText("开奖")
        }
        ll_home_tab3.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val beginTransaction = fragmentManager.beginTransaction()
            beginTransaction.replace(R.id.mainContent, Test3Fragment())
            beginTransaction.commit()
            subordinate_title_pay.setTitleText("走势")
        }
        ll_home_tab4.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val beginTransaction = fragmentManager.beginTransaction()
            beginTransaction.replace(R.id.mainContent, Test4Fragment())
            beginTransaction.commit()
            subordinate_title_pay.setTitleText("我的")
        }
    }
}
