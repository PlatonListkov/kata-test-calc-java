class ArabicToRomanConverter {
    private static final int[] ARABIC_VALUES = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_VALUES = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String arabicToRoman(short arabicNumeral) throws IllegalArgumentException {

        if (arabicNumeral < 1 || arabicNumeral > 3999)
            throw new IllegalArgumentException("Римляне не знали отрицательных чисел");

        StringBuilder result = new StringBuilder();
        short remainingValue = arabicNumeral;
        for (short i = 0; i < ARABIC_VALUES.length; i++) {
            while (remainingValue >= ARABIC_VALUES[i]) {
                result.append(ROMAN_VALUES[i]);
                remainingValue -= ARABIC_VALUES[i];
            }
        }
        return result.toString();
    }
}