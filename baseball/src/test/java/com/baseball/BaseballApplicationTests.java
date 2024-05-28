package com.baseball;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baseball.dto.BatterForStrategyData;
import com.baseball.dto.req_res.VBO1020RequestDto;
import com.baseball.dto.req_res.VBO1020RequestDto.BatterInput;
import com.baseball.service.VBOService;
import com.baseball.strategy.InningStrategy01;

@SpringBootTest
class BaseballApplicationTests {
	
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
	
	@Test
	public void vbo1020() {
		BatterInput batter = BatterInput.builder()
								.fname("周東")
								.bat("0.3")
								.obp("0.38")
								.homerun("5")
								.steal("40")
								.bunt("10").build();
		
		List<BatterInput> order = Lists.newArrayList(batter,batter,batter,batter,batter,batter,batter,batter,batter); 
		VBO1020RequestDto request = new VBO1020RequestDto();
		request.setBatterOrder(order);
		VBOService service = new VBOService();
		service.vbo1020(request);
	}
}
