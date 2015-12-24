package implClasses;


public class Divider {

    private final int numOfColumns;
    private final int[] colLength;
    private final String[][] values;
    private final boolean withRowNumbers;

    public static Divider create(int numOfColumns, int[] colLength, String[][] values, boolean withRowNumbers) {
        return new Divider(numOfColumns, colLength, values, withRowNumbers);
    }

    public Divider(int numOfColumns, int[] colLength, String[][] values, boolean withRowNumbers) {
        this.numOfColumns = numOfColumns;
        this.colLength = colLength;
        this.values = values;
        this.withRowNumbers = withRowNumbers;
    }

    public String[] makeDivider() {
        String[] divider = new String[numOfColumns];
        for (int i = 0; i < numOfColumns; i++) {
            divider[i] = "+-";
        }
        makeDividerLength(divider);
        return divider;
    }

    public String[] makeDividerLength(String[] divider) {

        for (int div = 0; div < divider.length; div++) {
            StringBuilder dividerLength = new StringBuilder();
            int dif = colLength[div] - divider[div].length();
            if (dif >= 0) {
                dividerLength.append(divider[div]);
                for (int d = 0; d < (dif + 3); d++) {
                    dividerLength.append("-");
                }
            } else dividerLength.append(divider[div]).append("-");
            divider[div] = dividerLength.toString();
        }
        return divider;
    }

    public StringBuilder makeNumPartForDivider() {
        StringBuilder numPart = new StringBuilder("+");
        String rowNumbersLength = String.valueOf(values.length);
        for (int i1 = 0; i1 < rowNumbersLength.length() + 2; i1++) {
            numPart.append("-");
        }
        return numPart;
    }

    public String makeFullStringFromDivider() {
        StringBuilder fullDivider = new StringBuilder();
        if (withRowNumbers) {
            fullDivider = makeNumPartForDivider();
        }
        return fullDivider.append(makeStringDivider()).toString();
    }

    public StringBuilder makeStringDivider() {
        StringBuilder result = new StringBuilder();
        for (String divider : makeDivider()) {
            result.append(divider);
        }
        return result;
    }
}
