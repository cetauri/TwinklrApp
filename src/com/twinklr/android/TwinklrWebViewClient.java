package com.twinklr.android;

import android.webkit.WebViewClient;
import android.webkit.WebView;

public class TwinklrWebViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url){
		view.loadUrl(url);
		return true;
		
	}
}
