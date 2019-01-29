package com.zhoubl.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:32
 * @Description excel抽象解析类
 */
public abstract class AbstractExcelParse implements ExcelParseAware {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExcelParse.class);

    public abstract List<List<String[]>> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException;

    public abstract List<List<String[]>> parseExcel(InputStream inputStream, int startParseRowIndex) throws IOException, InvalidFormatException;

    public abstract List<List<String[]>> parseExcel(InputStream inputStream, int designateSheetIndex, int startParseRowIndex) throws IOException, InvalidFormatException;

    @Override
    public List<List<String[]>> parseExcel(InputStream inputStream, Class<?> clazz, String... fields) {
        return null;
    }

    @Override
    public void exportExcel(String exportFileName, List<List<String[]>> dataList, String... headers) {

    }

}
