package implClasses;

/**
 * Created by n.chernonog on 12/10/2015.
 */
public class DBforTests {
    private String [] headers;
    private String [][] values;
    int sizeHeaders = 10;
    int sizeValue = 100000;

    public DBforTests() {
        this.headers = new String[sizeHeaders];
        this.values = new String[sizeValue][sizeHeaders];
    }

    public String [] createHeaders (){
        for (int columnHeaders = 0; columnHeaders<sizeHeaders; columnHeaders++){
            headers[columnHeaders]= "column "+String.valueOf(columnHeaders);
        }
        return headers;
    }

    public String [][] createValues (){
        for (int rowsVal=0; rowsVal<sizeValue; rowsVal++ ){
            for (int columnVal=0; columnVal<sizeHeaders; columnVal++){
                values [rowsVal][columnVal]="Value "+String.valueOf(rowsVal)+String.valueOf(columnVal);
            }
        }
        return values;
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
}
