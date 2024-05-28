package com.baseball.common;
public class OrderUtil {
    
    public static int fromOrderNoToIndex(int orderNo){
        return orderNo-1;
    }

    public static int fromIndexToOrderNo(int index){
        return index+1;
    }
}
