package com.twinklr.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	static final int PROGRESS_DIALOG = 0;

	private WebView webView;

	ProgressDialogTask task;
    ProgressDialog progressDialog;
	
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

//		progressDialog = ProgressDialog.show(WebViewActivity.this, "",
//		   		"로딩 중입니다. 잠시 기다려주세요", true);//(ProgressBar) findViewById(R.id.pB1);

//		progressThread = new ProgressThread(handler);
//		progressThread.start();
//		
        task = new ProgressDialogTask();
        task.execute(50);
		webView.setWebViewClient(new TwinklrWebViewClient());
		TwinklrWebChromeClient chromeClient = new TwinklrWebChromeClient(task);
		webView.setWebChromeClient(chromeClient);
		

		webView.loadUrl("http://twinklr.com");
		
////		progressDialog = new ProgressDialog(WebViewActivity.this);
////		progressDialog = ProgressDialog.(WebViewActivity.this, "",
////		   		"로딩 중입니다. 잠시 기다려주세요", true);
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//		progressDialog.setMessage("Loading...");
////		progressThread = new ProgressThread(handler);
////		progressThread.start();
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
	    	webView.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	class ProgressDialogTask extends AsyncTask<ProgressDialog, Integer, Void> {//Params, Progress, Result


        @Override
        protected void onPreExecute() {
            System.out.println("onPreExecute : ");
            progressDialog = new ProgressDialog(WebViewActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer ... progress) {
            progressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Void) {
            // 작업이 완료 된 후 할일
            progressDialog.dismiss();
//            super.onPostExecute();
        }

        @Override
        protected Void doInBackground(ProgressDialog ... paramses){
            publishProgress(paramses[0]);
        }
    }
}