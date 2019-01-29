package com.zhoubl.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:34
 * @Description excel解析默认实现
 */
public class DefaultExcelParse extends AbstractExcelParse {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExcelParse.class);

    private ValidatorAware defaultValidator;

    public DefaultExcelParse(ValidatorAware defaultValidator) {
        this.defaultValidator = defaultValidator;
    }

    public ResultParse parseExcelCommon(InputStream inputStream) throws IOException, InvalidFormatException {
        final List<List<String[]>> lists = this.parseExcel(inputStream);
        if (!hasValidator()) {
            this.defaultValidator = new DefaultValidator();
        }
        return this.defaultValidator.nullValid(lists, 2);
    }

    private boolean hasValidator() {
        return this.defaultValidator == null ? false : true;
    }

    @Override
    public List<List<String[]>> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        return parseExcel(inputStream, 1);
    }

    private String getCellData(Cell cell) {
        String cellVal = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellVal = sdf.format(cell.getDateCellValue());
                    } else {
                        double cellValue = cell.getNumericCellValue();
                        int newVal = (int)cellValue;
                        cellVal = newVal - cellValue == 0 ? String.valueOf(newVal) : String.valueOf(cellValue);
                    }
                    break;
                case STRING:
                    cellVal = StringUtils.trim(cell.getStringCellValue());
                    break;
                case BLANK:
                    cellVal = "";
                    break;
                case BOOLEAN:
                    cellVal = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    cellVal = cell.getCellFormula();
                    break;
                default:
                    cellVal = "!#REF!";
                    break;
            }
        }
        return cellVal;
    }

    @Override
    public List<List<String[]>> parseExcel(InputStream inputStream, int startParseRowIndex) throws IOException, InvalidFormatException {
        return parseExcel(inputStream, 0, startParseRowIndex);
    }

    @Override
    public List<List<String[]>> parseExcel(InputStream inputStream, int designateSheetIndex, int startParseRowIndex) throws IOException, InvalidFormatException {
        final Workbook workbook = WorkbookFactory.create(inputStream);
        final int numberOfSheets = workbook.getNumberOfSheets();
        Sheet currentSheet;
        int totalColumns;
        List<List<String[]>> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfSheets; i++) {
            currentSheet = workbook.getSheetAt(i);
            if (currentSheet.getPhysicalNumberOfRows() <= 0) {
                LOG.info("当前工作薄是空,跳过!");
                continue;
            }
            //从标题列获取总列数,比较准确
            totalColumns = currentSheet.getRow(0).getPhysicalNumberOfCells();
            List<String[]> rowList = new ArrayList<>(currentSheet.getLastRowNum());
            for (int j = startParseRowIndex; j <= currentSheet.getLastRowNum(); j++) {
                final Row currentSheetRow = currentSheet.getRow(j);
                if (currentSheetRow == null) {
                    LOG.info("当前行是空,跳过!");
                    continue;
                }
                String[] datas = new String[totalColumns];
                for (int k = 0; k < totalColumns; k++) {
                    final Cell sheetRowCell = currentSheetRow.getCell(k);
                    datas[k] = getCellData(sheetRowCell);
                }
                rowList.add(datas);
            }
            resultList.add(rowList);
            LOG.info("所有的sheet解析成功!");
        }
        return resultList;
    }


    @Override
    public List<List<String[]>> parseExcel(InputStream inputStream, Class<?> clazz, String... fields) {
        return super.parseExcel(inputStream, clazz, fields);
    }

    @Override
    public void exportExcel(String exportFileName, List<List<String[]>> dataList, String... headers) {
        super.exportExcel(exportFileName, dataList, headers);
    }
}
