package com.jaf.cricinfo.feeds;

import java.util.HashMap;


import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class OtherTeamsAdapter extends SimpleCursorAdapter {
	private String[] items, links;
	private Activity context;
	private Cursor cursor;
	private TextView tv;
	private HashMap<Integer, Integer> imageMap;

	public OtherTeamsAdapter(Activity context, Cursor cursor, String[] items) {

		super(context, R.layout.home, cursor, items, null);
		this.items = items;
		this.context = context;
		this.cursor = cursor;
		links = new String[items.length];
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		imageMap = new HashMap<Integer, Integer>();
		
		imageMap.put(0, R.drawable.india);
		imageMap.put(1, R.drawable.bangladesh);
		imageMap.put(2, R.drawable.australia);
		imageMap.put(3, R.drawable.england);
		imageMap.put(4, R.drawable.rsa);
		imageMap.put(5, R.drawable.west_indies);
		imageMap.put(6, R.drawable.nz);
		imageMap.put(7, R.drawable.sl);
		imageMap.put(8, R.drawable.pak);
		imageMap.put(9, R.drawable.zim);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View row = inflater.inflate(R.layout.home, null);
		LinearLayout base = (LinearLayout) row.findViewById(R.id.basePanel);
		ImageView image = (ImageView) base.findViewById(R.id.image);
		TextView label = (TextView) base.findViewById(R.id.label);

		cursor.moveToPosition(position);
		label.setText(cursor.getString(1));
		label.setPadding(15, 20, 5, 5);
		label.setTextColor(Color.BLACK);
		label.setTextSize(20);

		Resources resources = context.getResources();
		int imageResource = (int) imageMap.get(position);
		Drawable drawable = resources.getDrawable(imageResource);
		image.setImageDrawable(drawable);
		image.setPadding(15, 5, 5, 5);
		return row;
	}

	public String[] getLinks() {
		return links;
	}

}
