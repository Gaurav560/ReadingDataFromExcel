package com.telusko.helper;

import com.telusko.model.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonHelper {


    //this method checks if file is excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.ms-excel") || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }


    //this method converts excel into list of persons
    public static List<Person> convertExcelToPerson(InputStream inputStream) {

        List<Person> persons = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellId = 0;

                Person person = new Person();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellId) {
                        case 0:
                            person.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            person.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            person.setAge((int) cell.getNumericCellValue());
                            break;
                        case 3:
                            person.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            person.setAddress(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellId++;
                }
                persons.add(person);
            }


        } catch (Exception e) {
            e.printStackTrace();


        }
        return persons;
    }
}
