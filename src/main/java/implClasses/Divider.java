package implClasses;


public class Divider {

    private final int numOfColumns;
    private final int[] numberOfCharsInColumn;
    private final String[][] values;
    private final boolean withRowNumbers;
    private String[] dividerBase;

    public static Divider create(int numOfColumns, int[] numberOfCharsInColumn, String[][] values, boolean withRowNumbers) {
        return new Divider(numOfColumns, numberOfCharsInColumn, values, withRowNumbers);
    }

    public Divider(int numOfColumns, int[] numberOfCharsInColumn, String[][] values, boolean withRowNumbers) {
        this.numOfColumns = numOfColumns;
        this.numberOfCharsInColumn = numberOfCharsInColumn;
        this.values = values;
        this.withRowNumbers = withRowNumbers;
    }

    public String[] makeDivider() {
        dividerBase = new String[numOfColumns];
        for (int i = 0; i < numOfColumns; i++) {
            dividerBase[i] = "+-";
        }
        makeFullDivider();
        return dividerBase;
    }

    public void makeFullDivider() {

        for (int div = 0; div < dividerBase.length; div++) {
            StringBuilder fullDivider = new StringBuilder();
            int difference = numberOfCharsInColumn[div] - dividerBase[div].length();
            if (difference >= 0) {
                fullDivider.append(dividerBase[div]);
                for (int numOfDashes = 0; numOfDashes < (difference + 3); numOfDashes++) {
                    fullDivider.append("-");
                }
            } else fullDivider.append(dividerBase[div]).append("-");
            dividerBase[div] = fullDivider.toString();
        }

    }

    public StringBuilder makeNumPartForDivider() {
        StringBuilder numPartDivider = new StringBuilder("+");
        int rowNumbersLength = String.valueOf(values.length).length();
        for (int numOfDashes = 0; numOfDashes < rowNumbersLength + 2; numOfDashes++) {
            numPartDivider.append("-");
        }
        return numPartDivider;
    }

    public String makeFullStringFromDivider() {
        StringBuilder fullDividerString = new StringBuilder();
        if (withRowNumbers) {
            fullDividerString = makeNumPartForDivider();
        }
        return fullDividerString.append(makeStringDivider()).toString();
    }

    public StringBuilder makeStringDivider() {
        StringBuilder result = new StringBuilder();
        for (String divider : makeDivider()) {
            result.append(divider);
        }
        return result;
    }
}
