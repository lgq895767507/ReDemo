package com.eatchicken.go.base

import android.app.Fragment
import android.app.ProgressDialog

abstract class BaseFragment : Fragment() {

    protected var progressDialog: ProgressDialog? = null

    protected fun hideProgressLoading() {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    protected fun showProgressLoading() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(activity!!)
        }
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
        }
    }

    override fun onDestroy() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
        super.onDestroy()
    }

}
