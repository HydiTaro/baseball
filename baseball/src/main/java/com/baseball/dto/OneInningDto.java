package com.baseball.dto;

import lombok.Data;

@Data
public class OneInningDto {

    // private int outCount;// アウントカウント
    // private boolean firstBase, secondBase, thirdBase;// 塁上ランナー有無
    private int inningPoints;// 得点
    private int startNo=1;//先頭打者の打順
    // private Strategy strategy;//
    // private Batter[] orderBatters;// 
    
    public OneInningDto(int startNo){
        this.startNo = startNo;
//        this.endNo = startNo;
        this.inningPoints = 0;
    }
    public void clearPoint() {
    	this.inningPoints = 0;
    }
    public void setNextInningStartNo(int batterNum){
    	startNo = (startNo+batterNum)%9;
    	if(startNo==0) {
    		startNo=9;
    	}
    }
    public void setPoints(int points){
        this.inningPoints = points;
    }
    public int getPoints(){
        return inningPoints;
    }
//    private void clearInning() {
//    	startNo=;
//    }
}