package com.trustly.emiliano.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Utility class that contains helper methods for handling Html documents.
 * 
 * @author Emiliano Pessôa
 *
 */
public class HtmlUtils {

	public synchronized static String getHtmlTagById(String html, String id) throws Exception {
		return getHtmlTagByContentValue(html, "id=\"" + id + "\"");
	}

	/**
	 * 
	 * Search for an attribute value inside a given tag.
	 * 
	 * @param tag       HTML tag that contains the attribute
	 * @param attribute Tag attribute containing the value to be searched
	 * 
	 * @return The tag value attribute or null if not found.
	 * @throws Exception If an Exception occurs.
	 */
	public synchronized static String getTagAttributeValue(String tag, String attribute) throws Exception {
		return getHtmlAttributesValues(tag, attribute).get(0);
	}

	/**
	 * 
	 * Search for a Tag through its content.
	 * 
	 * @param html         HTML document to be searched
	 * @param contentValue Tag Any value that`s within the tag. It can be part of
	 *                     innerHtml content, attributes...
	 * @return The tag in HTML format
	 * @throws Exception If an Exception occurs.
	 */
	public synchronized static String getHtmlTagByContentValue(String html, String contentValue) throws Exception {
		int index = html.indexOf(contentValue);
		if (index > 0) {
			// search backwards the beginning of the tag
			for (int i = index; i > 0; i--) {
				if (html.charAt(i) == '<') {
					String cutHtml = html.substring(i);
					boolean endBegin = false;
					// search forward the end of the tag
					int inTagCount = 0;
					for (int j = 0; j < cutHtml.length(); j++) {
						if (!endBegin) {
							if (cutHtml.substring(j).startsWith("<") && !cutHtml.substring(j + 1).startsWith("/")) {
								inTagCount++;
							}
							if (cutHtml.substring(j).startsWith("</")) {
								inTagCount--;
							}
						}
						if (endBegin || (inTagCount == 0 && cutHtml.substring(j).startsWith("</"))) {
							endBegin = true;
							if (cutHtml.substring(j).startsWith(">")) {
								return cutHtml.substring(0, j + 1);
							}
						}
					}
					break;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Search attributes values of all Tags that are inside an html document.
	 * 
	 * @param html      HTML document to be searched
	 * @param attribute Attribute name.
	 * @return A list of String containing the values
	 * @throws Exception If an Exception occurs.
	 */
	public synchronized static List<String> getHtmlAttributesValues(String html, String attribute) throws Exception {
		String attrBegin = attribute + "=\"";
		String attrEnd = "\"";
		Matcher matcher = Pattern.compile(attrBegin + "(.*?)" + attrEnd).matcher(html);
		List<String> result = new ArrayList<String>();
		while (matcher.find()) {
			result.add(matcher.group().replace(attrBegin, "").replace(attrEnd, ""));
		}
		return result;
	}

	/**
	 * Rescues the html content of a web page given its address (url).
	 * 
	 * @param url The page address.
	 * 
	 * @return The html content of the address (url).
	 * @throws MalformedURLException - If the URL is invalid
	 * @throws IOException           - If an I/O exception occurs.
	 */
	public synchronized static String getHtmlFromUrl(String url) throws MalformedURLException, IOException {
		URL urlContent = new URL(url);
		URLConnection yc = urlContent.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String html = "", line = "";
		while ((line = in.readLine()) != null) {
			html += line;
		}
		return html;
	}

}
