package com.example.recommendmeamovie.ui.login


import android.webkit.WebView
import android.webkit.WebViewClient


class LoginWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        url?.let {
            view?.loadUrl(it)
        }

        return false
    }
}