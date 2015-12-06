import implClasses.TableImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Наталия on 06.12.2015.
 */

public final class TablePrinterTests {


    public static String realString;
    public static String expectedString =
            "+--+----------+-----+---------------+\n" +
            "|№| Name     | Age | Address       |\n" +
            "+--+----------+-----+---------------+\n" +
            "|1 | Ann      | 24  | Ukraine       |\n" +
            "|2 | Peter    | 26  | USA           |\n" +
            "|3 | Vasya    | 102 | Georgia       |\n" +
            "|4 | Alexandr | 7   | Great Britain |\n" +
            "+--+----------+-----+---------------+";

    public static String []headersTest = {"Name", "Age", "Address"};
    public static String [][] valuesTest = {{"Ann", "24", "Ukraine"}, {"Peter", "26", "USA"},
            {"Vasya", "102", "Georgia"}, {"Alexandr", "7", "Great Britain"}};
    @Test
    public static void main(String[] args) {
        TableImpl table = TableImpl.create(headersTest, valuesTest);

        System.out.println(table.columnLength().toString());

        realString = table.toString();
        System.out.println(realString);

        table.printTable();

        Assert.assertEquals(realString, expectedString);

    }
}
