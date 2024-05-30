package com.baseball.dto.req_res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VBO1040RequestDto {
	
private String teamId;
	
	private List<BattersInput04> battersInput;
	
	@Data
	public static class BattersInput04 {
		
		@JsonProperty("orderNo")
		private String orderNo;
		
		@JsonProperty("playerId")
		private String playerId;
////		@JsonProperty("bat")
//		private String bat;
//		
////		@JsonProperty("homerun")
//		private String homerun;
//		
////		@JsonProperty("obp")
//		private String obp;
//		
////		@JsonProperty("steal")
//		private String steal;
//		
////		@JsonProperty("bunt")
//		private String bunt;
//		
////		@JsonProperty("position")
//		private String position;
	}
}
