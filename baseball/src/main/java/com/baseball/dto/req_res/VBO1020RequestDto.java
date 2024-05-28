package com.baseball.dto.req_res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登場人物のBaseDto
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VBO1020RequestDto {
	
	private List<BatterInput> batterOrder;
	
	@Data
	@Builder
	public static class BatterInput {
		@JsonProperty("fname")
		private String fname;
		
		@JsonProperty("lname")
		private String lname;
		
		@JsonProperty("team")
		private String team;
		
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
	}
	
}
