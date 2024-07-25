package com.baseball.common;

import org.springframework.beans.factory.annotation.Value;

public class UrlCommon {
	
	private static String suffixOfHtml = ".html";
	
	@Value("${url.npb.official.prev}")
	private static String npbScrapeUrl;
	/**
	 * NPBサイトのURLの部品
	 */
	private static String npbMiddleNameOfBatUrl = "/stats/idb1_";
	private static String npbMiddleNameOfPitchUrl = "/stats/idp1_";
	/**
	 * URLの可変部分作成
	 * @param sitesSymbols
	 * @param teamPrefix
	 * @return
	 */
	public static String makeNpbBatUrl(String teamPrefix,int year) {
		StringBuilder suffixUrl = new StringBuilder(npbScrapeUrl);
		suffixUrl.append(year);
		suffixUrl.append(npbMiddleNameOfBatUrl);
		suffixUrl.append(teamPrefix);
		suffixUrl.append(suffixOfHtml);
		return suffixUrl.toString();
	}

	/**
	 * URLの可変部分作成
	 * @param sitesSymbols
	 * @param teamPrefix
	 * @return
	 */
	public static String makeNpbPitchUrl(String teamPrefix,int year) {
		StringBuilder suffixUrl = new StringBuilder(npbScrapeUrl);
		suffixUrl.append(year);
		suffixUrl.append(npbMiddleNameOfPitchUrl);
		suffixUrl.append(teamPrefix);
		suffixUrl.append(suffixOfHtml);
		return suffixUrl.toString();
	}
}
