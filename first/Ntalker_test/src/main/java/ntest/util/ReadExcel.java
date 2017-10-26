package ntest.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2017/1/18.
 * 读取和处理测试用例输入文件
 */
public class ReadExcel {

    private String path;
    private JSONArray _array = new JSONArray();

    public ReadExcel(String path) {
        this.path = path;
    }

    /**
     * 从Excel读取测试用例
     */
    public JSONArray readExcelXlsx() {
        try {
            InputStream xlsxfile = new FileInputStream((path));

            try {
//                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(xlsxfile);
                HSSFWorkbook xssfWorkbook = new HSSFWorkbook(xlsxfile);
                //遍历Excel当前的页数据
                for (int sheetNum = 0; sheetNum < xssfWorkbook.getNumberOfSheets(); sheetNum++) {
                    HSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetNum);
                    HSSFRow xssfRowtile = xssfSheet.getRow(0);//获取表格的标题名称
                    //遍历Excel当前页的行数据
                    for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                        HSSFRow xssfRow = xssfSheet.getRow(rowNum);//获取表中的行数
                        //创建json对象，把数据装入json
                        JSONObject object = new JSONObject();
                        //遍历Excel当前行的列数据
                        for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
                            HSSFCell xssfCelltile = xssfRowtile.getCell(cellNum);
                            HSSFCell xssfCell = xssfRow.getCell(cellNum);
//                            System.out.println("test:"+xssfCelltile.toString()+"   test2:"+xssfCell.toString());
                            object.put(xssfCelltile.toString(), xssfCell.toString());
                        }
                        _array.add(object);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                _array = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _array = null;
        }
        return _array;

    }

    public List getFistColumn() {
        List li = new ArrayList();
        try {
            InputStream xlsxfile = new FileInputStream(path);
            HSSFWorkbook xssfWorkbook = new HSSFWorkbook(xlsxfile);
            for (int sheemNum = 0; sheemNum < xssfWorkbook.getNumberOfSheets(); sheemNum++) {
                HSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheemNum);
                for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    HSSFCell cell = xssfRow.getCell(0);
                    li.add(cell.toString());
                }
            }


        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("-----------");
            System.out.println(e.getMessage());
            System.out.println("-----------");
            e.printStackTrace();

        }

       return li;
    }


}