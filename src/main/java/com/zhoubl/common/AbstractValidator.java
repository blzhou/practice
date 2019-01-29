package com.zhoubl.common;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:14
 * @Description 数据校验抽象类
 */
public abstract class AbstractValidator implements ValidatorAware {

    @Override
    public ResultParse nullValid(List<List<String[]>> resultList, int offset) {
        ResultParse resultParse = new ResultParse();
        if (resultList == null || resultList.isEmpty()) {
            resultParse.setHasBlankExcel(true);
            resultParse.setHasErrorMap(false);
            return resultParse;
        }
        StringBuilder msgSb = new StringBuilder();
        int sheetIndex = 0;
        boolean hasErrorMaps = false;
        List<String> nullRowIndex;
        for (List<String[]> sheetDatas : resultList) {
            boolean hasErrorMap = false;
            int rowIndex = 0;
            nullRowIndex = new ArrayList<>();
            sheetIndex++;
            for (String[] datas : sheetDatas) {
                for (String data : datas) {
                    if (data == null || StringUtils.trim(data).isEmpty()) {
                        if (!hasErrorMap) {
                            rowIndex += offset;
                            hasErrorMap = true;
                        }
                        nullRowIndex.add(String.valueOf(rowIndex));
                        break;
                    }
                }
                rowIndex++;
            }
            if (hasErrorMap) {
                hasErrorMaps = true;
                msgSb.append("第[").append(sheetIndex).append("]工作薄,第[").append(StringUtils.join(nullRowIndex, ","))
                        .append("]行有空字符,请重新填写!\r\n");
            }
        }
        resultParse.setHasErrorMap(hasErrorMaps);
        if (hasErrorMaps) {
            Map<String, Object> errorMap = new LinkedHashMap<>(1);
            errorMap.put(ResultParse.ERROR_MESSAGE_KEY, msgSb.toString());
            resultParse.setErrorMap(errorMap);
            return resultParse;
        }
        resultParse.setDataList(resultList);
        return resultParse;
    }

    @Override
    public ResultParse repeatValid(List<List<String[]>> resultList, int...designateIndex) {
        ResultParse resultParse = new ResultParse();
        int[] checkDesignateNum;
        if (designateIndex == null || designateIndex.length == 0) {
            checkDesignateNum = new int[0];
        } else {
            checkDesignateNum = designateIndex;
        }

        StringBuilder checkDataStrs;
        Set<String> targetSet;
        int sheetIdx = 0;
        StringBuilder msgSb = new StringBuilder();
        for (List<String[]> sheetDatas : resultList) {
            targetSet = new HashSet<>();
            for (String[] rowDatas : sheetDatas) {
                checkDataStrs = new StringBuilder();
                int colNum = 0;
                for (String cellData : rowDatas) {
                    for (int designateNum : checkDesignateNum) {
                        if (colNum == designateNum) {
                            checkDataStrs.append(cellData);
                            break;
                        }
                    }
                    colNum++;
                }
                targetSet.add(checkDataStrs.toString());
            }
            if (targetSet.size() != sheetDatas.get(sheetIdx).length) {
                msgSb.append("第[").append(sheetIdx).append("]工作薄,有重复数据!\r\n");
                if (!resultParse.hasErrorMap) {
                    resultParse.setHasErrorMap(true);
                }
            }
            sheetIdx++;
        }
        resultParse.setDataList(resultList);
        return resultParse;
    }
}
