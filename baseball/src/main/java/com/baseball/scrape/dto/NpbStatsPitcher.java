package com.baseball.scrape.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NpbStatsPitcher extends NpbPlayerMasterDTO{
	
	private int games;
	private int winNum,loseNum,saveNum;
	private int hold,holdPoint;
	private int completeGame,whitewashWinNum;
	private int noWalk;
	private double winRate,innings;
	private int againstBatters,hits,homeruns;
	/**
	 * 四球、故意四球、死球
	 */
	private int fourBalls,gotWalked,deadBalls;
	private int wildPitch,bork,kk;
	private int allowedRuns,earnedRuns;
	private double era;
}
