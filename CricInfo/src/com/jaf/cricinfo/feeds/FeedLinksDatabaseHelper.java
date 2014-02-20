package com.jaf.cricinfo.feeds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedLinksDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "feeds.db";
	private static final int DATABASE_VERSION = 1;

	public static final String LINKS_TABLE = "feedSites";
	public static final String LINKS = "links";
	public static final String TITLE = "title";
	public static final String IMAGE = "image";
	public static final String ID = "_id";

	public FeedLinksDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase feedsDb) {
		feedsDb.execSQL("create table " + LINKS_TABLE + "(" + ID
				+ " integer primary key not null," + TITLE + " text not null,"
				+ LINKS + " text not null," + IMAGE + " text not null );");

	}

	@Override
	public void onUpgrade(SQLiteDatabase feedsDb, int arg1, int arg2) {

	}
}
