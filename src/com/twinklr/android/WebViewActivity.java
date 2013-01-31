package com.twinklr.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	private WebView webView;

    int _progress;
	ProgressDialogTask task;
    ProgressDialog progressDialog;
	
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

        task = new ProgressDialogTask();

        webView = (WebView) findViewById(R.id.webView1);
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		
		webView.setHorizontalScrollBarEnabled(true);
		webView.setVerticalScrollBarEnabled(false);
		webView.setBackgroundColor(0);

		webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
//                if (progressDialog.isShowing()) {
//                    progressDialog = null;

//                }
            }

            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                task.execute(webView);

//                if (!progressDialog.isShowing()) {
//                    task.execute(view);
//                    progressDialog = new ProgressDialog(activity);
//                    progressDialog.setMessage("Page Loading...");
//                    progressDialog.show();
//                }
//                if(!progressDialog.isShowing()){
//
//                }

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        WebChromeClient chromeClient = new WebChromeClient(){
            //프로그레스 변경 시 호출
            @Override
            public void onProgressChanged(WebView view, int progress) {
                _progress = progress;

            }
        };

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
	
	class ProgressDialogTask extends AsyncTask<WebView, Integer, Integer> {//Params, Progress, Result

        @Override
        protected void onPreExecute() {
            System.out.println("onPreExecute : ");
            progressDialog = new ProgressDialog(WebViewActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Loading...");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer ... progress) {
            progressDialog.setProgress(progress[0]);
            progressDialog.setMessage("Please wait..... "+progress[0] + "%");
        }

        @Override
        protected void onPostExecute(Integer a) {
            // 작업이 완료 된 후 할일
            progressDialog.dismiss();
            super.onPostExecute(a);
        }

        @Override
        protected Integer doInBackground(WebView ... paramses){
            while(_progress < 100){
                publishProgress(_progress);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
            return _progress;
        }
    }
}