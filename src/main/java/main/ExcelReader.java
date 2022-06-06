package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static final String EXCEL_FILE = "./Example.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        // collecting all objects here
        ArrayList<BigObject> objectArray = new ArrayList<>();

        // Creating a Workbook from an Excel file
        Workbook workbook = WorkbookFactory.create(new File(EXCEL_FILE));

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // get all objects first
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            final Row row = sheet.getRow(i);
            for (int j = 0; (j < row.getPhysicalNumberOfCells()); j++) {
                Cell cell = row.getCell(j);
                if ((cell != null) && (cell.getRichStringCellValue().getString().startsWith("I")
                        || (cell.getRichStringCellValue().getString().startsWith("O")))) {
                    if (row.getCell(j + 2).getRichStringCellValue().getString().startsWith("object")) {
                        objectArray.add(new BigObject(row.getCell(j + 2).getRichStringCellValue().getString(),
                                row.getCell(j).getRichStringCellValue().getString()));
                    }
                }
            }
        }

        // mapping fields to objects
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            final Row row = sheet.getRow(i);
            for (int j = 0; (j < row.getPhysicalNumberOfCells()) && row.getCell(j) != null; j++) {
                Cell cell = row.getCell(j);
                if ((cell.getRichStringCellValue().getString().equals("I"))
                        || (cell.getRichStringCellValue().getString().equals("O"))) {
                    cell = row.getCell(j + 1);
                    String[] fieldName = cell.getRichStringCellValue().getString().split("/");
                    String tempFieldName = fieldName[fieldName.length - 1];
                    String tempObjName = fieldName[fieldName.length - 2];
                    String tempType = row.getCell(j + 2).getRichStringCellValue().getString();
                    String tempAllowedVals = row.getCell(j + 3).getRichStringCellValue().getString();
                    String tempMandatory = row.getCell(j + 4).getRichStringCellValue().getString();
                    int objectIndx = getObjectIndx(tempObjName, objectArray);
                    if (row.getCell(j + 3).getRichStringCellValue().getString().equals(""))
                        tempAllowedVals = "-1";
                    try {
                        objectArray.get(objectIndx)
                                .addField(new Field(tempFieldName, tempType, tempAllowedVals, tempMandatory));
                    } catch (IndexOutOfBoundsException e) {
                        if (tempFieldName.startsWith("field"))
                            objectArray.add(new BigObject(tempFieldName,
                                    row.getCell(j + 2).getRichStringCellValue().getString()));
                    }
                }
            }
        }

        // prints all the objects we made so far
        for (int i = 0; i < objectArray.size(); i++) {
            System.out.print(objectArray.get(i).getObjectName());
            for (int j = 0; j < objectArray.get(i).getFields().size(); j++) {
                System.out.print("\t \t" + objectArray.get(i).getField(j).getFieldName());
                System.out.print("\t \t" + objectArray.get(i).getField(j).getType());
                System.out.print("\t \t" + objectArray.get(i).getField(j).getAllowedVals());
                System.out.print("\t \t" + objectArray.get(i).getField(j).getMandatory());
            }
            System.out.println("\t \t" + objectArray.get(i).getIo());
        }
        // Closing the workbook
        workbook.close();
    }

    // returns the index of the object we will append to
    public static int getObjectIndx(String objName, ArrayList<BigObject> objectArray) {
        for (int i = 0; i < objectArray.size(); i++) {
            if (objectArray.get(i).getObjectName().equals(objName))
                return i;
        }
        // return -1 if not found
        return -1;
    }
}
