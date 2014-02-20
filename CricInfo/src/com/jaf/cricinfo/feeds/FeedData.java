package com.jaf.cricinfo.feeds;

public class FeedData {

	private String title;
	private String description;
	private String link;

	public FeedData(String title, String description, String link) {
		this.title = title;
		this.description = description;
		this.link = link;
	}

	public String getData() {
		StringBuilder dataString = new StringBuilder();
		dataString.append(title + ";;");
		dataString.append(description + ";;");
		dataString.append(link);
		return dataString.toString();
	}

	public String getTitle() {
		return title;
	}
}
