package interfaces;

/**
 * Created by Наталия on 06.12.2015.
 */
public class Table {
    private String[] headers;
    private String[][] values;
    private int colLength [];

    public boolean isHeadersColMatchValueCol (){
        return false;
    }

    public int[] columnLength() {
                return null;
    }

    public void printTable(){

    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[][] getValues() {
        return values;
    }

    public void setValues(String[][] values) {
        this.values = values;
    }

    public int[] getColLength() {
        return colLength;
    }

    public void setColLength(int[] colLength) {
        this.colLength = colLength;
    }
}
