package com.eatchicken.go.core.main

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewFragment
import com.eatchicken.go.model.TabModel
import com.eatchicken.go.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseViewFragment(), MainTabContract.View {

    private val mainTabPresenter: MainTabContract.Presenter by lazy { MainTabPresenter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainTabPresenter.attachView(this)
        mainTabPresenter.loadMainTabs()
    }

    override fun onStop() {
        super.onStop()
        mainTabPresenter.onStop()
    }

    override fun loadMainTabsSuccess(mainTabs: List<TabModel>) {
        if (mainTabs.isNotEmpty()) {

            mainTabs.map {
                val tab = tb_type?.newTab()
                tab?.text = it.tabName
                tb_type?.addTab(tab!!)
            }

            val linearLayout = tb_type.getChildAt(0) as LinearLayout
            linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            linearLayout.dividerPadding = 50
            linearLayout.dividerDrawable = ContextCompat.getDrawable(activity, R.drawable.shape_tab_divider)

            vp_data.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
                override fun getItem(position: Int) = MainItemPageFragment.newInstance(mainTabs[position].tabId, position)

                override fun getCount() = mainTabs.size

                override fun getPageTitle(position: Int) = mainTabs[position].tabName
            }
            vp_data.offscreenPageLimit = mainTabs.size
            tb_type.setupWithViewPager(vp_data)
        }
    }

    override fun loadMainTabsFailed(e: Throwable) {
        ToastUtils.showToast(e.message)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}