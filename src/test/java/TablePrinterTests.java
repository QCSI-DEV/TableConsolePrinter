import implClasses.DBforTest;
import implClasses.TableImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;


public final class TablePrinterTests {
    public void print(String text) {
        System.out.println(text);
    }

    public void testCase(String expectedString, TableImpl table) {
        String realString;

        print(Arrays.toString(table.columnLength()));

        realString = table.printTable();
        print(realString);
        Assert.assertEquals(realString, expectedString);
    }

    @Test
    public void tableWithHeadersAndRowNumbers() {
        String expectedString =
                        "+---+----------+-----+---------------+\n" +
                        "| # | Name     | Age | Address       |\n" +
                        "+---+----------+-----+---------------+\n" +
                        "| 1 | Ann      | 24  | Ukraine       |\n" +
                        "| 2 | Alexandr | 7   | Great Britain |\n" +
                        "+---+----------+-----+---------------+";

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
    public void tableWithoutHeadersWithRowNumbers() {
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
    public void tableWithoutHeadersWithoutRowNumbers() {
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
    public void tableWithHeadersWithoutRowNumbers() {
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


