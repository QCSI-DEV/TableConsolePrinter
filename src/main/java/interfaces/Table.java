package interfaces;


public abstract class Table {
    private String[] headers;
    private String[][] values;
    private int numOfCharsInColumn[];

    public boolean isHeadersColMatchValueCol() {
        return false;
    }



    public String makeTableAsString() {

        return null;
    }

    public void makeHeadersAsString(String[] headers) {
    }

    public void makeHeadersAsString(String[][] values) {
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

    public int[] getNumberOfCharsInColumn() {
        return numOfCharsInColumn;
    }

    public void setNumberOfCharsInColumn(int[] colLength) {
        this.numOfCharsInColumn = colLength;
    }

    public abstract int[] getNumOfCharsInColumn();

    public abstract void setNumOfCharsInColumn(int[] numOfCharsInColumn);
}
