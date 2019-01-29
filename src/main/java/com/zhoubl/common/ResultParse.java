package com.zhoubl.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 12:26
 * @Description excel导入解析成功且校验后的实体对象
 */
public class ResultParse implements Serializable {

    private static final long serialVersionUID = -5453483433314076149L;

    //错误提示默认key
    public static final String ERROR_MESSAGE_KEY = "errorMsg";

    public boolean hasBlankExcel = false;

    public boolean hasErrorMap = false;

    public Map<String, Object> errorMap;

    public List<List<String[]>> dataList;

    public Map<String, Object> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, Object> errorMap) {
        this.errorMap = errorMap;
    }

    public List<List<String[]>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<String[]>> dataList) {
        this.dataList = dataList;
    }

    public boolean isHasErrorMap() {
        return hasErrorMap;
    }

    public void setHasErrorMap(boolean hasErrorMap) {
        this.hasErrorMap = hasErrorMap;
    }

    public boolean isHasBlankExcel() {
        return hasBlankExcel;
    }

    public void setHasBlankExcel(boolean hasBlankExcel) {
        this.hasBlankExcel = hasBlankExcel;
    }
}
