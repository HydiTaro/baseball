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
	
	public static final String SLASH = "/";
	
	public static int thisYear = getCurrentYear();
	
    public static int getCurrentYear() {
        return Year.now().getValue();
    }
}