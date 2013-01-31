package com.twinklr.android;

import android.os.AsyncTask;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class TwinklrWebChromeClient extends WebChromeClient {
	 final AsyncTask task;
	 
	 TwinklrWebChromeClient(AsyncTask task){
		 this.task = task;

	 }
	//프로그레스 변경 시 호출
    @Override
    public void onProgressChanged(WebView view, int progress) {
//        task.fi
//        task.execute(progress);
//        task.execute(progress);
//        if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
//        	progressBar.setVisibility(ProgressBar.VISIBLE);
////            txtview.setVisibility(View.VISIBLE);
//        }
//        progressBar.setProgress(progress);
//        if(progress == 100) {
//        	progressBar.setVisibility(ProgressBar.GONE);
////            txtview.setVisibility(View.GONE);
//        }
        
    }
 }
