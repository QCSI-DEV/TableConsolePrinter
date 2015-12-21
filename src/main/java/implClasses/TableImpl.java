package implClasses;

import interfaces.Table;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;


public final class TableImpl extends Table {
    private String[] headers;
    private String[][] values;
    private boolean withRowNumbers;
    private boolean withHeaders = true;

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
                numOfCoumns = inner.length;
            }
    }

    @Override
    public String printTable() {
        StringBuilder sb = new StringBuilder();
        Divider divider;
        String dividerString;

        colLength = columnLength();

        if (withHeaders) makeStringLength(headers);
        makeStringLength(values);

        divider = Divider.create(numOfCoumns, colLength, values, withRowNumbers);
        divider.makeDividerLength(divider.makeDivider());
        dividerString = divider.makeFullStringfromDivider();

        addDividerString(dividerString, sb);
        addHeader(dividerString, sb);
        for (int i = 0; i < values.length; i++) {
            String[] value = values[i];
            addRowNumbers(sb, i);
            addValueRows(sb, value);
        }
        addDividerString(dividerString, sb);

        return sb.toString();
    }

    private void addDividerString(String dividerString, StringBuilder sb) {
        sb.append(dividerString).append("+").append("\n");
    }

    private void addValueRows(StringBuilder sb, String[] value) {
        for (int valcol = 0; valcol < numOfCoumns; valcol++) {
            sb.append("|").append(value[valcol]);
        }
        sb.append("|").append("\n");
    }

    private void addRowNumbers(StringBuilder sb, int i) {
        if (withRowNumbers) {
            int rowNumdersLength = String.valueOf(values.length).length();
            sb.append("| ");
            sb.append(i + 1);
            int size = rowNumdersLength - String.valueOf(i + 1).length();
            if (String.valueOf(i).length() < rowNumdersLength) {
                for (int i1 = 0; i1 < size; i1++) {
                    sb.append(" ");
                }
            }
            sb.append(" ");
        }
    }

    private void addHeader(String dividerString, StringBuilder sb) {
        if (withHeaders) {
            if (withRowNumbers) {
                String rowNumbersLength = String.valueOf(values.length);
                sb.append("| #");
                for (int i1 = 0; i1 < rowNumbersLength.length(); i1++) {
                    sb.append(" ");
                }
            }
            for (String header : headers) {
                sb.append("|").append(header);
            }
            sb.append("|").append("\n");
            addDividerString(dividerString, sb);
        }
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
