package com.zhoubl.common;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author zhoubl
 * @Date 2019/1/25 10:47
 * @Description excel导入导出接口
 */
public interface ExcelParseAware {



    List<List<String[]>> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException;

    List<List<String[]>> parseExcel(InputStream inputStream, Class<?> clazz, String...fields);

    void exportExcel(String exportFileName, List<List<String[]>> dataList, String... headers);

}
