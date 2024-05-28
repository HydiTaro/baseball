package com.baseball;

import com.baseball.common.OrderUtil;
import com.baseball.dto.BatterForStrategyData;
import com.baseball.strategy.InningStrategy;

/**
 * 延長戦考慮せず
 */
public class Game {

	private InningStrategy strategy;// シミュレーション
	private BatterForStrategyData[] orderBatters;//

	public Game(InningStrategy strategy, BatterForStrategyData[] orderBatters) {
		this.strategy = strategy;
		this.orderBatters = orderBatters;
	}

	public void gameStart() {
		for (int i = 0; i < 9; i++) {
//        	System.out.println("*****イニング"+OrderUtil.fromIndexToOrderNo(i)+"*******");
			strategy.inningOffenceStart(orderBatters);
			strategy.inningOffenceFin();
		}
	}

	public void gameFin() {
		// nothing
	}
}