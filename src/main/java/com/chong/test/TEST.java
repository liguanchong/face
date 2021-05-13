package com.chong.test;

import com.chong.utils.FaceUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.Random;

/**
 * @Author: chong
 * @Date: 2021-03-20
 */
public class TEST {
    public static void main(String[] args) {
            File file = new File("D:\\3.jpg");
        String string;

        {
            try {
                string = FaceUtil.detect(file);
                System.out.println(string);
                boolean res = FaceUtil.search(string);
                System.out.println(res);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void test() {
        BigDecimal random = makeRandom(39f, 36f, 1);
        System.out.println(random);
    }

    /**
     * 生成指定范围，指定小数位数的随机数
     * @param max 最大值
     * @param min 最小值
     * @param scale 小数位数
     * @return
     */
    BigDecimal makeRandom(float max, float min, int scale){
        BigDecimal cha = new BigDecimal(Math.random() * (max-min) + min);
        return cha.setScale(scale,BigDecimal.ROUND_HALF_UP);//保留 scale 位小数，并四舍五入
    }
}
