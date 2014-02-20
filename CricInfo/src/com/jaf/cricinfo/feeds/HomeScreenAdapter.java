package com.jaf.cricinfo.feeds;


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

public class HomeScreenAdapter extends SimpleCursorAdapter {
	private String[] items, links;
	private Activity context;
	private Cursor cursor;
	private TextView tv;

	public HomeScreenAdapter(Activity context, Cursor cursor, String[] items) {

		super(context, R.layout.home, cursor, items, null);
		this.items = items;
		this.context = context;
		this.cursor = cursor;
		links = new String[items.length];

		// TODO Auto-generated constructor stub
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View row = inflater.inflate(R.layout.home, null);
		LinearLayout base = (LinearLayout) row.findViewById(R.id.basePanel);
		ImageView image = (ImageView) base.findViewById(R.id.image);
		TextView label = (TextView) base.findViewById(R.id.label);
		Drawable drawable = null;

		if (position < 2) {
			cursor.moveToPosition(position);
			label.setText(cursor.getString(1));
			label.setPadding(15, 20, 5, 5);
			label.setTextColor(Color.BLACK);
			label.setTextSize(20);
			Resources resources = context.getResources();
			if (position == 0)
				drawable = resources.getDrawable(R.drawable.latestnews);
			else if (position == 1)
				drawable = resources.getDrawable(R.drawable.world);
			else if (position == 2)
				drawable = resources.getDrawable(R.drawable.scores);
			image.setImageDrawable(drawable);
			image.setPadding(15, 5, 5, 5);
		}

		else if (position == 2) {

			Resources resources = context.getResources();
			drawable = resources.getDrawable(R.drawable.ball);
			image.setImageDrawable(drawable);
			label.setText("Teams");
			label.setPadding(15, 20, 5, 5);
			label.setTextColor(Color.BLACK);
			label.setTextSize(20);
			image.setPadding(15, 5, 5, 5);
		}

		return row;
	}

	public String[] getLinks() {
		return links;
	}

}
