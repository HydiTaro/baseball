package com.baseball.scrape.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baseball.common.CommonEnum;
import com.baseball.common.Constants;
import com.baseball.dao.ScrapeNpbOfficialDao;
import com.baseball.scrape.dto.NPBStatsHitter;
import com.baseball.scrape.dto.NpbPlayerMasterDTO;
import com.baseball.scrape.dto.NpbStatsPitcher;

@Service
public class ScrNpbService {

	@Autowired
	private ScrapeNpbOfficialDao scrapeNpbOfficialDao;
	/**
	 * NPBのサイトのURLの部品
	 */
	@Value("${url.npb.official.prev}")
	private String scrapeUrl;
	/**
	 * NPBのサイトのURLの部品
	 */
	private String stats = "/stats/id";
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
	public int npbPlayerMaster() throws IOException {
		// 戻り値
		int insertCount = 0;
		List<String> sitesSymbols = new ArrayList<String>(Arrays.asList("b","p"));
		// URLの作成(チームと年度ごとにURLが異なる)
		//　スクレイピングでデータを取得
		//　テーブルに打者データを挿入
		for (CommonEnum.Team team : CommonEnum.Team.getAllTeam()) {
			// ノーマルなURL
			if (team != CommonEnum.Team.BUFFERORS && team != CommonEnum.Team.OLDBAYSTARS
					&& team != CommonEnum.Team.BAYSTARS) {
				getAndInsertMaster(sitesSymbols,team, firstYear,Constants.thisYear);
				// オリックスは年度によってURLがイレギュラー
			} else if (team == CommonEnum.Team.BUFFERORS) {
				// URLに使用されている識別子（投手または打者）
				List<String> pitchOrBat = Arrays.asList("p", "b");
				// オリックス：2018年まで
				for (int i = firstYear; i <= 2018; i++) {
					StringBuilder url = new StringBuilder(scrapeUrl);
					url.append(i);
					url.append(stats);
					for (String s : pitchOrBat) {
						StringBuilder suffixUrl = new StringBuilder();
						suffixUrl.append(s);
						suffixUrl.append(middleNameOfHtml);
						suffixUrl.append("bs");
						suffixUrl.append(suffixOfHtml);
						System.out.println(url + suffixUrl.toString());
						if(s.equals("b")) {
							System.out.println(url.toString() + suffixUrl.toString());
							Elements scrapedData = Jsoup.connect(url.toString() + suffixUrl.toString()).get()
									.select("#stdivmaintbl").first().select("tr");
//							setPlayerMasterH(masterHitData,scrapedDataHitter, team.getValue(),i);
							scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterP(scrapedData,team.getValue(),i));
						} else if(s.equals("p")) {
							Elements scrapedData = Jsoup.connect(url.toString() + suffixUrl.toString()).get()
									.select("#stdivmaintbl").first().select("tr");
//							setPlayerMasterP(masterPitchData,scrapedDataPitcher, team.getValue(),i);
							scrapeNpbOfficialDao.updatePlayerMasterThrowHand(setPlayerMasterH(scrapedData,team.getValue(),i));
						}
//						Elements scrapingData = Jsoup.connect(url.toString() + suffixUrl.toString()).get()
//								.select("#stdivmaintbl").first().select("tr");
//						insertCount += scrapeNpbOfficialDao
//								.insertPlayerMaster(setPlayerMaster(scrapingData, team.getValue(), s, i));
					}
				}
				// オリックス：2019年から
				getAndInsertMaster(sitesSymbols,team, 2019, Constants.thisYear);
				// TBS横浜
			} else if (team == CommonEnum.Team.OLDBAYSTARS) {
				getAndInsertMaster(sitesSymbols,team, firstYear, 2011);
				// DeNA横浜
			} else {
				getAndInsertMaster(sitesSymbols,team, 2012,Constants.thisYear);
			}
//			scrapeNpbOfficialDao.insertPlayerMaster(masterHitData);
//			scrapeNpbOfficialDao.updatePlayerMasterThrowHand(masterPitchData);
		}
		
		
		return insertCount;
	}

	/**
	 * URLの部品
	 */
	private String middleNameOfHtml = "1_";
	/**
	 * URLの部品
	 */
	private String suffixOfHtml = ".html";
	private String urlP = "p";
	private String urlB = "b";

	/**
	 * ウェブサイトから取得した選手マスタデータをテーブルへ挿入
	 * 
	 * @param sitesSymbol
	 * @param team
	 * @param firstYear
	 * @param toYear
	 * @throws IOException
	 */
	private void getAndInsertMaster(List<String> sitesSymbols,CommonEnum.Team team, int firstYear, int toYear)
			throws IOException {
		// URLに使用されている識別子（投手または打者）
			for (int i = firstYear; i <= toYear; i++) {
				StringBuilder url = new StringBuilder(scrapeUrl);
				url.append(i);
				url.append(stats);
				String teamPrefix = team.getPrefix();
//				for (String s : sitesSymbols) {
//					if(s.equals("b")) {
						System.out.println(url + makeUrl(urlB, teamPrefix));
						Elements scrapedDataHitter = Jsoup.connect(url.toString() + makeUrl(urlB, teamPrefix)).get()
								.select("#stdivmaintbl").first().select("tr");
						scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterH(scrapedDataHitter, team.getValue(),i));
//					} else if(s.equals("p")) {
						System.out.println(url + makeUrl(urlP, teamPrefix));
						Elements scrapedDataPitcher = Jsoup.connect(url.toString() + makeUrl(urlP, teamPrefix)).get()
								.select("#stdivmaintbl").first().select("tr");
						scrapeNpbOfficialDao.updatePlayerMasterThrowHand(setPlayerMasterP(scrapedDataPitcher, team.getValue(),i));
//					}
//				}
			}
	}

	/**
	 * サイトから取得したデータを、DBへ挿入するDTOへ成形する
	 * 
	 * @param scrapingData
	 * @param teamId
	 * @param pitchOrBat
	 * @param year
	 * @return
	 * @throws IOException
	 */
	private List<NpbPlayerMasterDTO> setPlayerMasterP(Elements scrapedData, String teamId,int year)
			throws IOException {
		// 配列を用意
		List<NpbPlayerMasterDTO> stats = new ArrayList<>();
		// スクレイピングデータの配列数文ループ
		NpbPlayerMasterDTO stat = new NpbPlayerMasterDTO();
		for (int i = 2; i < scrapedData.size(); i++) {
			stat = new NpbPlayerMasterDTO();
			stat.setYearOfInfo(year);
			stat.setTeamId(teamId);
			String[] data = scrapedData.get(i).text().split(" ");
				stat.setPosition(CommonEnum.Position.Pitcher.getValue());
				stat.setNameAndHandP(data);
			stats.add(stat);
		}
		System.out.println(" ");
		// 選手情報の配列をリターン
		return stats;
	}
	
	/**
	 * サイトから取得したデータを、DBへ挿入するDTOへ成形する
	 * 
	 * @param scrapingData
	 * @param teamId
	 * @param pitchOrBat
	 * @param year
	 * @return
	 * @throws IOException
	 */
	private List<NpbPlayerMasterDTO> setPlayerMasterH(Elements scrapedData, String teamId,int year)
			throws IOException {
		// 配列を用意
		List<NpbPlayerMasterDTO> stats = new ArrayList<>();
		// スクレイピングデータの配列数文ループ
		NpbPlayerMasterDTO stat = new NpbPlayerMasterDTO();
		for (int i = 2; i < scrapedData.size(); i++) {
			stat = new NpbPlayerMasterDTO();
			stat.setYearOfInfo(year);
			stat.setTeamId(teamId);
			String[] data = scrapedData.get(i).text().split(" ");
			stat.setNameAndHandH(data);
//			list.add(stat);
			stats.add(stat);
		}
		System.out.println(" ");
		// 選手情報の配列をリターン
		return stats;
	}

	/**
	 * NPBサイトから年度別の打者成績を取得する
	 * 
	 * @return
	 * @throws IOException
	 */
	public int npbHitAndPitchStatsPerTeamAndYear() throws IOException {
		StringBuilder url;
		// URLに使用されている識別子（投手または打者）
		List<String> pitchOrBat = Arrays.asList("b");
		int insertCount = 0;
		// リーグと年情報を引き渡しNPB公式サイトをスクレイピング
		for (CommonEnum.Team team : CommonEnum.Team.getAllTeam()) {
			// ノーマルなURL
			if (team != CommonEnum.Team.BUFFERORS && team != CommonEnum.Team.OLDBAYSTARS
					&& team != CommonEnum.Team.BAYSTARS) {
				getAndInsertHitAndPitchData(pitchOrBat, team, firstYear, Constants.thisYear);
				// オリックスは年度によってURLがイレギュラー
			} else if (team == CommonEnum.Team.BUFFERORS) {
				// オリックス：2018年まで
				for (int i = firstYear; i <= 2018; i++) {
					url = new StringBuilder(scrapeUrl);
					url.append(i);
					url.append(stats);
					for (String s : pitchOrBat) {
						StringBuilder suffixUrl = new StringBuilder();
						suffixUrl.append(s);
						suffixUrl.append(middleNameOfHtml);
						suffixUrl.append("bs");
						suffixUrl.append(suffixOfHtml);
						System.out.println(url + suffixUrl.toString());
						Elements scrapingData = Jsoup.connect(url.toString() + suffixUrl.toString()).get()
								.select("#stdivmaintbl").first().select("tr");
						insertCount += scrapeNpbOfficialDao
								.insertHitterPerTeamAndYear(setHittingStats(scrapingData, team.getValue(), i));
					}
				}
				// オリックス：2019年から
				getAndInsertHitAndPitchData(pitchOrBat, team, 2019, Constants.thisYear);
				
			} else if (team == CommonEnum.Team.OLDBAYSTARS) {// TBS横浜
				getAndInsertHitAndPitchData(pitchOrBat, team, firstYear, 2011);
			} else {// DeNA
				getAndInsertHitAndPitchData(pitchOrBat, team, 2012, Constants.thisYear);
			}
		}
		return insertCount;
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
			String[] data = scrapingData.get(i).text().split(" ");
			stat.setNameAndHandH(data);
//			System.out.print(data[0]);
			// 表の左端が "*" or "+"
			if (data[0].equals("*") || data[0].equals("+")) {
				// 以降statマップにセット
				Double SluggingPercentage = Double.parseDouble(data[22]);
				Double OnBasePercentage = Double.parseDouble(data[23]);

				stat.setGames(Integer.parseInt(data[2]));// 試合
				stat.setAtBats(Integer.parseInt(data[4]));// 打数
				stat.setPlateAppearance(Integer.parseInt(data[3]));// 打席
				stat.setRuns(Integer.parseInt(data[5]));// 得点
				stat.setTotalHits(Integer.parseInt(data[6]));
				stat.setDoubles(Integer.parseInt(data[7]));
				stat.setTriples(Integer.parseInt(data[8]));
				stat.setHomeruns(Integer.parseInt(data[9]));
				stat.setTotalBases(Integer.parseInt(data[10]));// 塁打
				stat.setRbi(Integer.parseInt(data[11]));// 打点
				stat.setStolenBases(Integer.parseInt(data[12]));
				stat.setStolenBaseDeath(Integer.parseInt(data[13]));
				stat.setSacrificeBunts(Integer.parseInt(data[14]));
				stat.setSacrificeFlies(Integer.parseInt(data[15]));
				stat.setFourBalls(Integer.parseInt(data[16]));// 四球
				stat.setGotWalked(Integer.parseInt(data[17]));// 故意
				stat.setDeadBalls(Integer.parseInt(data[18]));// 死球
				stat.setStrikeOuts(Integer.parseInt(data[19]));// 三振
				stat.setDoublePlay(Integer.parseInt(data[20]));
				stat.setBattingAverage(Double.parseDouble(data[21]));
				stat.setSluggingPercentage(SluggingPercentage);
				stat.setOnBasePercentage(OnBasePercentage);
				stat.setOps(SluggingPercentage + OnBasePercentage);
			} else {
				// 以降statマップにセット
				Double SluggingPercentage = Double.parseDouble(data[21]);
				Double OnBasePercentage = Double.parseDouble(data[22]);
//				System.out.println(data[0]);
				stat.setGames(Integer.parseInt(data[1]));
				stat.setAtBats(Integer.parseInt(data[2]));
				stat.setPlateAppearance(Integer.parseInt(data[3]));
				stat.setRuns(Integer.parseInt(data[4]));
				stat.setTotalHits(Integer.parseInt(data[5]));
				stat.setDoubles(Integer.parseInt(data[6]));
				stat.setTriples(Integer.parseInt(data[7]));
				stat.setHomeruns(Integer.parseInt(data[8]));
				stat.setTotalBases(Integer.parseInt(data[9]));
				stat.setRbi(Integer.parseInt(data[10]));
				stat.setStolenBases(Integer.parseInt(data[11]));
				stat.setStolenBaseDeath(Integer.parseInt(data[12]));
				stat.setSacrificeBunts(Integer.parseInt(data[13]));
				stat.setSacrificeFlies(Integer.parseInt(data[14]));
				stat.setFourBalls(Integer.parseInt(data[15]));// 四球
				stat.setGotWalked(Integer.parseInt(data[16])); // 故意四球
				stat.setDeadBalls(Integer.parseInt(data[17]));
				stat.setStrikeOuts(Integer.parseInt(data[18]));
				stat.setDoublePlay(Integer.parseInt(data[19]));
				stat.setBattingAverage(Double.parseDouble(data[20]));
				stat.setSluggingPercentage(SluggingPercentage);
				stat.setOnBasePercentage(OnBasePercentage);
				stat.setOps(SluggingPercentage + OnBasePercentage);
			}
			// 選手１人分の成績を配列に追加
			stats.add(stat);
		}
		// 選手情報の配列をリターン
		return stats;
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
	private void getAndInsertHitAndPitchData(List<String> sitesSymbols, CommonEnum.Team team, int firstYear, int toYear)
			throws IOException {
		for (int i = firstYear; i < toYear; i++) {
			StringBuilder url = new StringBuilder(scrapeUrl);
			url.append(i);
			url.append(stats);
			String teamPrefix = team.getPrefix();
//			for (String s : sitesSymbols) {
				System.out.println(url + makeUrl(urlB, teamPrefix));
				Elements scrapingData = Jsoup.connect(url.toString() + makeUrl(urlB, teamPrefix)).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertHitterPerTeamAndYear(setHittingStats(scrapingData, team.getValue(), i));
				
				System.out.println(url + makeUrl(urlP, teamPrefix));
				scrapingData = Jsoup.connect(url.toString() + makeUrl(urlP, teamPrefix)).get()
						.select("#stdivmaintbl").first().select("tr");
				scrapeNpbOfficialDao.insertPitcherPerTeamAndYear(setPitchingStats(scrapingData, team.getValue(), i));
//			}
		}
	}

	/**
	 * NPBサイトから2024年度の打者成績と投手成績を取得する
	 * 最終的には、更新処理を削除と挿入に変更したい
	 * @return
	 * @throws IOException
	 */
	public int npbStatsThisYear() throws IOException {
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
		StringBuilder url = new StringBuilder(scrapeUrl);
		url.append(Constants.thisYear);
		url.append(stats);
		String teamPrefix = team.getPrefix();
		// 打者データの取得
		Elements scrapingData = Jsoup.connect(url.toString() + makeUrl(urlB, teamPrefix)).get().select("#stdivmaintbl")
				.first().select("tr");
		// 取得先のURLを表示
		System.out.println(url + makeUrl(urlB, teamPrefix));
		//データの整形
		List<NPBStatsHitter> hitData = setHittingStats(scrapingData, team.getValue(), Constants.thisYear);
		// マスタテーブルを更新
		scrapeNpbOfficialDao.insertPlayerMaster(setPlayerMasterH(scrapingData, team.getValue(),Constants.thisYear));
		// 初出場の選手は事前に登録されていないためまず挿入処理が必要
		scrapeNpbOfficialDao.insertHitterThisYear(hitData);
		// 登録済みの本年データの更新が必要
		scrapeNpbOfficialDao.updateHitterThisYear(hitData);
		// 投手データの取得
		scrapingData = Jsoup.connect(url.toString() + makeUrl(urlP, teamPrefix)).get().select("#stdivmaintbl")
				.first().select("tr");
		// 取得先のURLを表示
		System.out.println(url + makeUrl(urlP, teamPrefix));
		//データの整形
		List<NpbStatsPitcher> pitchData = setPitchingStats(scrapingData, team.getValue(),Constants.thisYear);
		// 初出場の選手は事前に登録されていないためまず挿入処理が必要
		scrapeNpbOfficialDao.insertPitcherThisYear(pitchData);
		// 登録済みの本年データの更新が必要
		scrapeNpbOfficialDao.updatePitcherThisYear(pitchData);
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
			String[] data = scrapingData.get(i).text().split(" ");
//			String scrapedLine = scrapingData.get(i).text();
//			List<String>  data= StringUtils.splitBySpace(scrapedLine);
			stat.setNameAndHandH(data);
			System.out.print(data[0]);
			// 表の左端が "*" or "+"
			if (data[0].equals("*") || data[0].equals("+")) {
				
				// 以降statマップにセット
				stat.setGames(Integer.parseInt(data[2]));// 試合
				stat.setWinNum(Integer.parseInt(data[3]));// 打席
				stat.setLoseNum(Integer.parseInt(data[4]));// 打数
				stat.setSaveNum(Integer.parseInt(data[5]));// 得点
				stat.setHold(Integer.parseInt(data[6]));
				stat.setHoldPoint(Integer.parseInt(data[7]));
				stat.setCompleteGame(Integer.parseInt(data[8]));
				stat.setWhitewashWinNum(Integer.parseInt(data[9]));// 塁打
				stat.setNoWalk(Integer.parseInt(data[10]));// 打点
				stat.setWinRate(Double.parseDouble(data[11]));
				stat.setAgainstBatters(Integer.parseInt(data[12]));
				double innings = 0;
				if(!data[13].equals("+")) {
					innings = Integer.parseInt(data[13]);
				};
				if(data[14].contains(".")) {
					innings += Double.parseDouble(data[14]);
					stat.setInnings(innings);
					stat.setHits(Integer.parseInt(data[15]));
					stat.setHomeruns(Integer.parseInt(data[16]));// 四球
					stat.setFourBalls(Integer.parseInt(data[17]));// 四球
					stat.setGotWalked(Integer.parseInt(data[18]));// 故意
					stat.setDeadBalls(Integer.parseInt(data[19]));// 死球
					stat.setKk(Integer.parseInt(data[20]));// 三振
					stat.setWildPitch(Integer.parseInt(data[21]));
					stat.setBork(Integer.parseInt(data[22]));
					stat.setAllowedRuns(Integer.parseInt(data[23]));
//					stat.setEarnedRuns(Integer.parseInt(data[24]));
					if(data[25].equals("----")) {
						stat.setEra(0);
					} else {
						stat.setEra(Double.parseDouble(data[25]));
					}
				} else {// 投球回が＋の場合は0
					stat.setInnings(innings);
					stat.setHits(Integer.parseInt(data[14]));//投球回
					stat.setHomeruns(Integer.parseInt(data[15]));// 四球
//					System.out.println(stat.getHomeruns());
					stat.setFourBalls(Integer.parseInt(data[16]));// 四球
					stat.setGotWalked(Integer.parseInt(data[17]));// 故意
					stat.setDeadBalls(Integer.parseInt(data[18]));// 死球
					stat.setKk(Integer.parseInt(data[19]));// 三振
//					System.out.println(stat.getKk());
					stat.setWildPitch(Integer.parseInt(data[20]));
//					System.out.println(stat.getWildPitch());
					stat.setBork(Integer.parseInt(data[21]));
					stat.setAllowedRuns(Integer.parseInt(data[22]));
//					stat.setEarnedRuns(Integer.parseInt(data[23]));
					if(data[24].equals("----")) {
						stat.setEra(0);
					} else {
						stat.setEra(Double.parseDouble(data[24]));
					}
				}
			} else {
				// 以降statマップにセット
				stat.setGames(Integer.parseInt(data[1]));// 
				stat.setWinNum(Integer.parseInt(data[2]));// 打席
				stat.setLoseNum(Integer.parseInt(data[3]));// 打数
				stat.setSaveNum(Integer.parseInt(data[4]));// 得点
				stat.setHold(Integer.parseInt(data[5]));
				stat.setHoldPoint(Integer.parseInt(data[6]));
				stat.setCompleteGame(Integer.parseInt(data[7]));
				stat.setWhitewashWinNum(Integer.parseInt(data[8]));// 塁打
				stat.setNoWalk(Integer.parseInt(data[9]));// 打点
				stat.setWinRate(Double.parseDouble(data[10]));
//				System.out.println(stat.getWinRate());
				stat.setAgainstBatters(Integer.parseInt(data[11]));
				double innings = 0;
				if(!data[12].equals("+")) {
					innings = Integer.parseInt(data[12]);
				};
				if(data[13].contains(".")) {
					innings += Double.parseDouble(data[13]);
					stat.setInnings(innings);
//					System.out.println(stat.getInnings());
					stat.setHits(Integer.parseInt(data[14]));//投球回
					stat.setHomeruns(Integer.parseInt(data[15]));// 四球
//					System.out.println(stat.getHomeruns());
					stat.setFourBalls(Integer.parseInt(data[16]));// 四球
					stat.setGotWalked(Integer.parseInt(data[17]));// 故意
					stat.setDeadBalls(Integer.parseInt(data[18]));// 死球
					stat.setKk(Integer.parseInt(data[19]));// 三振
					stat.setWildPitch(Integer.parseInt(data[20]));
					stat.setBork(Integer.parseInt(data[21]));
					stat.setAllowedRuns(Integer.parseInt(data[22]));
					if(data[24].equals("----")) {
						stat.setEra(0);
					} else {
						stat.setEra(Double.parseDouble(data[24]));
					}
				} else {
					stat.setInnings(innings);
					stat.setHits(Integer.parseInt(data[13]));//投球回
					stat.setHomeruns(Integer.parseInt(data[14]));// 四球
					stat.setFourBalls(Integer.parseInt(data[15]));// 四球
					stat.setGotWalked(Integer.parseInt(data[16]));// 故意
					stat.setDeadBalls(Integer.parseInt(data[17]));// 死球
					stat.setKk(Integer.parseInt(data[18]));// 三振
					stat.setWildPitch(Integer.parseInt(data[19]));
					stat.setBork(Integer.parseInt(data[20]));
					stat.setAllowedRuns(Integer.parseInt(data[21]));
					if(data[23].equals("----")) {
						stat.setEra(0);
					} else {
						stat.setEra(Double.parseDouble(data[23]));
					}
				}
			}
			// 選手１人分の成績を配列に追加
			stats.add(stat);
		}
		// 選手情報の配列をリターン
		return stats;
	}
	
	/**
	 * URLの可変部分作成
	 * @param sitesSymbols
	 * @param teamPrefix
	 * @return
	 */
	private String makeUrl(String sitesSymbols, String teamPrefix) {
		StringBuilder suffixUrl = new StringBuilder();
		suffixUrl.append(sitesSymbols);
		suffixUrl.append(middleNameOfHtml);
		suffixUrl.append(teamPrefix);
		suffixUrl.append(suffixOfHtml);
		return suffixUrl.toString();
	}
}