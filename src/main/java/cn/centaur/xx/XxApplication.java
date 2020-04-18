package cn.centaur.xx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class XxApplication {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String dir = System.getProperty("user.dir");
        if (args.length == 1) {
            String path = dir + "/" + args[0];
            String target = dir + "/" + args[0] + ".xlsx";
            convert(path, target);
        } else if (args.length == 0) {
            File file = new File(dir);
            List<File> fs = Arrays.stream(file.listFiles()).filter(f -> (f.getName().endsWith(".xml") || f.getName().endsWith(".XML")) && f.isFile()).collect(Collectors.toList());
//            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
            fs.parallelStream().forEach(f -> {
                String path = dir + "/" + f.getName();
                String target = dir + "/" + f.getName() + ".xlsx";
                try {
                    convert(path, target);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        long end = System.currentTimeMillis();
        System.out.println("time cost: " + (end - start) / 1000 + "(s)");
    }

    public static void convert(String src, String target) throws Exception {

        String path = src;
        System.out.println("converting:" + path);
        String xmlStr = Xml2Json.readFile(path);
        JSONObject jo = Xml2Json.xml2Json(xmlStr);
        JSONArray ja = jo.getJSONObject("Worksheet").getJSONObject("Table").getJSONArray("Row");
        XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");
        for (int i = 0; i < ja.size(); i++) {
            JSONObject o = ja.getJSONObject(i);
            JSONArray a = o.getJSONArray("Cell");
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < a.size(); j++) {
                XSSFCell cell = row.createCell(j, CellType.STRING);
                JSONObject o2 = a.getJSONObject(j);
                String data = o2.getString("Data");
                cell.setCellValue(data);
            }
        }
//        workbook.write(new FileOutputStream("/Users/zh/tmp/s.xlsx"));
        workbook.write(new FileOutputStream(target));
        System.out.println("converting:" + path + " success ");
    }

}
