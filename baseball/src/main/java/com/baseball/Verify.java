package com.baseball;

import java.util.List;

import com.baseball.common.OrderUtil;
import com.baseball.dto.BatterForStrategyData;
import com.baseball.dto.GamesStats;
import com.baseball.strategy.InningStrategy;

public class Verify {

	InningStrategy strategy;
	List<BatterForStrategyData> orderBatters;

	public GamesStats verify(String name, int times) {
		System.out.println(strategy.toString());
		GamesStats gameStats = new GamesStats(times);
		for (int i = 0; i < times; i++) {
			System.out.println(
					"=====================" + OrderUtil.fromIndexToOrderNo(i) + "試合目=========================");
			gameFromStartToFin(gameStats);
		}
		
		return gameStats;
	}

	public void gameFromStartToFin(GamesStats gameStats) {
		// 一試合の得点
		int gameScore = 0;
		for (int i = 0; i < 9; i++) {
			System.out.println("*****イニング" + OrderUtil.fromIndexToOrderNo(i) + "*******");
			strategy.inningOffenceStart(this.orderBatters);
			gameScore += strategy.getInningScore();
			strategy.inningOffenceFin();
		}
		gameStats.addTotalGameScore(gameScore);
	}

	public Verify(InningStrategy s, List<BatterForStrategyData> b) {
		this.strategy = s;
		this.orderBatters = b;
//		this.gameStats = new GamesStats(0);
	}
}
