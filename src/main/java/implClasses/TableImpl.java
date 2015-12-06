package implClasses;

import com.sun.xml.internal.bind.v2.TODO;
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
        String divider = "+-";
        StringBuffer sb = new StringBuffer();

        formatDivider(divider, sb);

        for (String header : headers) {
            sb.append("|").append(header);
        }
        sb.append("|").append("\n");

        formatDivider(divider, sb);
        for (String[] value : values) {
            for (int k = 0; k < headers.length; k++) {
                sb.append("|").append(value[k]);
            }
            sb.append("|").append("\n");
            formatDivider(divider, sb);
        }
        System.out.println(sb);
    }

    // TODO: Change divider cycle for formatter.
    private void formatDivider(String divider, StringBuffer sb) {
        for (String header1 : headers) {
            sb.append(divider);
        }
        sb.append("\n");
    }

    @Override
        public String[] getHeaders () {
            return headers;
        }

        @Override
        public void setHeaders (String[]headers){
            this.headers = headers;
        }

        @Override
        public String[][] getValues () {
            return values;
        }

        @Override
        public void setValues (String[][]values){
            this.values = values;
        }

        @Override
        public int[] getColLength () {
            return colLength;
        }

        @Override
        public void setColLength ( int[] colLength){
            this.colLength = colLength;
        }

        @Override
        public String toString () {
            return "TableImpl{" +
                    "headers=" + Arrays.toString(headers) +
                    ", values=" + Arrays.toString(values) +
                    ", colLength=" + Arrays.toString(colLength) +
                    '}';
        }
    }
