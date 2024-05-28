package com.baseball.dao.condition;

import java.util.List;

import com.baseball.scrape.dto.NPBStatsHitter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)	
public class InsertScrapedNpbPerTeamAndYearCondition {
	
	private List<NPBStatsHitter> npbStats;
	
	private String teamId;
	
	private String startYear;
}
