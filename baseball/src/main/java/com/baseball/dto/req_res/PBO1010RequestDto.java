package com.baseball.dto.req_res;

import lombok.Builder;
import lombok.Data;

@Data
public class PBO1010RequestDto {
	
	private String teamId;
	
	private String userId;
	
//	private PredictOrder order;
	
	private String gameDate; 
//	
//	@Data
//	public static class PredictOrder{
		private String postion_1;
		private String player_id_1;
		
		private String postion_2;
		private String player_id_2;
		
		private String postion_3;
		private String player_id_3;
		
		private String postion_4;
		private String player_id_4;
		
		private String postion_5;
		private String player_id_5;
		
		private String postion_6;
		private String player_id_6;
		
		private String postion_7;
		private String player_id_7;
		
		private String postion_8;
		private String player_id_8;
		
		private String postion_9;
		private String player_id_9;
//	}
}
