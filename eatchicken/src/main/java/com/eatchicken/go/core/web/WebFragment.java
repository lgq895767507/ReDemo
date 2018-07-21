package com.eatchicken.go.core.web;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eatchicken.go.BuildConfig;
import com.eatchicken.go.R;

/**
 * com.aihuishou.opt.net.model.brand.A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment implements WebActivity.IActivity {
    private String url;
    private String title;
    private final int REQUEST_FILE_PICKER = 1;
    private ViewHolder viewHolder;
    private boolean hasToolBar = false;

    public WebFragment() {
    }

    public static WebFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("title", "");
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static WebFragment newInstance(String url, String title) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("title", title);
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof WebActivity) {
            ((WebActivity) getActivity()).setActivityListener(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
        title = getArguments().getString("title");
        hasToolBar = !TextUtils.isEmpty(title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_web, container, false);
        viewHolder = new ViewHolder(contentView);
        if (hasToolBar) {
            viewHolder.title.setText(title);
            viewHolder.title.setVisibility(View.VISIBLE);
        } else {
            viewHolder.title.setVisibility(View.GONE);
        }
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }
        viewHolder.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        viewHolder.webview.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        viewHolder.webview.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        viewHolder.webview.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        viewHolder.webview.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        viewHolder.webview.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        viewHolder.webview.getSettings().setAppCacheEnabled(true);//是否使用缓存
        viewHolder.webview.getSettings().setDomStorageEnabled(true);//DOM Storage
        viewHolder.webview.setWebChromeClient(webChromeClient);
        viewHolder.webview.setWebViewClient(webViewClient);//拦截到第三方浏览器的跳转
        viewHolder.webview.loadUrl(url);
    }

    private final WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            viewHolder.progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onLoadResource(WebView view, String url) {

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            viewHolder.progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();//接受所有的网站证书
        }
    };

    private final WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int progress) {// 载入进度改变而触发
            viewHolder.progressBar.setProgress(progress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (!TextUtils.isEmpty(title)) {
                viewHolder.title.setText(title);
            }
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                                 final JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final JsResult result) {
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message,
                                  String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public void onRequestFocus(WebView view) {
            super.onRequestFocus(view);
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            Log.d("tagss", "openFileChooser: fileChooserParams = " + fileChooserParams);
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(Intent.createChooser(intent, "File Chooser"), REQUEST_FILE_PICKER);
            return true;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (viewHolder.webview != null) {
                viewHolder.webview.loadUrl("about:blank");
                CookieSyncManager.createInstance(getActivity());  //Create a singleton CookieSyncManager within a context
                CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
                cookieManager.removeAllCookie();// Removes all cookies.
                CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

                viewHolder.webview.setWebChromeClient(null);
                viewHolder.webview.setWebViewClient(null);
                viewHolder.webview.getSettings().setJavaScriptEnabled(false);
                viewHolder.webview.clearCache(true);
                viewHolder.webview.freeMemory();
                viewHolder.webview.removeAllViews();
                viewHolder.webview.destroy();
            }
            url = null;
            viewHolder = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (viewHolder.webview.canGoBack()) {
            viewHolder.webview.goBack();
        } else {
            getActivity().finish();
        }
    }

    static class ViewHolder {
        WebView webview;
        ProgressBar progressBar;
        TextView title;

        ViewHolder(View view) {
            this.webview = view.findViewById(R.id.webview);
            this.progressBar = view.findViewById(R.id.progress_bar);
            this.title = view.findViewById(R.id.tv_title);
        }
    }
}
