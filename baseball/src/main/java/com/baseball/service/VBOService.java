package com.baseball.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseball.Verify;
import com.baseball.dao.VerifyBatOrderDao;
import com.baseball.dto.BatterForStrategyData;
import com.baseball.dto.GamesStats;
import com.baseball.dto.req_res.VBO1010RequestDto;
import com.baseball.dto.req_res.VBO1010ResponseDto;
import com.baseball.dto.req_res.VBO1010ResponseDto.BattersForVerify;
import com.baseball.dto.req_res.VBO1020RequestDto;
import com.baseball.dto.req_res.VBO1020RequestDto.BattersInput;
import com.baseball.dto.req_res.VBO1020ResponseDto;
import com.baseball.dto.req_res.VBO1030RequestDto;
import com.baseball.dto.req_res.VBO1030ResponseDto;
import com.baseball.dto.req_res.VBO1030ResponseDto.PitchersForVerify;
import com.baseball.dto.req_res.VBO1040RequestDto;
import com.baseball.dto.req_res.VBO1040ResponseDto;
import com.baseball.strategy.InningStrategy;
import com.baseball.strategy.InningStrategy02;

@Service
public class VBOService {
	
	@Autowired
	VerifyBatOrderDao verifyBatOrderDao;
	
	/**
	 * チームの打撃成績取得
	 * @param vbo1010RequestDto
	 * @return
	 */
	public VBO1010ResponseDto vbo1010(VBO1010RequestDto vbo1010RequestDto) {
		VBO1010ResponseDto response = new VBO1010ResponseDto();
		int parsedTeamId = Integer.parseInt(vbo1010RequestDto.getTeamId());
		// 選手の成績を取得して返却
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
//		打順のチェック
//		checkRequest1020(vbo1020RequestDto);
		
		List<BatterForStrategyData> batterOrder = new ArrayList<BatterForStrategyData>();
		BatterForStrategyData newBatter;
		//  オーダーのマッピング
		for(BattersInput batterInput : vbo1020RequestDto.getBattersInput()) {
			newBatter = BatterForStrategyData.newBatterData02(batterInput.getFname()
							,Double.parseDouble(batterInput.getObp())
							,Double.parseDouble(batterInput.getBat())
							,Integer.parseInt(batterInput.getHomerun())
							,Integer.parseInt(batterInput.getSteal())
							,Integer.parseInt(batterInput.getBunt()));
			System.out.printf("Input:%2.4f, ParsedObps:%d, ParsedKK:%d\n"
					,Double.parseDouble(batterInput.getObp())
					,newBatter.getObps()
					,newBatter.getKks());
			batterOrder.add(newBatter);
		}
		InningStrategy strategy02 = new InningStrategy02();
		Verify verify = new Verify(strategy02,batterOrder);
		GamesStats gamesStats = verify.verify("",1);
		gamesStats.printData();
		//打率と本塁打率から、一塁打、二塁打の率を計算（計算式は全打者共通）、盗塁数からスリーベースを計算
		VBO1020ResponseDto response = VBO1020ResponseDto.builder()
											.aveScore(gamesStats.getAverageScore())
											.gameScore(gamesStats.GameScore())
											.hits(gamesStats.getHits())
											.singles(gamesStats.getSingles())
											.doubles(gamesStats.getDoubles())
											.triples(gamesStats.getTriples())
											.homeruns(gamesStats.getHomeruns()).build();
		System.out.println(response.getAveScore()+response.getHomeruns());
		return response;
	}
	
	/**
	 * 打順のチェック
	 * TODO　打順が1～９のすべて揃っているか、守備が1～9までそろっているか
	 * @param vbo1020RequestDto
	 */
	private void checkRequest1020(VBO1020RequestDto vbo1020RequestDto) {
		// 打者人数が9人であること
		if(vbo1020RequestDto.getBattersInput().size()!=9) {
//			throw ;
			//エラーの返却
		// 守備位置にひとりずついる（外野は3人）
		} else {
			// 打順チェック
			for(BattersInput batterInput : vbo1020RequestDto.getBattersInput()) {
				batterInput.getPosition();
			}
		}
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
	
	/**
	 * 今年度投手のデータ取得
	 * @param vbo1010RequestDto
	 * @return
	 */
	public VBO1040ResponseDto vbo1040(VBO1040RequestDto vbo1040RequestDto) {
		VBO1040ResponseDto response = new VBO1040ResponseDto();
		int parsedTeamId = Integer.parseInt(vbo1040RequestDto.getTeamId());
		// 選手の成績を取得して返却
//		response.setInningDetail();
		return response;
	}
}
