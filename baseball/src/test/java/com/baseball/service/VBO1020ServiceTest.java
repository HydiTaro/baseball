package com.baseball.service;

import java.io.IOException;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baseball.dto.BatterForStrategyData;
import com.baseball.dto.req_res.VBO1020RequestDto;
import com.baseball.dto.req_res.VBO1020RequestDto.BattersInput;
import com.baseball.scrape.service.ScrNpbService;
import com.baseball.strategy.InningStrategy01;

@SpringBootTest
public class VBO1020ServiceTest {
	
	@Autowired
	private ScrNpbService scrNpbService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void batterMake() {
		BatterForStrategyData batter = BatterForStrategyData.newBatterData02("周東",0.380,0.300,5,50,10);
		InningStrategy01 strategy = new InningStrategy01();
//		vbo1020Service.vbo1020(null);
		strategy.resultOfOneBatter(batter);
	}
	
	/**
	 * 乱数はEffective JavaによるとThreadLocalRandomクラスを推奨
	 * 
	 * 乱数生成器の選択1回目
	 * Randomクラス：４４ミリ秒、平均得点は不明
	 * ThreadLocalRandomクラス:69ミリ、体金得点は不明
	 * 
	 * 乱数生成器の選択2回目
	 * Randomクラス：1420ミリ秒、平均得点が4.633点
	 * ThreadLocalRandomクラス:1384ミリ秒、平均得点が4.636点
	 * 
	 * 乱数生成器の選択3回目
	 * Randomクラス：1279ミリ秒、平均得点が4.7580点
	 * ThreadLocalRandomクラス:1536ミリ秒、平均得点が4.599点
	 */
	@Test
	public void vbo1020() {
//		BattersInput batter = BattersInput.builder()
//								.fname("周東")
//								.bat("0.3")
//								.obp("0.38")
//								.homerun("20")
//								.steal("40")
//								.bunt("10").build();
//		
//		List<BattersInput> order = Lists.newArrayList(batter,batter,batter,batter,batter,batter,batter,batter,batter); 
//		VBO1020RequestDto request = new VBO1020RequestDto();
//		request.setBattersInput(order);
//		VBOService service = new VBOService();
//		// 処理時間開始
//		long startTime = System.currentTimeMillis();
//		System.out.println();
//		service.vbo1020(request);
//        // 処理終了後の時刻を取得
//        long endTime = System.currentTimeMillis();
//        long elapsedTime = endTime - startTime;
//        // 処理時間を出力
//        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
	}
	
	/**
	 * @throws IOException 
	 */
	@Test
	public void scrapeMaster() throws IOException {
		// 処理時間開始
		long startTime = System.currentTimeMillis();
		// 処理時間開始
		System.out.println(scrNpbService.npbPlayerMasterService());
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
	
	}
	
	/**
	 * @throws IOException 
	 */
	@Test
	public void npbHitterStats() throws IOException {
		long startTime = System.currentTimeMillis();
		// 処理時間開始
//		System.out.println(scrNpbService.npbHitterStats());
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
	}
	
	/**
	 * @throws IOException 
	 */
	@Test
	public void npbHitterThisYear() throws IOException {
		long startTime = System.currentTimeMillis();
		// 処理時間開始
		System.out.println(scrNpbService.npbStatsThisYearService());
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
	}
	
	@Test
	public void jjo() {
		
	}
}
