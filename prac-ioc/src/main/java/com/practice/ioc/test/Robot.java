package com.practice.ioc.test;

import com.practice.ioc.test.Hand;
import com.practice.ioc.test.Mouth;

/**
 * @Classname Robot
 * @Description TODO
 * @Date 19-12-15 下午5:49
 * @Created by xns
 */
public class Robot {
    private Hand hand;
    private Mouth mouth;

    public void show(){
        hand.waveHand();
        mouth.speak();
    }
}
