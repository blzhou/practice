package com.zhoubl.common;

import java.util.List;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:35
 * @Description 默认实现的校验器
 */
public class DefaultValidator extends AbstractValidator {

    @Override
    public ResultParse nullValid(List<List<String[]>> resultList, int offset) {
        System.out.println("----user define nullValid-------");
        return super.nullValid(resultList, offset);
    }

    @Override
    public ResultParse repeatValid(List<List<String[]>> resultList, int... designateIndex) {
        System.out.println("----user define repeatValid-------");
        return super.repeatValid(resultList, designateIndex);
    }
}
