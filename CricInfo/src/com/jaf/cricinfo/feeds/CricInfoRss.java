package com.jaf.cricinfo.feeds;

import java.util.HashMap;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CricInfoRss extends ListActivity {

	private FeedLinksDatabaseHelper feedsHelper;
	private SQLiteDatabase feedsDb;
	private String[] feeds = { /*"Bangladesh News",*/ "Latest News", "Live Scores",
			"India News","Bangladesh News", "Australia News", "England News",
			"South Africa News", "West Indies News", "New Zealand News",
			"Sri Lanka News", "Pakistan News", "Zimbabwe  News" }, images = {
			"bangladesh", "bangladesh",  "india","bangladesh", "australia",
			"england", "south_africa", "bangladesh", "new_zealand",
			"sri_lanka", "pakistan", "zimbabwe" };
	private HashMap<String, String> linksMap;
	private Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.main);
		String[] projection = { FeedLinksDatabaseHelper.ID,
				FeedLinksDatabaseHelper.TITLE, FeedLinksDatabaseHelper.LINKS,
				FeedLinksDatabaseHelper.IMAGE };
		cursor = feedsDb.query(FeedLinksDatabaseHelper.LINKS_TABLE, projection,
				FeedLinksDatabaseHelper.ID + " <=3 ", null, null, null, null);
		int[] to = { android.R.id.text1 };
		String[] values = { FeedLinksDatabaseHelper.TITLE };
		// setListAdapter(new SimpleCursorAdapter(this,
		// android.R.layout.simple_list_item_1, cursor, values, to));
		setListAdapter(new HomeScreenAdapter(this, cursor, values));
	}

	private void init() {
		initMap();
		feedsHelper = new FeedLinksDatabaseHelper(getApplicationContext());
		feedsDb = feedsHelper.getWritableDatabase();
		int i = 0;
		String[] projection = { FeedLinksDatabaseHelper.ID,
				FeedLinksDatabaseHelper.TITLE, FeedLinksDatabaseHelper.LINKS,
				FeedLinksDatabaseHelper.IMAGE };
		cursor = feedsDb.query(FeedLinksDatabaseHelper.LINKS_TABLE, projection,
				null, null, null, null, null);
		int count = cursor.getCount();
		if (count == 0) {
			for (String a : feeds) {
				feedsDb.execSQL("insert into "
						+ FeedLinksDatabaseHelper.LINKS_TABLE + "("
						+ FeedLinksDatabaseHelper.TITLE + ","
						+ FeedLinksDatabaseHelper.LINKS + ","
						+ FeedLinksDatabaseHelper.IMAGE + ") values('" + a
						+ "','" + linksMap.get(a) + "','" + images[i] + "');");
				i++;
			}
		}
	}

	private void initMap() {

		linksMap = new HashMap<String, String>();
	
		linksMap.put("Latest News",
				"http://www.cricinfo.com/rss/content/story/feeds/0.xml");
		linksMap.put("Live Scores",
				"http://static.cricinfo.com/rss/livescores.xml");
		linksMap.put("India News",
				"http://www.cricinfo.com/rss/content/story/feeds/6.xml");
		linksMap.put("Bangladesh News",
		"http://www.cricinfo.com/rss/content/story/feeds/25.xml");
		linksMap.put("Australia News",
				"http://www.cricinfo.com/rss/content/story/feeds/2.xml");
		linksMap.put("England News",
				"http://www.cricinfo.com/rss/content/story/feeds/1.xml");
		linksMap.put("South Africa News",
				"http://www.cricinfo.com/rss/content/story/feeds/3.xml");
		linksMap.put("West Indies News",
				"http://www.cricinfo.com/rss/content/story/feeds/4.xml");
		linksMap.put("New Zealand News",
				"http://www.cricinfo.com/rss/content/story/feeds/5.xml");
		linksMap.put("Sri Lanka News",
				"http://www.cricinfo.com/rss/content/story/feeds/8.xml");
		linksMap.put("Pakistan News",
				"http://www.cricinfo.com/rss/content/story/feeds/7.xml");
		linksMap.put("Zimbabwe  News",
				"http://www.cricinfo.com/rss/content/story/feeds/9.xml");
	}

	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {

		cursor.moveToPosition(position);
		Intent intent = new Intent();
		if (position < 2) {
			intent.setAction("com.jaf.cricinfo.GET_FEEDS");
			intent.putExtra("url", cursor.getString(2));
		} else if (position == 2)
			intent.setAction("com.jaf.cricinfo.OTHER_TEAMS");
		startActivity(intent);
	}

}