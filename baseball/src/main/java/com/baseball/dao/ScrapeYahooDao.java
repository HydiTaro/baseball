package com.baseball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.dao.condition.SelectPredictBatOrderDtoResponse;
import com.baseball.scrape.dto.YahooStarting;

@Mapper
public interface ScrapeYahooDao {

	/**
	 * Yahooの打順挿入
	 * @param condition
	 * @return
	 */
	int insertBatOreder(
			@Param("conditionEntity") List<YahooStarting> condition);
	/**
	 * Yahooの打順挿入
	 * @param condition
	 * @return
	 */
	SelectPredictBatOrderDtoResponse getLatestOreder(
			@Param("conditionEntity") String teamId);
	
}
