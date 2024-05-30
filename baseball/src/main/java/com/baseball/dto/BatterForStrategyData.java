package com.baseball.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BatterForStrategyData extends PlayerForStrategy {

	// 打数
	private int atBats;
	// 打席
	private int plateAppearance;
	private int bat;
	// 1*single+2*second+3*third+4*homerun /atBats = slu
	private int obps;
	// single + second + third + homerun = bat

	private double slu;
	private int singles;
	private int doubles;
	private int triples;
	private int homeruns;
//    private int outExceptKks;
	private int bunts;
	private int sacrificeFlies;
	private int fourballs;
	private int deadballs;

//  on-base-percentage
//  (安打数＋四球＋死球）÷（打数＋四球＋死球＋犠飛)
	private int kks;

	private BatterForStrategyData(String name, int atBats, int plateAppearance, int obps, int bat, int singles,
			int doubles, int triples, int homeruns, int kks, int fourballs, int bunts, int intsacrificeFlies) {
		this.fName = name;
		this.atBats = atBats;
		this.plateAppearance = plateAppearance;
		this.obps = obps;
		this.bat = bat;
		this.singles = singles;
		this.doubles = doubles;
		this.triples = triples;
		this.homeruns = homeruns;
		this.kks = kks;
		this.fourballs = fourballs;
		this.bunts = bunts;
	}

	/**
	 * BatterData for Strategy01
	 * 
	 * @param name
	 * @param atBats
	 * @param obp
	 * @param bat
	 * @param doubleNum
	 * @param tripleNum
	 * @param homerunNum
	 * @param kkNum
	 * @param fourballNum
	 * @param buntNum
	 * @param sacrificeFlyNum
	 * @return
	 */
	public static BatterForStrategyData newBatterData01(String name, int atBats, double obp, double bat, int doubleNum,
			int tripleNum, int homerunNum, int kkNum, int fourballNum, int buntNum, int sacrificeFlyNum) {
		int newBat = (bat > 1) ? (int) bat : (int) (1000 * bat);
		int newSingles = newBat - 1000 * (doubleNum + tripleNum + homerunNum) / atBats;
		int newDoubles = newSingles + 1000 * (+doubleNum) / atBats;
		int newTriples = newDoubles + 1000 * tripleNum / atBats;
		int newHomeruns = newTriples + 1000 * homerunNum / atBats;
		int newFourballs = newHomeruns + 1000 * fourballNum / (atBats + fourballNum);
		int newKks = newFourballs + 1000 * kkNum / atBats;
//        this.outExceptKks=1000 - this.kks;
//        this.fourballs=1000*fourballNum/atBats;
		int newBunts = buntNum / atBats;
		int newSacrificeFlies = sacrificeFlyNum / atBats;
		int newObp = (int) (1000 * obp);

		return new BatterForStrategyData(name, atBats, atBats, newObp, newBat, newSingles, newDoubles, newTriples,
				newHomeruns, newKks, newFourballs, newBunts, newSacrificeFlies);
	}

	/**
	 * BatterData for Strategy02
	 * 
	 * @param name
	 * @param obp
	 * @param bat
	 * @param homerunNum
	 * @param steal
	 * @param bunt
	 */
	public static BatterForStrategyData newBatterData02(String name, double obp, double bat, int homerunNum, int steal,
			int bunt) {
		int atBats = 500;
		int newBat = (bat >= 1) ? (int) bat : (int) (1000 * bat);
		int tripleNum = (homerunNum * steal / 100);
		// 本塁打が１０本以上の打者?安打の20%が二塁打:最低でも安打の10%は二塁打;
		int doubleNum = (homerunNum >= 10) ? newBat / 5 : newBat / 10;
		int newSingles = newBat - 2 * homerunNum - tripleNum - doubleNum;
		int newDoubles = newSingles + doubleNum;
		int newTriples = newDoubles + tripleNum;
		int newHomeruns = newTriples + 2 * homerunNum;
		int newObps = (int) (1000 * obp);
		int newFourballs = newObps;
		int newKks = newFourballs;
		int newBunts = 1000 * bunt / atBats;
		return new BatterForStrategyData(name, atBats, atBats, newObps, newBat, newSingles, newDoubles, newTriples,
				newHomeruns, newKks, newFourballs, newBunts, 0);
	}
}
