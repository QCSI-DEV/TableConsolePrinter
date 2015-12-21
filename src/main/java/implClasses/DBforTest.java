package implClasses;

import org.testng.annotations.Optional;

public class DBForTest {

    private String[] headers;
    private String[][] values;
    private int headersSize;
    private int valuesSize;

    public DBForTest(int  headersSize, int valuesSize) {
        this.headersSize = headersSize;
        this.valuesSize = valuesSize;
    }

    public String[] createHeaders() {
        headers = new String[headersSize];
        fillHeaders();
        return headers;
    }

    public String[][] createValues() {
        values = new String[valuesSize][headersSize];
        fillValues();
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
