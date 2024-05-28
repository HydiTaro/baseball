package com.baseball.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.baseball.common.Constants;
import com.baseball.common.OrderUtil;
import com.baseball.dto.BatterForStrategyData;
import com.baseball.dto.GamesStats;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 未考慮事項:相手投手能力、野手の好不調,走力,守備のエラー
 * 未考慮状況：代打,代走,犠打、犠飛、スクイズ、ゲッツー、犠牲フライ、盗塁,一塁打の場合、問答無用で一つ進塁,オートスタート(盗塁)
 * 課題：出塁率と打率の分母が違うこと TODO 得点計算と走者計算の分離、クリア関数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InningStrategy01 implements InningStrategy {

	protected int firstBatterOfInning = 1;// 先頭打者
	protected int batterInningTotalNum = 0;// 打者数
	protected int inningScore = 0;// 得点
	protected int outCount = 0;// アウトカウント;
	private Random random;
	private Boolean[] baseInfo = new Boolean[] { false, false, false };// 塁
	private GamesStats gameStats;
	
	public InningStrategy01() {
//		gameStats = GameStats.getInstance();
//		gameStats = new GamesStats();
//		this.random = new Random();
		// TODO EffectiveJavaには普通のRandomクラスより以下のクラスが適切と記載あり。要検証
		this.random = ThreadLocalRandom.current();
	}

	/**
	 * イニングスタート
	 */
	@Override
	public void inningOffenceStart(BatterForStrategyData[] order) {
		System.out.println(", 先頭打者：" + firstBatterOfInning + "番");
		while (this.outCount < 3) {
//			System.out.print(order[OrderUtil.fromOrderNoToIndex(setNextBatterNo())].getFName());
//			System.out.print(", " + outCount + "アウト");
//        	resultOfOneBatter(order[OrderUtil.fromOrderNoToIndex(startNo)]);
			resultOfOneBatter(order[OrderUtil.fromOrderNoToIndex(setNextBatterNo())]);
//			printBase();
		}
	}
	
	/**
	 * イニングスタート
	 */
	@Override
	public void inningOffenceStart(List<BatterForStrategyData> order) {
		System.out.println(", 先頭打者：" + firstBatterOfInning + "番");
		while (this.outCount < 3) {
			resultOfOneBatter(order.get(OrderUtil.fromOrderNoToIndex(setNextBatterNo())));
//			printBase();
		}
	}

	/**
	 * イニング終了
	 */
	@Override
	public void inningOffenceFin() {
		setNextinningFirstBatter();
		System.out.println(", 打者数：" + batterInningTotalNum + "人");
		System.out.println("得点：" + inningScore + ",");
		clearInning();
	}

	/**
	 * 打撃結果 分岐は以下の順で左からデータが並んでいるイメージ 
	 * single + doubles + triples + homeruns(左からここまでBAT) + fourball(左からここまでOBP) + KK + NonKK
	 */
	@Override
	public void resultOfOneBatter(BatterForStrategyData batter) {
        System.out.printf(", 打者打率:%d, 一塁打：%d,二塁打：%d,三塁打：%d,本塁打：%d, KK：%d, 四球:%d"
        		,batter.getBat()
        		,batter.getSingles()
        		,batter.getDoubles()
        		,batter.getTriples()
        		,batter.getHomeruns()
        		,batter.getKks()
        		,batter.getFourballs());
//        ThreadLocalRandom 
		int n = random.nextInt(1000);
		System.out.print(", random：" + n);
		if (batter.getKks() <= n) {// 三振以外のアウト
			System.out.print("　" + Constants.Bontai_Not_KK);
//			resultOfRun(CommonEnum.ResultOfBat.OUT_NONKK.getValue());
			moveOneBaseByOut();
			outCount++;
		} else if (batter.getFourballs()<n) {// 三振のアウト
			System.out.print(" " + Constants.Bontai_KK);
//			resultOfRun(CommonEnum.ResultOfBat.OUT_KK.getValue());
			outCount++;
		} else if (batter.getBat() < n) {// 四球
			System.out.print(" " + Constants.Fourball);
//			resultOfRun(CommonEnum.ResultOfBat.HIT_FOURBALL.getValue());
			moveOneBaseByFourball();
		} else if (n <= batter.getSingles()) {// 一塁打
			System.out.print(" "+Constants.Hit_Single);
//			resultOfRun(CommonEnum.ResultOfBat.HIT_SINGLE.getValue());
			moveOneBaseByHit();
//			gameStats.addSingle();
		} else if (n <= batter.getDoubles()) { // 二塁打
			System.out.print(" "+Constants.Hit_Double);
//			resultOfRun(CommonEnum.ResultOfBat.HIT_DOUBLE.getValue());
			moveTwoBase();
//			gameStats.addDouble();
		} else if (n <= batter.getTriples()) {// 三塁打
			System.out.print(" "+Constants.Hit_Triple);
//			resultOfRun(CommonEnum.ResultOfBat.HIT_SINGLE.getValue());
			moveThreeBase();
//			gameStats.addTriple();
		} else { // 本塁打
			System.out.print(" "+Constants.Hit_Homerun);
//			resultOfRun(CommonEnum.ResultOfBat.HIT_HOMERUN.getValue());
			moveForthBase();
//			gameStats.addHomerun();
		}
		System.out.println(",");
		batterInningTotalNum++;
	}

	// 守備結果（考慮外）
	@Override
	public int resultOfDefence() {
		return 0;
	}

	/**
	 * 走塁結果 単純に塁打数に応じて、走者を進塁させる
	 */
	@Override
	public void resultOfRun(String resultOfBat) {
//		// 三振以外アウトの場合
//		if (resultOfBat.equals(CommonEnum.ResultOfBat.OUT_NONKK.getValue())) {
//			if (outCount != 2) {// ツーアウト以外
//				int n = random.nextInt(10);
//				if (n <= 3 && !baseInfo[2] && (baseInfo[1] || baseInfo[0])) { // ランナー3塁がおらず、1塁or2塁にいる場合,10分の3の確率で進塁
//					moveOneBaseByOut();
////					System.out.print(", 進塁打成功！");
//				} else if (n <= 2 && baseInfo[2]) {// ランナー3塁の場合,10分の2の確率で進塁
//					moveOneBaseByOut();
////					System.out.print(", 犠牲フライorスクイズ成功！");
//				}
//			}
//			// 三振アウトの場合
//		} else if (resultOfBat.equals(CommonEnum.ResultOfBat.OUT_KK.getValue())) {
//			// nothing;
//			// 一塁打の場合
//		} else if (resultOfBat == CommonEnum.ResultOfBat.HIT_SINGLE.getValue()
//				|| resultOfBat == CommonEnum.ResultOfBat.HIT_FOURBALL.getValue()) {
//			moveOneBaseByHit();
//			// 二塁打の場合
//		} else if (resultOfBat == CommonEnum.ResultOfBat.HIT_DOUBLE.getValue()) {
//			moveTwoBase();
//			// 三塁打の場合
//		} else if (resultOfBat == CommonEnum.ResultOfBat.HIT_TRIPLE.getValue()) {
//			moveThreeBase();
//			// 本塁打の場合
//		} else {
//			moveForthBase();
//		}
	}
	
	/**
	 *  ラムダで書く方法を知りたい
	 *  ひとつづつ進塁させる
	 */
	private void moveOneBaseByHit() {
		if (baseInfo[2]) {// 三塁走者あり
//			gameStats.addGameScore(1);
//			System.out.print("(1点入りました");
		}
		baseInfo[2] = baseInfo[1];
		baseInfo[1] = baseInfo[0];
		baseInfo[0] = true;
	}
	/**
	 *  四球のためひとつづつ進塁
	 */
	private void moveOneBaseByFourball() {
		long getPoints = Arrays.stream(baseInfo).filter(b -> b) // trueのBooleanオブジェクトのみ抽出
				.count();
		if (getPoints==3) {// 満塁
//			gameStats.addGameScore(1);
//			System.out.print("(1点入りました");
		}
		if(baseInfo[0]){
			if(baseInfo[1]) {// 一二塁から
				baseInfo[2] = true;
			} else {		//  一塁走者のみ移動
				baseInfo[1] = true;
			}
		} else {// フォースランナーでないため移動なし
				baseInfo[0] = true;
		}
	}

	/**
	 * ヒット以外での進塁
	 */
	private void moveOneBaseByOut() {
		if (outCount != 2) {// ツーアウト以外
			int n = random.nextInt(10);
			if (n <= 3 && !baseInfo[2] && (baseInfo[1] || baseInfo[0])) { // ランナー3塁がおらず、1塁or2塁にいる場合,10分の3の確率で進塁
				if (baseInfo[2]) {// 三塁走者あり
//					gameStats.addGameScore(1);
//					System.out.print("(1点入りました");
				}
//				System.out.print(", 進塁打成功！");
			} else if (n <= 2 && baseInfo[2]) {// ランナー3塁の場合,10分の2の確率で進塁
				if (baseInfo[2]) {// 三塁走者あり
//					gameStats.addGameScore(1);
//					System.out.print("(1点入りました");
				}
//				System.out.print(", 犠牲フライorスクイズ成功！");
			}
			baseInfo[2] = baseInfo[1];
			baseInfo[1] = baseInfo[0];
			baseInfo[0] = false;
		}
	}

	/**
	 * ふたつづつ進塁させる
	 */
	private void moveTwoBase() {
		if (baseInfo[2] && baseInfo[1]) {// 二三塁から
//			gameStats.addGameScore(2);
//			System.out.print("(2点入りました");
		} else if (baseInfo[2] || baseInfo[1]) {// 二三塁のどちらかに走者あり
//			gameStats.addGameScore(1);
//			System.out.print("(1点入りました");
		} else {// 二三塁に走者なし
				// 得点なし
		}
		baseInfo[2] = baseInfo[0];
		baseInfo[1] = true;
		baseInfo[0] = false;
	}

	// みっつづつ進塁させる
	// ラムダ式
	private void moveThreeBase() {
		long getPoints = Arrays.stream(baseInfo).filter(b -> b) // trueのBooleanオブジェクトのみ抽出
				.count() + 1;
		baseInfo[2] = true;
		baseInfo[1] = false;
		baseInfo[0] = false;
//		inningScore += getPoints;
//		gameStats.addGameScore(getPoints);
//		System.out.print("(" + getPoints + "点入りました");
	}

	/**
	 * 本塁打
	 */
	private void moveForthBase() {
		long getPoints = Arrays.stream(baseInfo).filter(b -> b) // trueのBooleanオブジェクトのみ抽出
				.count() + 1;
//		System.out.print("(" + getPoints + "点入りました");
//		gameStats.addGameScore(getPoints);
		clearRunner();
	}

	private int setNextBatterNo() {
		int nextBatter = (firstBatterOfInning + batterInningTotalNum) % 9;
		if (nextBatter == 0) {
			nextBatter = 9;
		}
		return nextBatter;
	}

	private void setNextinningFirstBatter() {
		firstBatterOfInning = (firstBatterOfInning + batterInningTotalNum) % 9;
		if (firstBatterOfInning == 0) {
			firstBatterOfInning = 9;
		}
	}

	/**
	 * 走者クリア
	 */
	private void clearRunner() {
		baseInfo = Arrays.stream(baseInfo).map(s -> false).toArray(Boolean[]::new);
		// work correctly
	}

	private void clearInning() {
		outCount = 0;
		clearRunner();
		batterInningTotalNum = 0;
		inningScore = 0;
	}

	private GamesStats getGameStats() {
		return this.gameStats;
	}

	@Override
	public String toString() {
		return "Strategy01";
	}
}
