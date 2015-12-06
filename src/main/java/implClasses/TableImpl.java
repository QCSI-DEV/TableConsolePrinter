package implClasses;

import interfaces.Table;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * Created by Наталия on 06.12.2015.
 */

public final class TableImpl extends Table {
    private String[] headers;
    private String[][] values;
    int[] colLength;

    public static TableImpl create(String[] headers, String[][] values){
        return new TableImpl(headers, values);
    }

    private TableImpl(String[] headers, String[][] values) {
        this.headers = requireNonNull(headers);
        this.values = requireNonNull(values);
    }

    @Override
    public boolean isHeadersColMatchValueCol() {
        int columns =headers.length;
        for (String[]inner:values){
            if (columns!=inner.length){
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
                colLength [i] = headers[i].length();
            }
        }
        for (int j = 0; j<headers.length; j++){
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
        String divider ="+-";
        
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
