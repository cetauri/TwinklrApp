package com.twinklr.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.ProgressBar;

public class WebViewActivity extends Activity {

	private WebView webView;
	private ProgressBar progressBar;
	
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		webView = (WebView) findViewById(R.id.webView1);
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		
		webView.setHorizontalScrollBarEnabled(true);
		webView.setVerticalScrollBarEnabled(false);
		webView.setBackgroundColor(0);

		progressBar = (ProgressBar) findViewById(R.id.pB1);
		
		webView.setWebViewClient(new TwinklrWebViewClient());
		TwinklrWebChromeClient chromeClient = new TwinklrWebChromeClient(progressBar);
		webView.setWebChromeClient(chromeClient);
		

		webView.loadUrl("http://twinklr.com");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
	    	webView.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
}