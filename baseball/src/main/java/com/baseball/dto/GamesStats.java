package com.baseball.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 */
@Data
public class GamesStats {
	
	private String gameName;
	private int totalGamesScore = 0;// 合計得点
//	private int scores;
	private int hits = 0;// 合計安打
	private int singles, doubles, triples, homeruns = 0;// 合計各安打
	private int games;

	public GamesStats(int games) {
		this.games = games;
	}
	
	public void addScores(int a) {
		;
	}
//	private static GameStats singleton = new GameStats();
//
//	public void addHit() {
//		hits++;
//	}
//
//	public void addSingle() {
//		singles++;
//		hits++;
//	}
//
//	public void addDouble() {
//		doubles++;
//		hits++;
//	}
//
//	public void addTriple() {
//		triples++;
//		hits++;
//	}
//
//	public void addHomerun() {
//		homeruns++;
//		hits++;
//	}

//    public void clearStats() {
//    	gameScore = 0;
//    	homerun = 0;
//    }
	public int GameScore() {
		return totalGamesScore;
	}

	public void addTotalGameScore(int newScore) {
		totalGamesScore += newScore;
	}

//	public GameStats() {
//		// nothing
//	}

//	public static GameStats getInstance() {
//		return singleton;
//	}
	
	public double getAverageScore() {
		return (double)(totalGamesScore/games);
	}

	public void clearStats() {
		totalGamesScore = 0;
		homeruns = 0;
		hits = 0;
		singles = 0;
		doubles = 0;
		triples = 0;
		homeruns = 0;
	}

	@Override
	public String toString() {
		return gameName+"試合、得点:" + totalGamesScore + ",/n";
	}
	
	public void printData() {
		double aveScore = totalGamesScore / games;
		double aveHits = hits / games;
		double aveSingles = singles / games;
		double aveDoubles = doubles / games;
		double aveTriples = triples / games;
		double aveHomeruns = homeruns/ games;
		System.out.println("+++++++++++++++++++++++++++++++++");
		System.out.println("++全試合総得点：" + totalGamesScore);
		System.out.printf("++全試合平均得点：%2.4f, 平均安打:%2.4f,\n++安打の内訳++ 一塁打:%2.4f, 二塁打:%2.4f, 三塁打:%2.4f, 本塁打:%2.4f\n",
				aveScore, aveHits, aveSingles, aveDoubles, aveTriples, aveHomeruns);
	}
}
