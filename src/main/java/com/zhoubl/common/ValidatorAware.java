package com.zhoubl.common;

import java.util.List;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:41
 * @Description excel校验接口
 */
public interface ValidatorAware {

    ResultParse nullValid(List<List<String[]>> resultList, int offset);

    ResultParse repeatValid(List<List<String[]>> resultList, int...designateIndex);
}
