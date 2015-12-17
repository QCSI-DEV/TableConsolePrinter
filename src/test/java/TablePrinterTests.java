import implClasses.DBforTest;
import implClasses.TableImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Arrays;


public final class TablePrinterTests {
    public void print(String text) {
        System.out.println(text);
    }

    public void testCase(String expectedString, TableImpl table) throws IOException {
        String realString;

        print(Arrays.toString(table.columnLength()));

        realString = table.printTable();
        print(realString);
        makeFileExpectedString(table.printTable());
        Assert.assertEquals(realString, expectedString);
    }
    public void makeFileExpectedString(String s) throws IOException {
       
        File outFile = new File ("expectedString");
        FileWriter fWriter = new FileWriter (outFile);
        PrintWriter pWriter = new PrintWriter (fWriter);

        try {
            pWriter.write(s);
        }finally {
            if (pWriter != null) {
                pWriter.close();
            }
        }
    }

    public String readFile() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("expectedString"));
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return sb.toString();
    }

    @Test
    public void tableWithHeadersAndRowNumbers() throws IOException {

        String expectedString = readFile();


        DBforTest db = new DBforTest(100,100);
        db.createHeaders();
        db.createValues();

        String[] headersTest = db.fillHeaders();
        String[][] valuesTest = db.fillValues();

        print("tableWithHeadersAndRowNumbers");
        TableImpl table = TableImpl.create(headersTest, valuesTest, true);

        testCase(expectedString, table);

    }

    @Test
    public void tableWithoutHeadersWithRowNumbers() throws IOException {
        String expectedString =

                        "+---+----------+----+---------------+\n" +
                        "| 1 | Ann      | 24 | Ukraine       |\n" +
                        "| 2 | Alexandr | 7  | Great Britain |\n" +
                        "+---+----------+----+---------------+";

        String[][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Alexandr", "7", "Great Britain"}};


        print("tableWithoutHeadersWithRowNumbers");

        TableImpl table = TableImpl.create(false, valuesTest, true);
        testCase(expectedString, table);
    }

    @Test
    public void tableWithoutHeadersWithoutRowNumbers() throws IOException {
        String expectedString =

                        "+----------+----+---------------+\n" +
                        "| Ann      | 24 | Ukraine       |\n" +
                        "| Alexandr | 7  | Great Britain |\n" +
                        "+----------+----+---------------+";

        String[][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Alexandr", "7", "Great Britain"}};

        print("tableWithoutHeadersWithoutRowNumbers");
        TableImpl table = TableImpl.create(false, valuesTest, false);
        testCase(expectedString, table);

    }

    @Test
    public void tableWithHeadersWithoutRowNumbers() throws IOException {
        String expectedString =
                        "+----------+-----+---------------+\n" +
                        "| Name     | Age | Address       |\n" +
                        "+----------+-----+---------------+\n" +
                        "| Ann      | 24  | Ukraine       |\n" +
                        "| Alexandr | 7   | Great Britain |\n" +
                        "+----------+-----+---------------+";


        String[] headersTest = {"Name", "Age", "Address"};
        String[][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Alexandr", "7", "Great Britain"}};

        print("tableWithHeadersWithoutRowNumbers");
        TableImpl table = TableImpl.create(headersTest, valuesTest, false);
        testCase(expectedString, table);

    }
}


