package com.baseball.scrape.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baseball.common.CommonEnum;
import com.baseball.common.Constants;
import com.baseball.common.UrlCommon;
import com.baseball.dao.ScrapeNpbOfficialDao;
import com.baseball.scrape.dto.NPBStatsHitter;
import com.baseball.scrape.dto.NpbPlayerMasterDTO;
import com.baseball.scrape.dto.NpbStatsPitcher;

/**
 * This Year機能は後回し。
 * マスターデータ&2024の通算成績機能のみ
 * 20240726の日次最新データ更新は、DBからマスタデータ取得（700件程度）後、新規の選手のみ別枠で送信
 */
@Service
public class ScrNpbService {

	@Autowired
	private ScrapeNpbOfficialDao scrapeNpbOfficialDao;

	/**
	 * NPBのサイトのデータ開始年
	 */
	private int firstYear = 2005;

	/**
	 * NPBサイトから選手マスタデータを取得する TODO 現役,引退のチェック,横浜球団変更の時期を変数化
	 * 
	 * @return
	 * @throws IOException
	 */
	public int npbPlayerMasterService() throws IOException {
		// 戻り値
		int insertCount = 0;
		// URLの作成(チームと年度ごとにURLが異なる)
		//　スクレイピングでデータを取得
		//　テーブルに打者データを挿入
		for (CommonEnum.Team team : CommonEnum.Team.getAllTeamInHistory()) {
			// ノーマルなURL
			if (team != CommonEnum.Team.OLDBAYSTARS	&& team != CommonEnum.Team.BAYSTARS) {
				getAndInsertPlayerMaster(team, firstYear,Constants.thisYear);
			// TBS横浜
			} else if (team == CommonEnum.Team.OLDBAYSTARS) {
				getAndInsertPlayerMaster(team, firstYear, 2011);
			// DeNA横浜
			} else {
				getAndInsertPlayerMaster(team, 2012,Constants.thisYear);
			}
		}
		return insertCount;
	}

