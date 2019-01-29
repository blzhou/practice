package com.zhoubl.test;

import com.zhoubl.common.DefaultExcelParse;
import com.zhoubl.common.ResultParse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:50
 * @Description TODO(描述该类做什么)
 */
public class ExcelTest {

    static ResultParse parseExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        MyselfValidator myselfValidator = new MyselfValidator();
        DefaultExcelParse defaultExcelParse = new DefaultExcelParse(myselfValidator);
        return defaultExcelParse.parseExcelCommon(inputStream);
    }

    public static void main(String[] args) {
        ExcelTest et = new ExcelTest();
        File file = new File("D:\\zhoubl\\study\\test1.xlsx");
        ResultParse resultParse = new ResultParse();
        try {
            resultParse = ExcelTest.parseExcel(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        System.out.println("hasBlankExcel----------"+resultParse.hasBlankExcel);
        System.out.println("hasErrorMap----------"+resultParse.hasErrorMap);
        if (!resultParse.hasErrorMap && !resultParse.hasBlankExcel) {
            final List<List<String[]>> dataList = resultParse.getDataList();
            for (List<String[]> sheetDatas : dataList) {
                for (String[] sheetData : sheetDatas) {
                    for (String cellVal : sheetData) {
                        System.out.println(cellVal);
                    }
                }
            }
        } else {
            if (resultParse.hasErrorMap) {
                resultParse.getErrorMap();
                for (String key : resultParse.getErrorMap().keySet()) {
                    System.out.println(key + " = " + resultParse.getErrorMap().get(key));
                }
            }
        }
    }
}
