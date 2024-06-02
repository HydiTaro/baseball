package com.baseball.scrape.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baseball.common.Constants;
import com.baseball.dao.ScrapeYahooDao;
import com.baseball.scrape.dto.YahooStarting;

/**
 *　打順データ取得
 */
@Service
public class ScrYahooService {

	@Autowired
	private ScrapeYahooDao scrapeYahooDao;

	/**
	 * YahooのサイトのURLの部品
	 */
	@Value("${url.yahoo.prev}")
	private String scrapeUrl;

	private String urlTop = "/top";
	/**
	 * Yahooのサイトの試合No
	 */
	private static int gameNo = 2021020309;

	public void startingPerGameService() {

	}

	public int getAndInsertStarting() {
		int insertCount = 0;
		List<YahooStarting> startings = new ArrayList<>();
		int temp = 0;
		for (temp = 0; temp < 6; temp++) {
			System.out.println(makeUrl(temp + gameNo));
			try {
				Elements scrapedStartingData = Jsoup.connect(makeUrl(temp + gameNo)).get().select("#async-starting")
						.first().select("tr");
				if(scrapedStartingData.isEmpty()) {
					break;
				}
				setStarting(startings, temp, scrapedStartingData);
			} catch (IOException e) {
				break;
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		scrapeYahooDao.insertScrapeYahoo(startings);
		return insertCount;
	}

	/**
	 * サイトから取得したデータを、DBへ挿入するDTOへ成形する
	 */
	private void setStarting(List<YahooStarting> twoStarting, int temp, Elements scrapedStarting) {
		YahooStarting startings = YahooStarting.builder().gameId(temp + gameNo).build();
		String[][] scrapedData = new String[9][6];
		// スクレイピングデータの配列数文ループ
		for (int i = 3; i < 12; i++) {
			String[] data = scrapedStarting.get(i).text().split(" ");
			System.out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3]);
			// 外国人選手
			if (data[3].equals(Constants.Kanji_Migi) || data[3].equals(Constants.Kanji_Hidari)) {
				data[3] = data[2];
			}
			scrapedData[i - 3] = data;
		}
		setStringArrayToDto(startings,scrapedData);
		System.out.println(startings.getFname_6()+" "+startings.getPosition_9());
		twoStarting.add(startings);
		
		startings = YahooStarting.builder().gameId(temp + gameNo).build();
		scrapedData = new String[9][6];
		// スクレイピングデータの配列数文ループ
		for (int i = 15; i < 24; i++) {
			String[] data = scrapedStarting.get(i).text().split(" ");
			System.out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3]);
			// 外国人選手
			if (data[3].equals(Constants.Kanji_Migi) || data[3].equals(Constants.Kanji_Hidari)) {
				data[3] = data[2];
			}
			scrapedData[i - 15] = data;
		}
		setStringArrayToDto(startings,scrapedData);
		System.out.println(startings.getFname_2()+" "+startings.getPosition_8());
		twoStarting.add(startings);
		updateGameNo(1);
		System.out.println(twoStarting.get(0).getGameId()+" "+twoStarting.get(0).getPosition_1());
		System.out.println(twoStarting.get(1).getGameId()+" "+twoStarting.get(1).getPosition_3());
	}

	private void updateGameNo(int insertCount) {
		gameNo += insertCount;
	}

	/**
	 * URLの可変部分作成
	 * 
	 * @param sitesSymbols
	 * @param teamPrefix
	 * @return
	 */
	private String makeUrl(int madeGameNo) {
		StringBuilder targetUrl = new StringBuilder(scrapeUrl);
		targetUrl.append(madeGameNo);
		targetUrl.append(urlTop);
		return targetUrl.toString();
	}
	
	private void setStringArrayToDto(YahooStarting startings, String[][] scrapedData) {
		startings.setPosition_1(changeKanjiToInt(scrapedData[0][1]));
		startings.setFname_1(scrapedData[0][2]);
		startings.setLname_1(scrapedData[0][3]);
		startings.setPosition_2(changeKanjiToInt(scrapedData[1][1]));
		startings.setFname_2(scrapedData[1][2]);
		startings.setLname_2(scrapedData[1][3]);
		startings.setPosition_3(changeKanjiToInt(scrapedData[2][1]));
		startings.setFname_3(scrapedData[2][2]);
		startings.setLname_3(scrapedData[2][3]);
		startings.setPosition_4(changeKanjiToInt(scrapedData[3][1]));
		startings.setFname_4(scrapedData[3][2]);
		startings.setLname_4(scrapedData[3][3]);
		startings.setPosition_5(changeKanjiToInt(scrapedData[4][1]));
		startings.setFname_5(scrapedData[4][2]);
		startings.setLname_5(scrapedData[4][3]);
		startings.setPosition_6(changeKanjiToInt(scrapedData[5][1]));
		startings.setFname_6(scrapedData[5][2]);
		startings.setLname_6(scrapedData[5][3]);
		startings.setPosition_7(changeKanjiToInt(scrapedData[6][1]));
		startings.setFname_7(scrapedData[6][2]);
		startings.setLname_7(scrapedData[6][3]);
		startings.setPosition_8(changeKanjiToInt(scrapedData[7][1]));
		startings.setFname_8(scrapedData[7][2]);
		startings.setLname_8(scrapedData[7][3]);
		startings.setPosition_9(changeKanjiToInt(scrapedData[8][1]));
		startings.setFname_9(scrapedData[8][2]);
		startings.setLname_9(scrapedData[8][3]);
	}

	/**
	 * URLの可変部分作成
	 * 
	 * @param sitesSymbols
	 * @param teamPrefix
	 * @return
	 */
	private int changeKanjiToInt(String kanjiPojistion) {
		switch (kanjiPojistion) {
		case Constants.Kanji_Pitcher:
			return 1;
		case Constants.Kanji_Catcher:
			return 2;
		case Constants.Kanji_First:
			return 3;
		case Constants.Kanji_Second:
			return 4;
		case Constants.Kanji_Third:
			return 5;
		case Constants.Kanji_Short:
			return 6;
		case Constants.Kanji_Left:
			return 7;
		case Constants.Kanji_Center:
			return 8;
		case Constants.Kanji_Right:
			return 9;
		case Constants.Kanji_Dh:
			return 0;
		}
		return 99999;
	}

}
