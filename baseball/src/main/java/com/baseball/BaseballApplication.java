package com.baseball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseballApplication {
	
	public static void main(String[] args) throws Exception {
		System.out.println("baseball project Started!");
		SpringApplication.run(BaseballApplication.class, args);
		System.out.println(maxYakusu(5039,10000));
//		System.out.println(getYakusuNum());
//     // ここに時間計測したい処理を記述
	}
	/**
	 * 所定の値以下の値で最も約数の多いテーブル
	 * @throws Exception 
	 * 
	 */
	private static int maxYakusu(int minInt,int maxInt) throws Exception {
		if(minInt<2|| maxInt<2) {
			throw new Exception();
		}
		int tmpResult = 1;
		int aaa = 1;
		for(int i=minInt;i<maxInt;i++) {
			aaa = getYakusuNum(i);
			if(tmpResult < aaa) {
				tmpResult = aaa;
				System.out.println("値が更新されました！:"+i);
			} else if(tmpResult == aaa) {
				System.out.println("同じ個数です:"+i);
			}
		}
		return tmpResult;
	}
	/**
	 * 約数の個数を算出する
	 */
	private static int getYakusuNum(int a) {
		Map<Integer,Integer> m = new HashMap<>();
		Map<Integer,Integer> map = getElements(m,a,2);
//		System.out.println(map);
		int total =1;
		for(Entry<Integer,Integer> i : map.entrySet()) {
//			System.out.println(i.getValue());
			total *= i.getValue()+1;
		}
		return total;
	}
	
	/**
	 * 特定の値で最初に割り切れる数を取得する(再帰することで初期値の約数を取得できる)
	 * ループが二重の場合はrecursiveで書くとクリーンになる
	 */
	private static Map<Integer,Integer> getElements(Map<Integer,Integer> map,int a,int b) {
//		System.out.println(map+" "+a+" "+b);
		if(a+1!=b) {
			if(a%b==0) {
				if(!map.containsKey(b)) {
					map.put(b, 1);
				} else {
					map.put(b, map.get(b)+1);
				}
				if(a/b != 1 ) {
					getElements(map,a/b,2);
				}
			} else {// 割り切れない
				getElements(map,a,b+1);
			}
		}
		return map;
		
	}
}
