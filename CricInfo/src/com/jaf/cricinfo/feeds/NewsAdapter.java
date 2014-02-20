package com.jaf.cricinfo.feeds;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<String> {
	private String[] items, links;
	private Activity context;

	public NewsAdapter(Activity context, String[] items) {
		super(context, R.layout.news, items);
		this.items = items;
		this.context = context;
		links = new String[items.length];
		// TODO Auto-generated constructor stub
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View row = inflater.inflate(R.layout.news, null);
		LinearLayout itemPanel = (LinearLayout) row
				.findViewById(R.id.itemPanel);
		TextView label = (TextView) itemPanel.findViewById(R.id.title);
		TextView desc = (TextView) itemPanel.findViewById(R.id.desc);
		String data[] = items[position].split("::");
		SpannableString content = new SpannableString(data[0]);
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		label.setText(content);
		desc.setText(data[1]);
		label.setTextSize(20);
		label.setTextColor(Color.BLACK);
		label.setTypeface(Typeface.create("Arial", Typeface.BOLD));
		desc.setTextColor(Color.BLACK);
		desc.setTypeface(Typeface.create("Tahoma", Typeface.NORMAL));
		links[position] = data[2];
		return row;
	}

	public String[] getLinks() {
		return links;
	}
}
