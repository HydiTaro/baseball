package com.baseball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BaseballApplication {
	
	public static void main(String[] args) {
		System.out.println("Started!");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("Aa123456"));
		System.out.println(encoder.encode("QWE123"));
		SpringApplication.run(BaseballApplication.class, args);
		
//        BatterData y = new BatterData("山川",500,330,250, 20 , 0, 35, 100,100,0,15);
//        BatterData i = new BatterData("今宮",500, 300, 255, 20, 0, 3,58,30,10,10);
//        BatterData s = new BatterData("周東",500, 370, 300, 20, 3, 10, 80, 60,5,5);
//
//        BatterData[] orderOld = {s,i,s,y,y,i,i,i,s};
//        BatterData[] orderNew1 = {s,s,y,y,s,i,i,i,i};
//        BatterData[] orderNew2 = {y,y,s,s,s,i,i,i,i};
//        BatterData[] orderBad = {i,i,i,i,s,s,s,y,y};
//        
//     // ここに時間計測したい処理を記述
//        
//        Strategy s01 = new Strategy01();
//        long startTime = System.nanoTime(); // 処理の開始時間
//        Verify verify = new Verify(s01,orderOld);
//        verify.verify("古い",10000);
//        long endTime = System.nanoTime(); // 処理の終了時間
//        System.out.println(endTime-startTime); // 処理にかかった時間(ナノ秒)
//        verify = new Verify(s01,orderNew1);
//        verify.verify("やや新",100000);
//        verify = new Verify(s01,orderNew2);
//        verify.verify("新しい",100000);
//        verify = new Verify(s01,orderBad);
//        verify.verify("悪い",100000);
	}

}
