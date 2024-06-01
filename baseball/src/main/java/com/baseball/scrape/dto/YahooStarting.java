package com.baseball.scrape.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YahooStarting {
	
	private int gameId;
		
	private String teamId;
	
	private String gameDate;
	
	private int position_1;
	private String fname_1;
	private String lname_1;
	private int position_2;
	private String fname_2;
	private String lname_2;
	private int position_3;
	private String fname_3;
	private String lname_3;
	private int position_4;
	private String fname_4;
	private String lname_4;
	private int position_5;
	private String fname_5;
	private String lname_5;
	private int position_6;
	private String fname_6;
	private String lname_6;
	private int position_7;
	private String fname_7;
	private String lname_7;
	private int position_8;
	private String fname_8;
	private String lname_8;
	private int position_9;
	private String fname_9;
	private String lname_9;
//	
//	@Data
//	public static class Starting_1 {
//		private int no;// 打順
//		/**
//		 * 一桁の数値で表現した守備位置
//		 */
//		private int positionByInt;
//		private String fname;
//		private String lname;
//	}
}
