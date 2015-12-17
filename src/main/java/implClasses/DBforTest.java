package implClasses;

import java.io.*;

/**
 * Created by ������� on 13.12.2015.
 */
public class DBforTest {
    private String[] headers;
    private String[][] values;
    private int headersSize;
    private int valuesSize;


    public DBforTest(int headersSize, int valuesSize) {
        this.headersSize = headersSize;
        this.valuesSize = valuesSize;
    }

    public String[] createHeaders() {
        headers = new String[headersSize];
        return headers;
    }

    public String[][] createValues() {
        values = new String[valuesSize][headersSize];
        return values;
    }

    public String[] fillHeaders() {
        for (int i = 0; i < headersSize; i++) {
            headers[i] = "column " + i;
        }
        return headers;
    }

    public String[][] fillValues() {
        for (int v = 0; v < valuesSize; v++) {
            for (int c = 0; c < headersSize; c++) {
                values[v][c] = "value " + v + c;
            }
        }
        return values;
    }

}
