package com.baseball.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseball.Verify;
import com.baseball.dao.ScrapeNpbOfficialDao;
import com.baseball.dao.VerifyBatOrderDao;
import com.baseball.dto.BatterForStrategyData;
import com.baseball.dto.GamesStats;
import com.baseball.dto.req_res.VBO1010RequestDto;
import com.baseball.dto.req_res.VBO1010ResponseDto;
import com.baseball.dto.req_res.VBO1010ResponseDto.BattersForVerify;
import com.baseball.dto.req_res.VBO1020RequestDto;
import com.baseball.dto.req_res.VBO1020RequestDto.BatterInput;
import com.baseball.dto.req_res.VBO1020ResponseDto;
import com.baseball.dto.req_res.VBO1030RequestDto;
import com.baseball.dto.req_res.VBO1030ResponseDto;
import com.baseball.dto.req_res.VBO1030ResponseDto.PitchersForVerify;
import com.baseball.strategy.InningStrategy;
import com.baseball.strategy.InningStrategy02;

@Service
public class VBOService {
	
	@Autowired
	VerifyBatOrderDao verifyBatOrderDao;
	
//	@Autowired
//	private ScrapeNpbOfficialDao scrapeNpbOfficialDao;
	
	/**
	 * @param vbo1010RequestDto
	 * @return
	 */
	public VBO1010ResponseDto vbo1010(VBO1010RequestDto vbo1010RequestDto) {
		VBO1010ResponseDto response = new VBO1010ResponseDto();
		int parsedTeamId = Integer.parseInt(vbo1010RequestDto.getTeamId());
		// 選手の成績を取得して返却
//		List<BattersForVerify> list = scrapeNpbOfficialDao.getHitterFromTeam(parsedTeamId);
		List<BattersForVerify> list = verifyBatOrderDao.getHittersFromTeam(parsedTeamId);
		response.setOut(list);
		return response;
	}
	
	/**
	 * 入力された打順をただ検証する
	 * @param vbo1010RequestDto
	 * @return
	 */
	public VBO1020ResponseDto vbo1020(VBO1020RequestDto vbo1020RequestDto) {
		List<BatterForStrategyData> batterOrder = new ArrayList<BatterForStrategyData>();
		BatterForStrategyData newBatter;
		//  オーダーのマッピング
		for(BatterInput b : vbo1020RequestDto.getBatterOrder()) {
			newBatter = BatterForStrategyData.newBatterData02(b.getFname()
							,Double.parseDouble(b.getObp())
							,Double.parseDouble(b.getBat())
							,Integer.parseInt(b.getHomerun())
							,Integer.parseInt(b.getSteal())
							,Integer.parseInt(b.getBunt()));
			System.out.printf("Input:%2.4f, ParsedObps:%d, ParsedKK:%d\n"
					,Double.parseDouble(b.getObp())
					,newBatter.getObps()
					,newBatter.getKks());
			batterOrder.add(newBatter);
		}
		InningStrategy strategy02 = new InningStrategy02();
		Verify verify = new Verify(strategy02,batterOrder);
		GamesStats g = verify.verify("",1000);
		g.printData();
		//打率と本塁打率から、一塁打、二塁打の率を計算（計算式は全打者共通）、盗塁数からスリーベースを計算
		VBO1020ResponseDto response = VBO1020ResponseDto.builder()
											.aveScore(g.getAverageScore())
											.gameScore(g.GameScore())
											.hits(g.getHits())
											.singles(g.getSingles())
											.doubles(g.getDoubles())
											.triples(g.getTriples())
											.homeruns(g.getHomeruns()).build();
		return response;
	}
	
	/**
	 * 今年度投手のデータ取得
	 * @param vbo1010RequestDto
	 * @return
	 */
	public VBO1030ResponseDto vbo1030(VBO1030RequestDto vbo1030RequestDto) {
		VBO1030ResponseDto response = new VBO1030ResponseDto();
		int parsedTeamId = Integer.parseInt(vbo1030RequestDto.getTeamId());
		// 選手の成績を取得して返却
		List<PitchersForVerify> list = verifyBatOrderDao.getPitchersFromTeam(parsedTeamId);
		response.setOut(list);
		return response;
	}
}