	/**
	 * ウェブサイトから取得した選手マスタデータをテーブルへ挿入
	 * 
	 * @param sitesSymbol
	 * @param team
	 * @param firstYear
	 * @param toYear
	 * @throws IOException
	 */
	private void getAndInsertPlayerMaster(CommonEnum.Team team, int firstYear, int toYear)
			throws IOException {
		if(!team.equals(CommonEnum.Team.BUFFERORS)) {
			String teamPrefix = team.getPrefix();
			// データ取得は年度ごと
			for (int i = firstYear; i <= toYear; i++) {
				
				// 投手のデータを取得
				System.out.println(UrlCommon.makeNpbPitchUrl(teamPrefix,i).toString());
				Elements scrapedPitcherData = Jsoup.connect(UrlCommon.makeNpbPitchUrl(teamPrefix,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterP(scrapedPitcherData, teamPrefix,i));
				
				// 打者のデータを取得
				System.out.println(UrlCommon.makeNpbBatUrl(teamPrefix,i).toString());
				Elements scrapedDataHitter = Jsoup.connect(UrlCommon.makeNpbBatUrl(teamPrefix,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterH(scrapedDataHitter, teamPrefix,i));
				
			}
		} else {
			String orixValue = "b";
			// データ取得は年度ごと
			for (int i = firstYear; i <= toYear; i++) {
				String orixPrefixBs = getOrixPrefix(i);
				
				// 投手のデータを取得
				System.out.println(UrlCommon.makeNpbPitchUrl(orixPrefixBs,i).toString());
				Elements scrapedPitcherData = Jsoup.connect(UrlCommon.makeNpbPitchUrl(orixPrefixBs,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterP(scrapedPitcherData, orixValue,i));
				
				// 打者のデータを取得
				System.out.println(UrlCommon.makeNpbBatUrl(orixPrefixBs,i).toString());
				Elements scrapedDataHitter = Jsoup.connect(UrlCommon.makeNpbBatUrl(orixPrefixBs,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterH(scrapedDataHitter, orixValue,i));
			}
		}
	}
	
	/**
	 * サイトから取得したデータを、DBへ挿入するDTOへ成形する
	 * @param scrapedData
	 * @param teamPrefix
	 * @param year
	 * @return
	 * @throws IOException
	 */
	private List<NpbPlayerMasterDTO> setPlayerMasterH(Elements scrapedData, String teamPrefix,int year)
			throws IOException {
		// 配列を用意
		List<NpbPlayerMasterDTO> stats = new ArrayList<>();
		// スクレイピングデータの配列数文ループ
		NpbPlayerMasterDTO stat = new NpbPlayerMasterDTO();
		
		for (int i = 2; i < scrapedData.size(); i++) {
			stat = new NpbPlayerMasterDTO();
			stat.setYearOfInfo(year);
			stat.setTeamId(teamPrefix);
//			String[] data = scrapedData.get(i).text().split(" ");
//			stat.setNameAndHandH(data);
			stats.add(stat);
		}
		// 選手情報の配列をリターン
		System.out.println(stats);
		return stats;
	}
	
	/**
	 * サイトから取得したデータを、DBへ挿入するDTOへ成形する
	 * @param scrapedData
	 * @param teamId
	 * @param year
	 * @return
	 * @throws IOException
	 */
	private List<NpbPlayerMasterDTO> setPlayerMasterP(Elements scrapedData, String teamPrefix,int year)
			throws IOException {
		// 配列を用意
		List<NpbPlayerMasterDTO> stats = new ArrayList<>();
		// スクレイピングデータの配列数文ループ
		NpbPlayerMasterDTO stat = new NpbPlayerMasterDTO();
		for (int i = 2; i < scrapedData.size(); i++) {
			stat = new NpbPlayerMasterDTO();
			stat.setYearOfInfo(year);
			stat.setTeamId(teamPrefix);
//			String[] data = scrapedData.get(i).text().split(" ");
			stat.setPosition(CommonEnum.Position.PITCHER.getValue());
//			stat.setNameAndHandP(data);
			stats.add(stat);
		}
		System.out.println(stats);
		// 選手情報の配列をリターン
		return stats;
	}

	/**
	 * NPBサイトから年度別の打者成績を取得する
	 * 
	 * @return
	 * @throws IOException
	 */
	public int npbHitAndPitchStatsPerTeamAndYearService() throws IOException {
		int insertCount = 0;
		// リーグと年情報を引き渡しNPB公式サイトをスクレイピング
		for (CommonEnum.Team team : CommonEnum.Team.getAllTeamInHistory()) {
			// ノーマルなURL
			if (team != CommonEnum.Team.OLDBAYSTARS	&& team != CommonEnum.Team.BAYSTARS) {
				getAndInsertPerTeamAndYear(team, firstYear, Constants.thisYear);
			// TBS横浜
			} else if (team == CommonEnum.Team.OLDBAYSTARS) {
				getAndInsertPerTeamAndYear(team, firstYear, 2011);
			// DeNA
			} else {
				getAndInsertPerTeamAndYear(team, 2012, Constants.thisYear);
			}
		}
		return insertCount;
	}

	/**
	 * ウェブサイトから取得した年度野手データをテーブルへ挿入
	 * 
	 * @param sitesSymbol
	 * @param team
	 * @param firstYear
	 * @param toYear
	 * @throws IOException
	 */
	private void getAndInsertPerTeamAndYear(CommonEnum.Team team, int firstYear, int toYear)
			throws IOException {
		if(!team.equals(CommonEnum.Team.BUFFERORS)) {
			String teamPrefix = team.getPrefix();
			for (int i = firstYear; i < toYear; i++) {
				// 打者の年・チーム別データを取得する
				Elements scrapedDataHitter = Jsoup.connect(UrlCommon.makeNpbBatUrl(teamPrefix,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertHitterPerTeamAndYear(setHittingStats(scrapedDataHitter, teamPrefix,i));
				
				// 投手の年・チーム別データを取得する
				System.out.println(UrlCommon.makeNpbPitchUrl(teamPrefix,i).toString());
				Elements scrapedPitcherData = Jsoup.connect(UrlCommon.makeNpbPitchUrl(teamPrefix,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPitcherPerTeamAndYear(setPitchingStats(scrapedPitcherData, teamPrefix,i));
			}
		} else {
			String orixValue = "b";
			// データ取得は年度ごと
			for (int i = firstYear; i <= toYear; i++) {
				String orixPrefixBs = getOrixPrefix(i);
				// 打者のデータを取得
				System.out.println(UrlCommon.makeNpbBatUrl(orixPrefixBs,i).toString());
				Elements scrapedDataHitter = Jsoup.connect(UrlCommon.makeNpbBatUrl(orixPrefixBs,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertHitterPerTeamAndYear(setHittingStats(scrapedDataHitter, orixValue,i));
				
				// 投手のデータを取得
				System.out.println(UrlCommon.makeNpbPitchUrl(orixPrefixBs,i).toString());
				Elements scrapedPitcherData = Jsoup.connect(UrlCommon.makeNpbPitchUrl(orixPrefixBs,i).toString()).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPitcherPerTeamAndYear(setPitchingStats(scrapedPitcherData, orixValue,i));
			}
		}
	}

	/**
	 * NPBサイトから2024年度の打者成績と投手成績を取得する
	 * 最終的には、更新処理を削除と挿入に変更したい
	 * @return
	 * @throws IOException
	 */
	public int npbStatsThisYearService() throws IOException {
		int insertCount = 0;
		// リーグと年情報を引き渡しNPB公式サイトをスクレイピング
		for (CommonEnum.Team team : CommonEnum.Team.getNow12Teams()) {
			System.out.println(team.getFullName());
			// ウェブサイトから取得した今年の選手データをテーブルへ挿入
			getAndInsertThisYear(team);
		}
		return insertCount;
	}

	/**
	 * ウェブサイトから取得した今年の選手データをテーブルへ１．挿入、２．更新 
	 * 
	 * @param team
	 * @throws IOException
	 */
	private void getAndInsertThisYear(CommonEnum.Team team)
			throws IOException {
		String teamPrefix = team.getPrefix();
		// 打者データの取得
		Elements scrapingData = Jsoup.connect(UrlCommon.makeNpbBatUrl(teamPrefix,Constants.thisYear)).get().select("#stdivmaintbl")
				.first().select("tr");
		
		//データの整形
		List<NPBStatsHitter> hitData = setHittingStats(scrapingData, teamPrefix, Constants.thisYear);
		// マスタテーブルを更新
//		scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterH(scrapingData, teamPrefix,Constants.thisYear));
		// 初出場の選手は事前に登録されていないためまず挿入処理が必要
		scrapeNpbOfficialDao.insertHitterThisYear(hitData);
		// 登録済みの本年データの更新が必要
		scrapeNpbOfficialDao.updateHitterThisYear(hitData);
		// 投手データの取得
		scrapingData = Jsoup.connect(UrlCommon.makeNpbPitchUrl(teamPrefix,Constants.thisYear)).get().select("#stdivmaintbl")
				.first().select("tr");
		// 取得先のURLを表示
//		System.out.println(makePitchUrl(teamPrefix,Constants.thisYear));
		//データの整形
		List<NpbStatsPitcher> pitchData = setPitchingStats(scrapingData, teamPrefix,Constants.thisYear);
		// 初出場の選手は事前に登録されていないためまず挿入処理が必要
		scrapeNpbOfficialDao.insertPitcherThisYear(pitchData);
		// 登録済みの本年データの更新が必要
		scrapeNpbOfficialDao.updatePitcherThisYear(pitchData);
	}
	
	private String urlBsUntil2018 = "bs",urlBFrom2019 = "b";
	
	private String getOrixPrefix(int year) {
		return  (year < 2019) ? urlBsUntil2018 :urlBFrom2019;
	}
	
	/**
	 * サイトから取得したデータを、DBへ挿入するために成形する
	 * 
	 * @param scrapingData
	 * @param teamId
	 * @param pitchOrBat
	 * @param year
	 * @return
	 * @throws IOException
	 */
	private List<NPBStatsHitter> setHittingStats(Elements scrapingData, String teamId, int year)
		throws IOException {
		// 配列を用意
		List<NPBStatsHitter> stats = new ArrayList<>();
		// スクレイピングデータの配列数文ループ
		NPBStatsHitter stat;
		for (int i = 2; i < scrapingData.size(); i++) {
			stat = new NPBStatsHitter();
			stat.setTeamId(teamId);
			stat.setYearOfInfo(year);
			// スクレイピングデータの""を除去
//			String[] data = scrapingData.get(i).text().split(" ");
			List<String> data = Arrays.asList(scrapingData.get(i).text().split(" "));
			
			if(data.get(0).equals("*")) {
				data.set(0, CommonEnum.DominantHand.LEFT.getValue());
			} else if(!data.get(0).equals("+")) {
				data.add(0, CommonEnum.DominantHand.RIGHT.getValue());
			} else {
				data.set(0, CommonEnum.DominantHand.BOTHSIDE.getValue());
			}
			
			stat.setFnameAndLname(data);
			
			// 以降statマップにセット
			Double SluggingPercentage = Double.parseDouble(data.get(22));
			Double OnBasePercentage = Double.parseDouble(data.get(23));

			stat.setGames(Integer.parseInt(data.get(2)));// 試合
			stat.setPlateAppearance(Integer.parseInt(data.get(3)));// 打席
			stat.setAtBats(Integer.parseInt(data.get(4)));// 打数
			stat.setRuns(Integer.parseInt(data.get(5)));// 得点
			stat.setTotalHits(Integer.parseInt(data.get(6)));
			stat.setDoubles(Integer.parseInt(data.get(7)));
			stat.setTriples(Integer.parseInt(data.get(8)));
			stat.setHomeruns(Integer.parseInt(data.get(9)));
			stat.setTotalBases(Integer.parseInt(data.get(10)));// 塁打
			stat.setRbi(Integer.parseInt(data.get(11)));// 打点
			stat.setStolenBases(Integer.parseInt(data.get(12)));
			stat.setStolenBaseDeath(Integer.parseInt(data.get(13)));
			stat.setSacrificeBunts(Integer.parseInt(data.get(14)));
			stat.setSacrificeFlies(Integer.parseInt(data.get(15)));
			stat.setFourBalls(Integer.parseInt(data.get(16)));// 四球
			stat.setGotWalked(Integer.parseInt(data.get(17)));// 故意
			stat.setDeadBalls(Integer.parseInt(data.get(18)));// 死球
			stat.setStrikeOuts(Integer.parseInt(data.get(19)));// 三振
			stat.setDoublePlay(Integer.parseInt(data.get(20)));
			stat.setBattingAverage(Double.parseDouble(data.get(21)));
			stat.setSluggingPercentage(SluggingPercentage);
			stat.setOnBasePercentage(OnBasePercentage);
			stat.setOps(SluggingPercentage + OnBasePercentage);
			// 選手１人分の成績を配列に追加
			stats.add(stat);
		}
		System.out.println(stats);
		// 選手情報の配列をリターン
		return stats;
	}
	
	/**
	 * サイトから取得したデータを、DBへ挿入するために成形する
	 * 
	 * @param scrapingData
	 * @param teamId
	 * @param pitchOrBat
	 * @param year
	 * @return
	 * @throws IOException
	 */
	private List<NpbStatsPitcher> setPitchingStats(Elements scrapingData, String teamId,int year)
		throws IOException {
		// 配列を用意
		List<NpbStatsPitcher> stats = new ArrayList<>();
		// スクレイピングデータの配列数文ループ
		NpbStatsPitcher stat;
		for (int i = 2; i < scrapingData.size(); i++) {
			stat = new NpbStatsPitcher();
			stat.setTeamId(teamId);
			stat.setYearOfInfo(year);
			// スクレイピングデータの""を除去
//			String[] data = scrapingData.get(i).text().split(" ");
			List<String> data = Arrays.asList(scrapingData.get(i).text().split(" "));
			
			if(data.get(0).equals("*")) {
				data.set(0, CommonEnum.DominantHand.LEFT.getValue());
			} else if(!data.get(0).equals("+")) {
				data.add(0, CommonEnum.DominantHand.RIGHT.getValue());
			} else {
				data.set(0, CommonEnum.DominantHand.BOTHSIDE.getValue());
			}
			
			stat.setFnameAndLname(data);
			// 表の左端が "*" or "+"
//				// 以降statマップにセット
			stat.setGames(Integer.parseInt(data.get(2)));// 試合
			stat.setWinNum(Integer.parseInt(data.get(3)));// 打席
			stat.setLoseNum(Integer.parseInt(data.get(4)));// 打数
			stat.setSaveNum(Integer.parseInt(data.get(5)));// 得点
			stat.setHold(Integer.parseInt(data.get(6)));
			stat.setHoldPoint(Integer.parseInt(data.get(7)));
			stat.setCompleteGame(Integer.parseInt(data.get(8)));
			stat.setWhitewashWinNum(Integer.parseInt(data.get(9)));// 塁打
			stat.setNoWalk(Integer.parseInt(data.get(10)));// 打点
			stat.setWinRate(Double.parseDouble(data.get(11)));
			stat.setAgainstBatters(Integer.parseInt(data.get(12)));
			double innings = 0;
			if(!data.get(13).equals("+")) {
				innings = Integer.parseInt(data.get(13));
			};
			if(data.get(14).contains(".")) {
				innings += Double.parseDouble(data.get(14));
				stat.setInnings(innings);
				stat.setHits(Integer.parseInt(data.get(15)));
				stat.setHomeruns(Integer.parseInt(data.get(16)));// 四球
				stat.setFourBalls(Integer.parseInt(data.get(17)));// 四球
				stat.setGotWalked(Integer.parseInt(data.get(18)));// 故意
				stat.setDeadBalls(Integer.parseInt(data.get(19)));// 死球
				stat.setKk(Integer.parseInt(data.get(20)));// 三振
				stat.setWildPitch(Integer.parseInt(data.get(21)));
				stat.setBork(Integer.parseInt(data.get(22)));
				stat.setAllowedRuns(Integer.parseInt(data.get(23)));
				stat.setEarnedRuns(Integer.parseInt(data.get(24)));
				if(data.get(25).equals("----")) {
					stat.setEra(0);
				} else {
					stat.setEra(Double.parseDouble(data.get(25)));
				}
			} else {// 投球回が＋の場合は0
				stat.setInnings(innings);
				stat.setHits(Integer.parseInt(data.get(14)));//投球回
				stat.setHomeruns(Integer.parseInt(data.get(15)));// 四球
				stat.setFourBalls(Integer.parseInt(data.get(16)));// 四球
				stat.setGotWalked(Integer.parseInt(data.get(17)));// 故意
				stat.setDeadBalls(Integer.parseInt(data.get(18)));// 死球
				stat.setKk(Integer.parseInt(data.get(19)));// 三振
				stat.setWildPitch(Integer.parseInt(data.get(20)));
				stat.setBork(Integer.parseInt(data.get(21)));
				stat.setAllowedRuns(Integer.parseInt(data.get(22)));
				stat.setEarnedRuns(Integer.parseInt(data.get(23)));
				if(data.get(24).equals("----")) {
					stat.setEra(0);
				} else {
					stat.setEra(Double.parseDouble(data.get(24)));
				}
			}
			// 選手１人分の成績を配列に追加
			stats.add(stat);
		}
		System.out.println(stats);
		// 選手情報の配列をリターン
		return stats;
	}
}
