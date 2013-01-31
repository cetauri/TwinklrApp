package com.twinklr.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwinklrWebViewClient extends WebViewClient {
    public void onPageStarted(android.webkit.WebView view, java.lang.String url, android.graphics.Bitmap favicon) { /* compiled code */
        return ;
    }

    @Override
	public boolean shouldOverrideUrlLoading(WebView view, String url){
        view.loadUrl(url);
		return true;
		
	}
}
