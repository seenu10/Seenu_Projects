package com.jaf.cricinfo.feeds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GetRSSFeeds extends ListActivity {

	private class GetFeedsTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			StringBuilder feeds = new StringBuilder();
			try {
				URL feedUrl = new URL(urlString);
				HttpURLConnection connection = (HttpURLConnection) feedUrl
						.openConnection();
				connection.connect();
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				XMLReader reader = parser.getXMLReader();
				CricInfoRSSHandler handler = new CricInfoRSSHandler();
				reader.setContentHandler(handler);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				reader.parse(new InputSource(in));

				FeedData[] news = handler.getData();
				int count = handler.getCount();

				for (int i = 0; i < count - 1; i++) {
					String[] data = news[i].getData().split(";;");
					feeds.append(data[0] + "::" + data[1] + "::" + data[2]
							+ ";;");
				}
				String[] data = news[count - 1].getData().split(";;");
				feeds.append(data[0] + "::" + data[1] + "::" + data[2]);
				in.close();
				// TODO Auto-generated method stub
				return feeds.toString();
			} catch (Exception e) {
				
				
				return null;
			}
		}

		protected void onPreExecute() {
			showDialog(WAIT_DIALOG_KEY);
		}

		protected void onPostExecute(String feeds) {
			if(feeds==null)
			{
				Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_LONG).show();
				waitDialog.dismiss();
				finish();
			}
			else
			{
			String[] data = feeds.split(";;");
			NewsAdapter adapter = new NewsAdapter(context, data);
			setListAdapter(adapter);
			links = adapter.getLinks();
			waitDialog.dismiss();
			}
			
			
		}
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case WAIT_DIALOG_KEY: {
			waitDialog = new ProgressDialog(this);
			waitDialog.setMessage("Please wait while loading...");
			waitDialog.setIndeterminate(true);
			waitDialog.setCancelable(true);
			return waitDialog;
		}
		
			
			
		
		}
		return null;
	}

	private TextView feedData;
	private static final int WAIT_DIALOG_KEY = 123;
	private static final int Exception_ID=234;
	private ProgressDialog waitDialog;
	private String urlString;
	private Activity context;
	private String[] links;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feeds);
		Bundle bundle = getIntent().getExtras();
		urlString = bundle.getString("url");
		context = this;

	}

	public void onStart() {
		super.onStart();
		new GetFeedsTask().execute();
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		Intent intent = new Intent();
		intent.setAction("com.jaf.cricinfo.VIEW_PAGE");
		intent.putExtra("url", links[position]);
		startActivity(intent);
	}
}
