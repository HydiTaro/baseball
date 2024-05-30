package com.baseball.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.baseball.common.Constants;
import com.baseball.common.OrderUtil;
import com.baseball.dto.BatterForStrategyData;

import lombok.Data;

/**
 * 打者が入れ替えた打順で試合を行い、打者結果を返却する
 */
@Data
public class InningStrategy03 implements InningStrategy  {
	
	private int firstBatterOfInning = 1;// 先頭打者
	private int batterInningTotalNum = 0;// 打者数
	private int inningScore = 0;// 得点
	protected int outCount = 0;// アウトカウント;
	private Random random;
	private Boolean[] baseInfo = new Boolean[] { false, false, false };// 塁
	private List<BatterForStrategyData> batterOrder;

	public InningStrategy03() {
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
			System.out.print(", " + outCount + "アウト");
			resultOfOneBatter(order[OrderUtil.fromOrderNoToIndex(setNextBatterNo())]);
		}
	}
	
	/**
	 * イニングスタート
	 */
	@Override
	public void inningOffenceStart(List<BatterForStrategyData> order) {
		System.out.println(", 先頭打者：" + firstBatterOfInning + "番");
		while (this.outCount < 3) {
//			System.out.print(order.get(OrderUtil.fromOrderNoToIndex(setNextBatterNo())));
			System.out.print(", " + outCount + "アウト");
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
	 * 打撃結果 分岐は以下の順で左からデータが並んでいるイメージ single + doubles + triples
	 * +homeruns(左からここまでBAT) + fourball(左からここまでOBP) + KK + NonKK
	 */
	@Override
	public void resultOfOneBatter(BatterForStrategyData batter) {
        System.out.print(", 打者打率"+batter.getBat()+", 打者一塁打率："+batter.getSingles());
		int n = random.nextInt(1000);
		System.out.print(", random：" + n + ",");
		if (batter.getKks() <= n) {// 三振以外のアウト
			System.out.print("　" + Constants.Bontai_Not_KK);
			moveOneBaseByOut();
			outCount++;
		} else if (batter.getFourballs() < n) {// 三振のアウト
			System.out.print(" " + Constants.Bontai_KK);
			outCount++;
		} else if (batter.getBat() < n) {// 四球
			System.out.print(" " + Constants.Fourball);
			moveOneBaseByFourball();
		} else if (n <= batter.getSingles()) {// 一塁打
			System.out.print(" " + Constants.Hit_Single);
			moveOneBaseByHit();
//			batter.setHits();
		} else if (n <= batter.getDoubles()) { // 二塁打
			System.out.print(" " + Constants.Hit_Double);
			moveTwoBase();
		} else if (n <= batter.getTriples()) {// 三塁打
			System.out.print(" " + Constants.Hit_Triple);
			moveThreeBase();
		} else { // 本塁打
			System.out.print(" " + Constants.Hit_Homerun);
			moveForthBase();
		}
		batterInningTotalNum++;
		System.out.println(",");
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
	}

	/**
	 * ラムダで書く方法を知りたい ひとつづつ進塁させる
	 */
	private void moveOneBaseByHit() {
		if (baseInfo[2]) {// 三塁走者あり
			inningScore++;
//			System.out.print("(1点入りました");
		}
		baseInfo[2] = baseInfo[1];
		baseInfo[1] = baseInfo[0];
		baseInfo[0] = true;
	}

	/**
	 * ラムダで書く方法を知りたい ひとつづつ進塁させる
	 */
	private void moveOneBaseByFourball() {
		long getPoints = Arrays.stream(baseInfo).filter(b -> b) // trueのBooleanオブジェクトのみ抽出
				.count();
		if (getPoints == 3) {// 満塁
			inningScore++;
//			System.out.print("(1点入りました");
		}
		if (baseInfo[0]) {
			if (baseInfo[1]) {// 一二塁
				baseInfo[2] = true;
			} else { // 一塁走者のみ移動
				baseInfo[1] = true;
			}
		} else {// フォースランナーでないため移動なし
			baseInfo[0] = true;
		}
	}

	/**
	 * ヒット以外の進塁
	 */
	private void moveOneBaseByOut() {
		if (outCount != 2) {// ツーアウト以外
			int n = random.nextInt(10);
			if (n <= 3 && !baseInfo[2] && (baseInfo[1] || baseInfo[0])) { // ランナー3塁がおらず、1塁or2塁にいる場合,10分の3の確率で進塁
				if (baseInfo[2]) {// 三塁走者あり
					inningScore++;
//					System.out.print("(1点入りました");
				}
//				System.out.print(", 進塁打成功！");
			} else if (n <= 2 && baseInfo[2]) {// ランナー3塁の場合,10分の2の確率で進塁
				if (baseInfo[2]) {// 三塁走者あり
					inningScore++;
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
			inningScore+=2;
//			System.out.print("(2点入りました");
		} else if (baseInfo[2] || baseInfo[1]) {// 二三塁のどちらかに走者あり
			inningScore++;
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
		inningScore+=getPoints;
//		System.out.print("(" + getPoints + "点入りました");
	}

	/**
	 * 本塁打
	 */
	private void moveForthBase() {
		long getPoints = Arrays.stream(baseInfo).filter(b -> b) // trueのBooleanオブジェクトのみ抽出
				.count() + 1;
//		System.out.print("(" + getPoints + "点入りました");
		inningScore+=getPoints;
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

	@Override
	public String toString() {
		return "InningStrategy03";
	}
}
