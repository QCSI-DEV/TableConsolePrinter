package implClasses;

import interfaces.Table;
import org.testng.annotations.Optional;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;


public final class TableImpl extends Table {
    private String[] headers;
    private String[][] values;
    private boolean withRowNumbers;
    private boolean withHeaders = true;
    String[] divider;
    int[] colLength;
    int numOfCoumns;


    public static TableImpl create(String[] headers, String[][] values, boolean withRowNumbers) {
        return new TableImpl(headers, values, withRowNumbers);
    }

    public static TableImpl create(boolean withHeaders, String[][] values, boolean withRowNumbers) {
        return new TableImpl(withHeaders, values, withRowNumbers);
    }

    private TableImpl(String[] headers, String[][] values, boolean withRowNumbers) {
        this.headers = requireNonNull(headers);
        this.values = requireNonNull(values);
        this.withRowNumbers = withRowNumbers;
        getNumberOfColumns();
    }

    public TableImpl(boolean withHeaders, String[][] values, boolean withRowNumbers) {
        this.values = requireNonNull(values);
        this.withRowNumbers = withRowNumbers;
        this.withHeaders = withHeaders;
        getNumberOfColumns();
    }

    @Override
    public boolean isHeadersColMatchValueCol() {
        int columns = numOfCoumns;
        for (String[] inner : values) {
            if (columns != inner.length) {
                System.out.println("Value column is absent");
                return false;
            }
        }
        return true;
    }

    public int[] columnLength() {
        colLength = new int[numOfCoumns];
        if (withHeaders) {
            for (int i = 0; i < numOfCoumns; i++) {
                if (colLength[i] < headers[i].length()) {
                    colLength[i] = headers[i].length();
                }
            }
        }
        for (int j = 0; j < numOfCoumns; j++) {
            for (String[] value : values) {
                if (colLength[j] < value[j].length()) {
                    colLength[j] = value[j].length();
                }
            }
        }
        return colLength;
    }

    public void getNumberOfColumns() {
        if (withHeaders)
        numOfCoumns = headers.length;
        else
        for (String[] inner : values) {
            numOfCoumns =inner.length;
        }

    }

    @Override
    public String printTable() {
        if (withHeaders)makeStringLength(headers);
        makeStringLength(values);

        makeDivider();
        makeDividerLength(divider);
        String dividerString = makeStringfromDivider();

        StringBuilder sb = new StringBuilder();

        sb.append(dividerString).append("+").append("\n");
        if (withHeaders) {
            for (String header : headers) {
                sb.append("|").append(header);
            }
            sb.append("|").append("\n");
            sb.append(dividerString).append("+").append("\n");
        }
        for (String[] value : values) {
            for (int k = 0; k < numOfCoumns; k++) {
                sb.append("|").append(value[k]);
            }
            sb.append("|").append("\n");
        }
        sb.append(dividerString).append("+");
        return sb.toString();
    }

    @Override
    public void makeStringLength(String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            StringBuilder sbLen = new StringBuilder(" ");
            int dif = colLength[i] - headers[i].length();
            if (dif > 0) {
                sbLen.append(headers[i]);
                for (int d = 0; d < dif + 1; d++) {
                    sbLen.append(" ");
                }
            } else sbLen.append(headers[i]).append(" ");
            headers[i] = sbLen.toString();
        }
    }

    public void makeDividerLength(String[] divider) {
        for (int i = 0; i < divider.length; i++) {
            StringBuilder sbLen = new StringBuilder();

            int dif = colLength[i] - divider[i].length();
            if (dif >= 0) {
                if (withRowNumbers) {
                    String rowNumdersLength = String.valueOf(values.length);
                    sbLen.append("+");
                    for (int i1 = 0; i1 < rowNumdersLength.length(); i++) {
                        sbLen.append("-");
                    }
                }
                sbLen.append(divider[i]);
                for (int d = 0; d < (dif + 3); d++) {
                    sbLen.append("-");
                }
            } else sbLen.append(divider[i]).append("-");
            divider[i] = sbLen.toString();
        }
    }

    public void makeStringLength(String[][] values) {
        for (int rows = 0; rows < values.length; rows++) {
            for (int col = 0; col < numOfCoumns; col++) {
                StringBuilder sbLen = new StringBuilder(" ");
                int dif = colLength[col] - values[rows][col].length();
                if (dif > 0) {
                    sbLen.append(values[rows][col]);
                    for (int d = 0; d < dif + 1; d++) {
                        sbLen.append(" ");
                    }
                } else sbLen.append(values[rows][col]).append(" ");
                values[rows][col] = sbLen.toString();
            }
        }
    }

    private void makeDivider() {
        divider = new String[numOfCoumns];
        for (int i = 0; i < numOfCoumns; i++) {
            divider[i] = "+-";
        }
    }

    public String makeStringfromDivider() {
        StringBuilder sbdiv = new StringBuilder();
        for (int i = 0; i < divider.length; i++) {
            sbdiv.append(divider[i]);
        }
        return sbdiv.toString();
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    @Override
    public String[][] getValues() {
        return values;
    }

    @Override
    public void setValues(String[][] values) {
        this.values = values;
    }

    @Override
    public int[] getColLength() {
        return colLength;
    }

    @Override
    public void setColLength(int[] colLength) {
        this.colLength = colLength;
    }

    @Override
    public String toString() {
        return "TableImpl{" +
                "headers=" + Arrays.toString(headers) +
                ", values=" + Arrays.toString(values) +
                ", colLength=" + Arrays.toString(colLength) +
                '}';
    }


}
