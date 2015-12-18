package implClasses;


public class Divider {

    int numOfCoumns;
    int[] colLength;
    private String[][] values;
    private boolean withRowNumbers;
    String[] divider;

    public static Divider create(int numOfCoumns, int[] colLength, String[][] values, boolean withRowNumbers) {
        return new Divider(numOfCoumns, colLength, values, withRowNumbers);
    }

    public Divider(int numOfCoumns, int[] colLength, String[][] values, boolean withRowNumbers) {
        this.numOfCoumns = numOfCoumns;
        this.colLength = colLength;
        this.values = values;
        this.withRowNumbers = withRowNumbers;
    }

    public String[] makeDivider() {
        divider = new String[numOfCoumns];
        for (int i = 0; i < numOfCoumns; i++) {
            divider[i] = "+-";
        }
        return divider;
    }

    public void makeDividerLength(String[] divider) {

        for (int div = 0; div < divider.length; div++) {
            StringBuilder sbLen = new StringBuilder();
            int dif = colLength[div] - divider[div].length();
            if (dif >= 0) {
                sbLen.append(divider[div]);
                for (int d = 0; d < (dif + 3); d++) {
                    sbLen.append("-");
                }
            } else sbLen.append(divider[div]).append("-");
            divider[div] = sbLen.toString();
        }
    }

    public StringBuilder makeNumPartForDivider() {
        StringBuilder numPart = new StringBuilder("+");
        String rowNumdersLength = String.valueOf(values.length);
        for (int i1 = 0; i1 < rowNumdersLength.length() + 2; i1++) {
            numPart.append("-");

        }
        return numPart;
    }

    public String makeFullStringfromDivider() {
        StringBuilder sbdiv = new StringBuilder();
        if (withRowNumbers) {
            sbdiv = makeNumPartForDivider();
        }
        return sbdiv.append(makeStringDivider()).toString();
    }

    public StringBuilder makeStringDivider() {
        StringBuilder div = new StringBuilder();
        for (String aDivider : divider) {

            div.append(aDivider);
        }
        return div;
    }
}
