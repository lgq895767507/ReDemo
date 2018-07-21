package com.eatchicken.go.core.main

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewFragment
import com.eatchicken.go.model.MainListModel
import com.eatchicken.go.model.TabModel
import com.eatchicken.go.net.model.NLoadMainReq
import com.eatchicken.go.utils.RecyclerViewLoadMoreHelper
import com.eatchicken.go.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseViewFragment(), MainContract.View, MainTabContract.View, MainListContract.View {

    private val presenter: MainContract.Presenter by lazy { MainPresenter() }
    private val mainTabPresenter: MainTabContract.Presenter by lazy { MainTabPresenter() }
    private val mainListPresenter: MainListContract.Presenter by lazy { MainListPresenter() }

    private val tabList: MutableList<TabModel> = arrayListOf()
    private val tabAdapter: TabAdapter = TabAdapter(tabList)

    private val mainDataList: MutableList<MainListModel> = arrayListOf()
    private val mainListAdapter: MainListAdapter = MainListAdapter(mainDataList)

    private val mainListReq: NLoadMainReq = NLoadMainReq()

    private val loadMoreHelper: RecyclerViewLoadMoreHelper by lazy {
        RecyclerViewLoadMoreHelper(rv_data, srl_data, object : RecyclerViewLoadMoreHelper.Listener {
            override fun onRefreshListener() {
                mainListReq.pageIndex = loadMoreHelper.pageIndex
                mainListPresenter.loadMainList(false, mainListReq)
            }

            override fun onStartLoadMore() {
                mainListReq.pageIndex = loadMoreHelper.pageIndex
                mainListPresenter.loadMainList(true, mainListReq)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attachView(this)
        mainTabPresenter.attachView(this)
        mainListPresenter.attachView(this)

        mainTabPresenter.loadMainTabs()

        tabAdapter.onTabSelectedListener = object : TabAdapter.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                mainListReq.type = tabList[position].tabId
                loadMoreHelper.startSwipeRefresh()
            }
        }
        rv_tab.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_tab.adapter = tabAdapter

        rv_data.layoutManager = LinearLayoutManager(activity)
        rv_data.adapter = mainListAdapter
        rv_data.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
        mainTabPresenter.onStop()
        mainListPresenter.onStop()
    }

    override fun loadMainTabsSuccess(mainTabs: List<TabModel>) {
        tabList.clear()
        tabList.addAll(mainTabs)
        tabAdapter.notifyDataSetChanged()
        if (mainTabs.isNotEmpty()) {
            mainListReq.type = mainTabs[0].tabId
            mainListPresenter.loadMainList(false, mainListReq)
        }
    }

    override fun loadMainTabsFailed(e: Throwable) {
        ToastUtils.showToast(e.message)
    }

    override fun loadMainListSuccess(isLoadMore: Boolean, dataList: List<MainListModel>) {
        loadMoreHelper.loadCompleted(dataList.size)
        if (isLoadMore) {
            val startIndex = mainDataList.size
            mainDataList.addAll(dataList)
            mainListAdapter.notifyItemRangeInserted(startIndex, dataList.size)
        } else {
            mainDataList.clear()
            mainDataList.addAll(dataList)
            mainListAdapter.notifyDataSetChanged()
        }
    }

    override fun loadMainListFailed(isLoadMore: Boolean, e: Throwable) {
        loadMoreHelper.loadFailed()
        ToastUtils.showToast(e.message)
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}