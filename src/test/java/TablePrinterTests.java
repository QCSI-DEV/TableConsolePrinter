import implClasses.TableImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;


public final class TablePrinterTests {

    @Test
    public void tableWithHeadersAndRowNumbers() {
        String realString;
        String expectedString =
                 "+--+----------+-----+---------------+\n" +
                 "|# | Name     | Age | Address       |\n" +
                 "+--+----------+-----+---------------+\n" +
                 "|1 | Ann      | 24  | Ukraine       |\n" +
                 "|2 | Alexandr | 7   | Great Britain |\n" +
                 "+--+----------+-----+---------------+";

        String[] headersTest = {"Name", "Age", "Address"};
        String[][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Alexandr", "7", "Great Britain"}};
        boolean withRowNumbersTest = true;


        TableImpl table = TableImpl.create(headersTest, valuesTest, withRowNumbersTest);

        System.out.println("tableWithHeadersAndRowNumbers");
        System.out.println(Arrays.toString(table.columnLength()));

        realString = table.printTable();
        System.out.println(realString);
        Assert.assertEquals(realString, expectedString);
    }
    @Test
    public void tableWithoutHeadersWithRowNumbers() {
        String realString;
        String expectedString =

                        "+--+----------+-----+---------------+\n" +
                        "|1 | Ann      | 24  | Ukraine       |\n" +
                        "|2 | Alexandr | 7   | Great Britain |\n" +
                        "+--+----------+-----+---------------+";

        String[][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Alexandr", "7", "Great Britain"}};
        boolean withRowNumbersTest = true;
        boolean withHeaders=false;

        TableImpl table = TableImpl.create(withHeaders, valuesTest, withRowNumbersTest);
        System.out.println("tableWithoutHeadersWithRowNumbers");
        System.out.println(Arrays.toString(table.columnLength()));

        realString = table.printTable();
        System.out.println(realString);
        Assert.assertEquals(realString, expectedString);
    }
    @Test
    public void tableWithoutHeadersWithoutRowNumbers() {
        String realString;
        String expectedString =

                        "+----------+----+---------------+\n" +
                        "| Ann      | 24 | Ukraine       |\n" +
                        "| Alexandr | 7  | Great Britain |\n" +
                        "+----------+----+---------------+";

        String[][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Alexandr", "7", "Great Britain"}};
        boolean withRowNumbersTest = false;
        boolean withHeaders=false;

        TableImpl table = TableImpl.create(withHeaders, valuesTest, withRowNumbersTest);
        System.out.println("tableWithoutHeadersWithoutRowNumbers");
        System.out.println(Arrays.toString(table.columnLength()));

        realString = table.printTable();
        System.out.println(realString);
        Assert.assertEquals(realString, expectedString);
    }

    @Test
    public void tableWithHeadersWithoutRowNumbers () {
        String realString;
        String expectedString =
                        "+----------+-----+---------------+\n" +
                        "| Name     | Age | Address       |\n" +
                        "+----------+-----+---------------+\n" +
                        "| Ann      | 24  | Ukraine       |\n" +
                        "| Alexandr | 7   | Great Britain |\n" +
                        "+----------+-----+---------------+";


        String[] headersTest = {"Name", "Age", "Address"};
        String[][] valuesTest = {{"Ann", "24", "Ukraine"},{"Alexandr", "7", "Great Britain"}};
        boolean withRowNumbersTest = false;

        TableImpl table = TableImpl.create(headersTest, valuesTest, withRowNumbersTest);
        System.out.println("tableWithHeadersWithoutRowNumbers");

        System.out.println(Arrays.toString(table.columnLength()));

        realString = table.printTable();
        System.out.println(realString);
        Assert.assertEquals(realString, expectedString);
    }
}


