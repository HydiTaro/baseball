package com.baseball.dao.condition;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectLatestPredictBatOrderCondition {
	
	private String userId;
	
	private String teamId;
}
