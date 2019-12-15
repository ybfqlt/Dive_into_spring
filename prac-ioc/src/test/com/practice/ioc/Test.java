package com.practice.ioc;

import com.practice.ioc.core.JsonApplicationContext;
import com.practice.ioc.test.Robot;

/**
 * @Classname Test
 * @Description TODO
 * @Date 19-12-15 下午6:25
 * @Created by xns
 */
public class Test {
    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot aiRobot = (Robot)applicationContext.getBean("robot");
        aiRobot.show();
    }
}
