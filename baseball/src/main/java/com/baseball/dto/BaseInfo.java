package com.baseball.dto;

import java.util.Arrays;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

import com.baseball.common.CommonEnum;

public class BaseInfo {
	
	private static CommonEnum.Base[] baseInfo = new CommonEnum.Base[] {
					CommonEnum.Base.FRSTBASE,
					CommonEnum.Base.SCNDBASE,
					CommonEnum.Base.THRDBASE
				};
	
//	public void clearBase() {
//		for(boolean b:baseInfo) {
//			b = false;
//		}
//	}
//	String str = "(" + idList.stream()
//    .map(e-> String.join("id=", e))
//    .collect(Collectors.joining(" OR ")) + ")";
	
	@Override
	public String toString() {
//		String b = 
//				Arrays.stream(baseInfo)
//								.filter(s->s.isOnOff()==true).reduce(",",String::concat);
//		String b= "走者:"+Arrays.stream(baseInfo)
//		.filter(s->s.isOnOff()==true).map(e->e.getName()).collect(Collectors.joining(" , "));
		
//		return Arrays.stream(baseInfo)
//			    .filter(s -> s.isOnOff() == true)
//			    .map(e -> e.getName())
//			    .collect(Collectors.joining(", ", "[", "]"));
		return "";
		
	}
	
}
