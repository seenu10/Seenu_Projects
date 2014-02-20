package com.jaf.cricinfo.feeds;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CricInfoRSSHandler extends DefaultHandler {

	private boolean itemFlag, titleFlag, descriptionFlag, linkFlag;
	private StringBuilder articleString;
	private String titleString, descString, linkString;
	private FeedData[] newsObjects;
	private int newsCount;
	private static final int NEWS_TO_BE_DISPLAYED = 20;

	public CricInfoRSSHandler() {
		itemFlag = false;
		titleFlag = false;
		linkFlag = false;
		descriptionFlag = false;
		newsCount = 0;
		newsObjects = new FeedData[NEWS_TO_BE_DISPLAYED];
	}

	public void startElement(String uri, String name, String qName,
			Attributes atts) throws SAXException {
		if (name.equals("item"))
			itemFlag = true;

		if (itemFlag) {
			if (name.equals("title"))
				titleFlag = true;

			if (name.equals("description"))
				descriptionFlag = true;

			if (name.equals("link"))
				linkFlag = true;

		}
	}

	public void endElement(String uri, String name, String qName)
			throws SAXException {
		if (name.equals("item")) {
			if (itemFlag) {
				articleString.append(titleString + ";;" + descString + ";;"
						+ linkString);
				String[] feedData = articleString.toString().split(";;");
				newsObjects[newsCount] = new FeedData(feedData[0], feedData[1],
						feedData[2]);
				articleString.delete(0, articleString.length());
				newsCount++;
			}

			itemFlag = false;
		}
	}

	public void characters(char ch[], int start, int length) {
		if (itemFlag) {
			if (titleFlag) {
				titleString = new String(ch, start, length);
				titleFlag = false;
			}

			if (descriptionFlag) {
				descString = new String(ch, start, length);
				descriptionFlag = false;
			}

			if (linkFlag) {
				linkString = new String(ch, start, length);
				linkFlag = false;
			}

		}
	}

	public void startDocument() throws SAXException {
		articleString = new StringBuilder();
	}

	@Override
	public void endDocument() throws SAXException {

	}

	public int getCount() {
		return newsCount;
	}

	public FeedData[] getData() {
		return newsObjects;
	}
}
