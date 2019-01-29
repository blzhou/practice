package com.zhoubl.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/28 13:51
 * @Description TODO(描述该类做什么)
 */
public class Test {

    public static void main(String[] args) {
        getVars();
        System.out.println("------------------------");
        getVars(1,3);
        stringJoin(new String[]{"1","4","2"});
        stringJoin(new int[]{1,4,5});
    }

    private static void stringJoin(String[] strings) {
        System.err.println(StringUtils.join(strings, ","));
    }

    private static void stringJoin(int[] strings) {
        System.err.println(StringUtils.join(strings, ","));
    }

    private static void getVars(int...idx) {
        if (idx.length == 0) {
            System.out.println('0');
        }
        for (int i : idx) {
            System.out.println(i);
        }
    }


}
