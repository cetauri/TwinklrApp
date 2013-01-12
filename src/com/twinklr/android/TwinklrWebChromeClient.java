package com.twinklr.android;

import android.widget.ProgressBar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class TwinklrWebChromeClient extends WebChromeClient {
	 final ProgressBar progressBar;
	 
	 TwinklrWebChromeClient(ProgressBar progressBar){
		 this.progressBar = progressBar;

	 }
	//프로그레스 변경 시 호출
    @Override
    public void onProgressChanged(WebView view, int progress) {

        if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
        	progressBar.setVisibility(ProgressBar.VISIBLE);
//            txtview.setVisibility(View.VISIBLE);
        }
        progressBar.setProgress(progress);
        if(progress == 100) {
        	progressBar.setVisibility(ProgressBar.GONE);
//            txtview.setVisibility(View.GONE);
        }
        
    }
 }
