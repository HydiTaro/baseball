package com.baseball.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseball.dao.PredictBatOrderDao;
import com.baseball.dao.ScrapeYahooDao;
import com.baseball.dao.VerifyBatOrderDao;
import com.baseball.dao.condition.InsertPredictBatOrderCondition;
import com.baseball.dao.condition.SelectLatestPredictBatOrderCondition;
import com.baseball.dao.condition.SelectPredictBatOrderDtoResponse;
import com.baseball.dto.req_res.PBO1010RequestDto;
import com.baseball.dto.req_res.PBO1010ResponseDto;
import com.baseball.dto.req_res.PBO1020RequestDto;
import com.baseball.dto.req_res.PBO1020ResponseDto;

@Service
public class PBOService {
	
	@Autowired
	private PredictBatOrderDao predictBatOrderDao;
	@Autowired
	private ScrapeYahooDao scrapeYahooDao;
	
	@Autowired
	private VerifyBatOrderDao verifyBatOrderDao;
	
	
	/**
	 * 予測打順登録
	 * @param pbo1010RequestDto
	 * @return
	 */
	public PBO1010ResponseDto pbo1010(PBO1010RequestDto request) {
		
		PBO1010ResponseDto response = new PBO1010ResponseDto();
		InsertPredictBatOrderCondition dto = InsertPredictBatOrderCondition.builder()
				.teamId(request.getTeamId())
				.userId(request.getUserId())
				.gameDate(request.getGameDate())
				.player_id_1(request.getPlayer_id_1())
				.player_id_2(request.getPlayer_id_2())
				.player_id_3(request.getPlayer_id_3())
				.player_id_4(request.getPlayer_id_4())
				.player_id_5(request.getPlayer_id_5())
				.player_id_6(request.getPlayer_id_6())
				.player_id_7(request.getPlayer_id_7())
				.player_id_8(request.getPlayer_id_8())
				.player_id_9(request.getPlayer_id_9())
				.build();
		
//		predictBatOrderDao.insertPredictBatOrder(dto);
		verifyBatOrderDao.insertPredictBatOrder(dto);
		
		return response;
	}
	
	
	/**
	 * 予測打順検証
	 * @param pbo1020RequestDto
	 * @return
	 */
	public PBO1020ResponseDto pbo1020(PBO1020RequestDto request) {
		PBO1020ResponseDto response = new PBO1020ResponseDto();
		SelectLatestPredictBatOrderCondition conditino = SelectLatestPredictBatOrderCondition.builder()
				.userId(request.getUserId())
				.teamId(request.getTeamId())
				.build();
//		SelectPredictBatOrderDtoResponse predictOrder = predictBatOrderDao.getLatestPredictBatOrder(conditino);
		SelectPredictBatOrderDtoResponse predictOrder = verifyBatOrderDao.getLatestPredictBatOrder(conditino);
		SelectPredictBatOrderDtoResponse yahooStatring = scrapeYahooDao.getLatestOreder(request.getTeamId());
		response.setScore(returnScore(predictOrder,yahooStatring));
		return response;
	}
	
	/**
	 * 打順の照合（6番までは一致、789は順不同）
	 * @param predict
	 * @param scraped
	 * @return
	 */
	private int returnScore(SelectPredictBatOrderDtoResponse predict,SelectPredictBatOrderDtoResponse scraped) {
		int score=0;
		
		if(predict.getPlayer_id_1().equals(scraped.getPlayer_id_1())) {
			score++;
		}
		if(predict.getPlayer_id_2().equals(scraped.getPlayer_id_2())) {
			score++;
		}
		if(predict.getPlayer_id_3().equals(scraped.getPlayer_id_3())) {
			score++;
		}
		if(predict.getPlayer_id_4().equals(scraped.getPlayer_id_4())) {
			score++;
		}
		if(predict.getPlayer_id_5().equals(scraped.getPlayer_id_5())) {
			score++;
		}
		if(predict.getPlayer_id_6().equals(scraped.getPlayer_id_6())) {
			score++;
		}
		List<String> predict789 = Arrays.asList(predict.getPlayer_id_7(),predict.getPlayer_id_8(),predict.getPlayer_id_9());
//		List<String> scraped789 = Arrays.asList(predict.getPlayer_id_7(),predict.getPlayer_id_8(),predict.getPlayer_id_9());
		if(predict789.contains(scraped.getPlayer_id_7())) {
			score++;
		}
		if(predict789.contains(scraped.getPlayer_id_8())) {
			score++;
		}
		if(predict789.contains(scraped.getPlayer_id_9())) {
			score++;
		}
		return score;
	}
}
