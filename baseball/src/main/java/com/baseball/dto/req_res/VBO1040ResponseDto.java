package com.baseball.dto.req_res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public class VBO1040ResponseDto {
	
	private List<InningDetail> inningDetail;
	
	@Data
	public static class InningDetail {
		
		private List<BattingResult> battingResult;
		
		@Data
		public static class BattingResult{
			
			@JsonProperty("playerId")
			private String playerId;
			
			@JsonProperty("orderNo")
			private String orderNo;
			
			@JsonProperty("batResult")
			private String batResult;
			/**
			 * 打撃後の塁の状況を返却"000"～"111"の間
			 */
			@JsonProperty("runResult")
			private String runResult;
			
			@JsonProperty("outCount")
			private String outCount;
		}
	}
}
