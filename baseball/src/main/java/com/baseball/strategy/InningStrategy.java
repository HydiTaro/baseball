package com.baseball.strategy;
import java.util.List;

import com.baseball.dto.BatterForStrategyData;

public interface InningStrategy {
	
    public abstract void inningOffenceStart(BatterForStrategyData[] OrderHit);
    
    public abstract void inningOffenceStart(List<BatterForStrategyData> OrderHit);
    
    public abstract void inningOffenceFin();
    // 打者結果
    // public abstract void resultOfOneBatter(Player pitcher,Player batter);
    public abstract void resultOfOneBatter(BatterForStrategyData batter);
    // 守備結果
    // public abstract int resultOfdefence(Player player);
    public abstract int resultOfDefence();
    // 走塁結果
    public abstract void resultOfRun(String resultOfBat);
    
	public abstract int getInningScore();
}
