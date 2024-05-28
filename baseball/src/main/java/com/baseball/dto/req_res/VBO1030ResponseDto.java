package com.baseball.dto.req_res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
public class VBO1030ResponseDto {
	
	private List<PitchersForVerify> out;
	
	@Data
	@Builder
	public static class PitchersForVerify {
		
		@JsonProperty("playerId")
		private String playerId;
		
		@JsonProperty("fname")
		private String fname;
		
		@JsonProperty("lname")
		private String lname;
		
		@JsonProperty("games")
		private String games;
		
		@JsonProperty("innings")
		private String innings;
		
		@JsonProperty("era")
		private String era;
		
		@JsonProperty("homerun")
		private String homerun;
		
		/**
		 * 死球＋敬遠＋四球
		 */
		@JsonProperty("fourball")
		private String fourball;
		
		@JsonProperty("wildPitch")
		private String wildPitch;
		
		@JsonProperty("position")
		private String position;
		
		@JsonProperty("throwHand")
		private String throwHand;
	}
}
