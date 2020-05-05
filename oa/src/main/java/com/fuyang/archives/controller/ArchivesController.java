package com.fuyang.archives.controller;

import com.fuyang.archives.service.ArchivesService;
import com.fuyang.bean.Archives;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/archives")
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;

    @RequestMapping("/archives-addArchives")
    public String archivesAdd(MultipartFile files) throws IOException {
        if(files != null){
            //先实现上传功能
            String originalFilename = files.getOriginalFilename();
            File file = new File("D:\\OA",originalFilename);
            files.transferTo(file);


            List<Archives> list = new ArrayList<>();
            //再实现Excel的导入功能
            //读取Excel表格文件
            InputStream is = new FileInputStream(file);
            //创建一个Excel文件对象
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            //从对象对应的Excel文件中读sheet表格，生成sheet表格对象
            XSSFSheet hssfSheet = workbook.getSheetAt(0);
            //再从sheet表格中读行
            for(int i = 1;i <= hssfSheet.getLastRowNum();i++){
                //再从一行读每个单元格，读取到了每个单元格的值
                XSSFRow hssfRow = hssfSheet.getRow(i);
                if (hssfRow == null) {
                    continue;
                }
                Archives archive = new Archives();

                //把读取到的一行中每个单元格的值保存到一个实体类对象中，一行就是一个对象
                XSSFCell cell0 = hssfRow.getCell(0);
                if (cell0 == null) {
                    continue;
                }
                archive.setDnum(cell0.getStringCellValue());

                XSSFCell cell1 = hssfRow.getCell(1);
                if (cell1 == null) {
                    continue;
                }
                double cellValue = cell1.getNumericCellValue();
                int value = (int)cellValue;
                archive.setLandline(value+"");

                XSSFCell cell2 = hssfRow.getCell(2);
                if (cell2 == null) {
                    continue;
                }
                archive.setSchool(cell2.getStringCellValue());

                XSSFCell cell3 = hssfRow.getCell(3);
                if (cell3 == null) {
                    continue;
                }
                archive.setZhuanye(cell3.getStringCellValue());

                XSSFCell cell4 = hssfRow.getCell(4);
                if (cell4 == null) {
                    continue;
                }
                archive.setSosperson(cell4.getStringCellValue());

                XSSFCell cell5 = hssfRow.getCell(5);
                if (cell5 == null) {
                    continue;
                }
                archive.setBiyedate(cell5.getDateCellValue());


                XSSFCell cell6 = hssfRow.getCell(6);
                if (cell6 == null) {
                    continue;
                }
                archive.setZzmm(cell6.getStringCellValue());

                XSSFCell cell7 = hssfRow.getCell(7);
                if (cell7 == null) {
                    continue;
                }
                archive.setMinzu(cell7.getStringCellValue());

                XSSFCell cell8 = hssfRow.getCell(8);
                if (cell8 == null) {
                    continue;
                }
                archive.setXueli(cell8.getStringCellValue());

                XSSFCell cell9 = hssfRow.getCell(9);
                if (cell9 == null) {
                    continue;
                }
                archive.setEmail(cell9.getStringCellValue());

                XSSFCell cell10 = hssfRow.getCell(10);
                if (cell10 == null) {
                    continue;
                }
                double cellValue1 = cell10.getNumericCellValue();
                int value1 = (int)cellValue1;
                archive.setEmpFk(value1);

                XSSFCell cell11 = hssfRow.getCell(11);
                if (cell11 == null) {
                    continue;
                }
                archive.setRemark(cell11.getStringCellValue());

                XSSFCell cell12 = hssfRow.getCell(12);
                if (cell12 == null) {
                    continue;
                }
                archive.setHirdate(cell12.getDateCellValue());

                //当所有行读取完成的时候，应该有多个实体类对象，把这些实体类对象放入集合
                list.add(archive);
            }

//            for(Archives ar : list){
//                System.out.println(ar);
//            }

            //最后把集合送到下一层，最后实现保存
            archivesService.save(list);
        }

        return "redirect:/archives.jsp";
    }
}
