package com.mec.dida.test;

import com.mec.dida.core.Dida;

import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        new Dida() {
            @Override
            public void work() {
                int n = 0;
                for (int i = 0; i < 1000000; i++) {
                   //定时要做的工作
                    n += i;
                }
                long currentTime = System.currentTimeMillis();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = format.format(currentTime);
                long mill = currentTime / 1000000000 / 10;
                System.out.println(time + ":" + mill);
            }
        }.setTime(2000).start();
    }


}
