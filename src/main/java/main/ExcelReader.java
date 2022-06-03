package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Index;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static final String excelFile = "./Example.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {

        // collecting all objects here
        ArrayList<BigObject> objectArray = new ArrayList<>();

        // Creating a Workbook from an Excel file
        Workbook workbook = WorkbookFactory.create(new File(excelFile));

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
        // prints all the objects we made so far
        for (int i = 0; i < objectArray.size(); i++) {
            System.out.print(objectArray.get(i).getObjectName());
            System.out.println("\t" + objectArray.get(i).getIo());
        }

        // testing getObjectIndx() method
        int testIndx = getObjectIndx("object3", objectArray);
        try {
            System.out.print(objectArray.get(testIndx).getObjectName());
            System.out.println("\t" + objectArray.get(testIndx).getIo());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("-1");
        }
        // end testing

        // Closing the workbook
        workbook.close();
    }

    // returns the index of the object we will append to
    public static int getObjectIndx(String objName, ArrayList<BigObject> objectArray) {
        for (int i = 0; i < objectArray.size(); i++) {
            if (objectArray.get(i).getObjectName() == objName)
                return i;
        }
        // return -1 if not found
        return -1;
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
}

// I/o || Field Name || Type || Allowed Values || Mandatory
// // 0 || 1 || 2 || 3 || 4
// for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
// final Row row = sheet.getRow(i);

// for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
// Cell cell = row.getCell(j);
// if (cell != null &&
// cell.getRichStringCellValue().getString().startsWith("/object")) {
// String[] FieldNames = cell.getRichStringCellValue().getString().split("/");
// int indx = objIndx(FieldNames);
// objectArray.add(new BigObject());
// }
// }
// System.out.println();
// }

// for (int i = 0; i < objectArray.size(); i++) {
// System.out.print(objectArray.get(i).getIo() + "\t");
// System.out.println(objectArray.get(i).getName());
// }

// Retrieving the number of sheets in the Workbook
// System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets
// : ");

// System.out.println("The Sheets in the Excel file : ");
// for (Sheet sheet : workbook) {
// System.out.println("\t -> " + sheet.getSheetName());
// }