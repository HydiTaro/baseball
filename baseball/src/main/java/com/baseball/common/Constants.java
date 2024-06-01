package com.baseball.common;

import java.time.Year;

public class Constants {
	
	public static final String Bontai = "凡退";
	public static final String Bontai_Not_KK = "凡退(三振以外";
	public static final String Bontai_KK = "凡退(三振";
	public static final String Bontai_Sinrui = "進塁打";
	
	public static final String Hit_Single = "一塁打";
	public static final String Hit_Double = "二塁打";
	public static final String Hit_Triple = "三塁打";
	public static final String Hit_Homerun = "本塁打";
	
	public static final String Fourball = "四球";
	public static final String Bunt = "犠打";
	public static final String SF = "犠飛";
	
	public static final String Scored_One = "一点入りました";
	public static final String Scored_Two = "二点入りました";
	public static final String Scored_Three = "三点入りました";
	public static final String Scored_Forth = "四点入りました";
	
	public static final String Kanji_Pitcher = "投";
	public static final String Kanji_Catcher = "捕";
	public static final String Kanji_First = "一";
	public static final String Kanji_Second = "二";
	public static final String Kanji_Third = "三";
	public static final String Kanji_Short = "遊";
	public static final String Kanji_Left = "左";
	public static final String Kanji_Center = "中";
	public static final String Kanji_Right = "右";
	public static final String Kanji_Dh = "指";
	
	public static final String Kanji_Migi = "右";
	public static final String Kanji_Hidari = "左";
	
	public static final String SLASH = "/";
	
	public static int thisYear = getCurrentYear();
	
    public static int getCurrentYear() {
        return Year.now().getValue();
    }
    
    private Constants() {
    	
    }
}
