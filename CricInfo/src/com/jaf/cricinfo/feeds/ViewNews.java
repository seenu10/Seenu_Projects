package com.jaf.cricinfo.feeds;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewNews extends Activity {

	private String url="";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		Bundle bundle = this.getIntent().getExtras();
		url = bundle.getString("url");
		WebView webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new HelloWebViewClient());
		final Activity activity = this;

		webview.setWebChromeClient(new WebChromeClient(){

		         public void onProgressChanged(WebView view, int progress) {
		                 activity.setTitle("Loading...");
		                 activity.setProgress(progress * 100);
		                    if(progress == 100)
		                       activity.setTitle(url);
		                 }
		});
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(url);
	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
		
	}
}
