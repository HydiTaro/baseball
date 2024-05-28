package com.baseball.scrape.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)	
public class NPBStatsHitter extends NpbPlayerMasterDTO{
	
//	/**
//	 * データの記録年
//	 */
//	private int year;
	/**
	 * 試合数,打数、打席数
	 */
	private int games,atBats,plateAppearance;
	/**
	 * 安打数、二塁打数、三塁打数、本塁打、塁打
	 */
	private int totalHits,doubles,triples,homeruns,totalBases;
	/**
	 * 打点、得点
	 */
	private int rbi,runs;
	/**
	 * 三振
	 */
	private int strikeOuts;
	/**
	 * 四球、故意四球、死球
	 */
	private int fourBalls,gotWalked,deadBalls;
	/**
	 * 犠打、犠飛
	 */
	private int sacrificeBunts,sacrificeFlies;
	/**
	 * 盗塁、盗塁死
	 */
	private int stolenBases,stolenBaseDeath;
	/**
	 * 併殺打
	 */
	private int doublePlay;
	/**
	 * 打率、出塁率、長打率、OPS
	 */
	private double battingAverage,onBasePercentage,sluggingPercentage,ops;
	/**
	 * 得点圏
	 */
	private double scoringRangePercentage;
	/**
	 * 失策
	 */
	private int errors;
}
