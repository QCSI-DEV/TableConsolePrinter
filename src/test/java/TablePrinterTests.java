import implClasses.DBForTest;
import implClasses.TableImpl;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;

public final class TablePrinterTests {

    public void doAssert(String expected, TableImpl table) throws IOException {
        Assert.assertEquals(table.printTable(), expected);
    }

    public String readFile(String fileName) {
        try {
            return FileUtils.readFileToString(new File(getClass().getClassLoader().getResource(fileName).getPath()), "UTF-8");
            //return FileUtils.readFileToString(new File("D:\\Education\\TableConsolePrinter\\src\\test\\resources\\"+fileName),"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tableWithHeadersAndRowNumbers() throws IOException {
        String expectedString = readFile("with_all_file.txt");

        DBForTest db = new DBForTest(100,100);
        TableImpl table = TableImpl.create(db.createHeaders(), db.createValues(), true);
        doAssert(expectedString, table);
    }

    @Test
    public void tableWithoutHeadersWithRowNumbers() throws IOException {
        String expectedString = readFile("without_headers.txt");

        DBForTest db = new DBForTest(100,100);
        TableImpl table = TableImpl.create(false, db.createValues(), true);
        doAssert(expectedString, table);
    }

    @Test
    public void tableWithHeadersWithoutRowNumbers() throws IOException {
        String expectedString = readFile("without_rowNumbers.txt");

        DBForTest db = new DBForTest(100,100);
        TableImpl table = TableImpl.create(db.createHeaders(), db.createValues(), false);
        doAssert(expectedString, table);
    }

    @Test
    public void tableWithoutHeadersWithoutRowNumbers() throws IOException {
        String expectedString = readFile("without_all.txt");

        DBForTest db = new DBForTest(100,100);
        TableImpl table = TableImpl.create(false, db.createValues(), false);
        doAssert(expectedString, table);
    }
}


