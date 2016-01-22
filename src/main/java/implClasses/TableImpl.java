package implClasses;

import interfaces.Table;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;


public final class TableImpl extends Table {
    private String[] headers;
    private String[][] values;
    private boolean withRowNumbers;
    private boolean withHeaders = true;


    private int numOfColumns;
    private int[] numOfCharsInColumn;


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

    //TODO: use this verification
    @Override
    public boolean isHeadersColMatchValueCol() {
        int columns = numOfColumns;
        for (String[] inner : values) {
            if (columns != inner.length) {
                System.out.println("Value column is absent");
                return false;
            }
        }
        return true;
    }

    public int[] getColumnWidth() {
        int[] possibleNumOfChars = new int[numOfColumns];
        int column;
        if (withHeaders) {
            for (column = 0; column < numOfColumns; column++) {
                if (possibleNumOfChars[column] < headers[column].length()) {
                    possibleNumOfChars[column] = headers[column].length();
                }
            }
        }
        for (column = 0; column < numOfColumns; column++) {
            for (String[] value : values) {
                if (possibleNumOfChars[column] < value[column].length()) {
                    possibleNumOfChars[column] = value[column].length();
                }
            }
        }
        return possibleNumOfChars;
    }

    public void getNumberOfColumns() {
        if (withHeaders)
            numOfColumns = headers.length;
        else
            for (String[] inner : values) {
                numOfColumns = inner.length;
            }
    }

    @Override
    public String makeTableAsString() {
        StringBuilder tableAsString = new StringBuilder();
        Divider divider;
        String dividerString;

        numOfCharsInColumn = getColumnWidth();

        if (withHeaders) makeHeadersAsString(headers);
        makeValuesAsString(values);

        divider = Divider.create(numOfColumns, numOfCharsInColumn, values, withRowNumbers);
        dividerString = divider.makeFullStringFromDivider();

        addDividerString(dividerString, tableAsString);
        if (withHeaders) addHeader(dividerString, tableAsString);
        for (int rows = 0; rows < values.length; rows++) {
            String[] value = values[rows];
            //TODO: continue refactoring
            addRowNumbers(tableAsString, rows);
            addValueRows(value,tableAsString);
        }
        addDividerString(dividerString, tableAsString);

        return tableAsString.toString();
    }

    private void addDividerString(String dividerString, StringBuilder withEndingDivider) {
        withEndingDivider.append(dividerString).append("+").append("\r\n");
    }

    private void addValueRows( String[] value, StringBuilder sb) {
        for (int columnOfValue = 0; columnOfValue < numOfColumns; columnOfValue++) {
            sb.append("|").append(value[columnOfValue]);
        }
        sb.append("|").append("\r\n");
    }

    private void addRowNumbers(StringBuilder sb, int i) {
        if (withRowNumbers) {
            int rowNumbersLength = String.valueOf(values.length).length();
            sb.append("| ");
            sb.append(i + 1);
            int size = rowNumbersLength - String.valueOf(i + 1).length();
            if (String.valueOf(i).length() < rowNumbersLength) {
                for (int i1 = 0; i1 < size; i1++) {
                    sb.append(" ");
                }
            }
            sb.append(" ");
        }
    }

    private void addHeader(String dividerString, StringBuilder headersWithDivider) {
        if (withRowNumbers) {
            int rowNumbersColumnWidth = String.valueOf(values.length).length();
            headersWithDivider.append("| #");
            for (int charNum = 0; charNum < rowNumbersColumnWidth; charNum++) {
                headersWithDivider.append(" ");
            }
        }
        for (String header : headers) {
            headersWithDivider.append("|").append(header);
        }
        headersWithDivider.append("|").append("\r\n");
        addDividerString(dividerString, headersWithDivider);
    }

    @Override
    public void makeHeadersAsString(String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            StringBuilder fullCellWidth = new StringBuilder(" ");
            int dif = numOfCharsInColumn[i] - headers[i].length();
            if (dif > 0) {
                fullCellWidth.append(headers[i]);
                for (int d = 0; d < dif + 1; d++) {
                    fullCellWidth.append(" ");
                }
            } else fullCellWidth.append(headers[i]).append(" ");
            headers[i] = fullCellWidth.toString();
        }
    }


    public void makeValuesAsString(String[][] values) {
        for (int rows = 0; rows < values.length; rows++) {
            for (int col = 0; col < numOfColumns; col++) {
                StringBuilder fullCellWidth = new StringBuilder(" ");
                int difference = numOfCharsInColumn[col] - values[rows][col].length();
                if (difference > 0) {
                    fullCellWidth.append(values[rows][col]);
                    for (int d = 0; d < difference + 1; d++) {
                        fullCellWidth.append(" ");
                    }
                } else fullCellWidth.append(values[rows][col]).append(" ");
                values[rows][col] = fullCellWidth.toString();
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
    public int[] getNumOfCharsInColumn() {
        return numOfCharsInColumn;
    }

    @Override
    public void setNumOfCharsInColumn(int[] colLength) {
        this.numOfCharsInColumn = colLength;
    }

    @Override
    public String toString() {
        return "TableImpl{" +
                "headers=" + Arrays.toString(headers) +
                ", values=" + Arrays.toString(values) +
                ", colLength=" + Arrays.toString(numOfCharsInColumn) +
                '}';
    }


}
