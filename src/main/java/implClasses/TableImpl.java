package implClasses;

import interfaces.Table;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;


public final class TableImpl extends Table {
    private String[] headers;
    private String[][] values;
    String[] divider;
    int[] colLength;

    public static TableImpl create(String[] headers, String[][] values) {
        return new TableImpl(headers, values);
    }

    private TableImpl(String[] headers, String[][] values) {
        this.headers = requireNonNull(headers);
        this.values = requireNonNull(values);
    }

    @Override
    public boolean isHeadersColMatchValueCol() {
        int columns = headers.length;
        for (String[] inner : values) {
            if (columns != inner.length) {
                System.out.println("Value column is absent");
                return false;
            }
        }
        return true;
    }

    public int[] columnLength() {
        colLength = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            if (colLength[i] < headers[i].length()) {
                colLength[i] = headers[i].length();
            }
        }
        for (int j = 0; j < headers.length; j++) {
            for (String[] value : values) {
                if (colLength[j] < value[j].length()) {
                    colLength[j] = value[j].length();
                }
            }
        }
        return colLength;
    }

    @Override
    public void printTable() {
        makeStringLength(headers);
        makeStringLength(values);

        makeDivider();
        makeDividerLength(divider);
        String dividerString = makeStringfromDivider();

        StringBuffer sb = new StringBuffer();

        sb.append(dividerString).append("+").append("\n");
        for (String header : headers) {
            sb.append("|").append(header);
        }
        sb.append("|").append("\n");
        sb.append(dividerString).append("+").append("\n");

        for (String[] value : values) {
            for (int k = 0; k < headers.length; k++) {
                sb.append("|").append(value[k]);
            }
            sb.append("|").append("\n");
            sb.append(dividerString).append("+").append("\n");
        }
        System.out.println(sb);
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
            if (dif > 0) {
                sbLen.append(divider[i]);
                for (int d = 0; d < dif + 3; d++) {
                    sbLen.append("-");
                }
            } else sbLen.append(divider[i]).append("-");
            divider[i] = sbLen.toString();
        }
    }

    public void makeStringLength(String[][] values) {
        for (int rows = 0; rows < values.length; rows++) {
            for (int col = 0; col < headers.length; col++) {
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

    // TODO: Change divider cycle for formatter.
    private void makeDivider() {
        divider = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            divider[i] = "+-";
        }
    }
    public String makeStringfromDivider (){
        StringBuilder sbdiv = new StringBuilder();
        for (int i =0; i<divider.length; i++){
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
