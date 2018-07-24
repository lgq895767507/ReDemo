package com.eatchicken.go.core.main

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eatchicken.go.R
import com.eatchicken.go.base.BaseRecyclerViewModel
import com.eatchicken.go.base.mvp.BaseViewFragment
import com.eatchicken.go.model.BannerModel
import com.eatchicken.go.model.MainListModel
import com.eatchicken.go.net.model.NLoadMainReq
import com.eatchicken.go.utils.FixLinearLayoutManager
import com.eatchicken.go.utils.RecyclerViewLoadMoreHelper
import com.eatchicken.go.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_main_item_page.*

/**
 * @author zhangnan
 * @date 2018/7/21
 */
class MainItemPageFragment : BaseViewFragment(), MainListContract.View, MainBannerContract.View {

    private val mainListPresenter: MainListContract.Presenter by lazy { MainListPresenter() }
    private val mainBannerPresenter: MainBannerContract.Presenter by lazy { MainBannerPresenter() }

    private val mainDataList: MutableList<BaseRecyclerViewModel> = arrayListOf()
    private val mainListAdapter: MainListAdapter = MainListAdapter(mainDataList)

    private val mainListReq: NLoadMainReq = NLoadMainReq()

    private var type: Int = 0
    private var position: Int = 0

    private val loadMoreHelper: RecyclerViewLoadMoreHelper by lazy {
        RecyclerViewLoadMoreHelper(rv_data, srl_data, object : RecyclerViewLoadMoreHelper.Listener {
            override fun onRefreshListener() {
                mainListReq.pageIndex = loadMoreHelper.pageIndex
                if (position == 0) {
                    mainBannerPresenter.loadMainBanner()
                } else {
                    mainListPresenter.loadMainList(false, mainListReq)
                }
            }

            override fun onStartLoadMore() {
                mainListReq.pageIndex = loadMoreHelper.pageIndex
                mainListPresenter.loadMainList(true, mainListReq)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_item_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainListPresenter.attachView(this)

        type = arguments.getInt(KEY_TYPE)
        position = arguments.getInt(KEY_POSITION)

        if (position == 0) {
            mainBannerPresenter.attachView(this)
            mainBannerPresenter.loadMainBanner()
        } else {
            mainListReq.type = type
            mainListPresenter.loadMainList(false, mainListReq)
        }

        rv_data.layoutManager = FixLinearLayoutManager(activity)
        rv_data.adapter = mainListAdapter
        rv_data.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    override fun onStop() {
        super.onStop()
        mainListPresenter.onStop()
        if (position == 0) mainBannerPresenter.onStop()
    }

    override fun loadMainListSuccess(isLoadMore: Boolean, dataList: List<MainListModel>) {
        loadMoreHelper.loadCompleted(dataList.size)
        if (isLoadMore) {
            val startIndex = mainDataList.size
            mainDataList.addAll(dataList)
            mainListAdapter.notifyItemRangeInserted(startIndex, dataList.size)
        } else {
            if (position != 0) mainDataList.clear()
            mainDataList.addAll(dataList)
            mainListAdapter.notifyDataSetChanged()
        }
    }

    override fun loadMainListFailed(isLoadMore: Boolean, e: Throwable) {
        loadMoreHelper.loadFailed()
        ToastUtils.showToast(e.message)
    }

    override fun loadMainBannersSuccess(banner: BannerModel) {
        mainDataList.clear()
        mainDataList.add(banner)
        mainListReq.type = type
        mainListPresenter.loadMainList(false, mainListReq)
    }

    override fun loadMainBannerFailed(e: Throwable) {
        ToastUtils.showToast(e.message)
    }

    companion object {

        private const val KEY_TYPE: String = "KEY_TYPE"
        private const val KEY_POSITION: String = "KEY_POSITION"

        fun newInstance(type: Int, position: Int): MainItemPageFragment {
            return MainItemPageFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_TYPE, type)
                    putInt(KEY_POSITION, position)
                }
            }
        }
    }

}