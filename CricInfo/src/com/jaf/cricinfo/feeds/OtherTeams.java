package com.jaf.cricinfo.feeds;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class OtherTeams extends ListActivity {

	private Cursor cursor;
	private FeedLinksDatabaseHelper feedsHelper;
	private SQLiteDatabase feedsDb;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		feedsHelper = new FeedLinksDatabaseHelper(getApplicationContext());
		feedsDb = feedsHelper.getWritableDatabase();
		setContentView(R.layout.main);
		String[] projection = { FeedLinksDatabaseHelper.ID,
				FeedLinksDatabaseHelper.TITLE, FeedLinksDatabaseHelper.LINKS,
				FeedLinksDatabaseHelper.IMAGE };
		cursor = feedsDb.query(FeedLinksDatabaseHelper.LINKS_TABLE, projection,
				/*FeedLinksDatabaseHelper.ID + " ==1 or "*/
						 FeedLinksDatabaseHelper.ID + " >= 3", null, null,
				null, null);
		int[] to = { android.R.id.text1 };
		String[] values = { FeedLinksDatabaseHelper.TITLE };
		// setListAdapter(new SimpleCursorAdapter(this,
		// android.R.layout.simple_list_item_1, cursor, values, to));
		setListAdapter(new OtherTeamsAdapter(this, cursor, values));
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {

		cursor.moveToPosition(position);
		Intent intent = new Intent();

		intent.setAction("com.jaf.cricinfo.GET_FEEDS");
		intent.putExtra("url", cursor.getString(2));
		startActivity(intent);
	}
}
