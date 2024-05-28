package com.baseball.dto.req_res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * 簡略データ返却
 */
@Data
public class VBO1010ResponseDto {
	
	private List<BattersForVerify> out;
	
	@Data
	@Builder
	public static class BattersForVerify {
		
		@JsonProperty("playerId")
		private String playerId;
		
		@JsonProperty("fname")
		private String fname;
		
		@JsonProperty("lname")
		private String lname;
		
		@JsonProperty("bat")
		private String bat;
		
		@JsonProperty("homerun")
		private String homerun;
		
		@JsonProperty("obp")
		private String obp;
		
		@JsonProperty("steal")
		private String steal;
		
		@JsonProperty("bunt")
		private String bunt;
		
		@JsonProperty("position")
		private String position;
		
		@JsonProperty("hitHand")
		private String hitHand;
		
	}
}
