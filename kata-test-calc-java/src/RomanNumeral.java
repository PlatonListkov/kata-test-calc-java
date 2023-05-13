
enum RomanNumeral {
    I("I", (short) 1), II("II", (short) 2), III("III", (short) 3), IV("IV", (short) 4), V("V", (short) 5),
    VI("VI", (short) 6), VII("VII", (short) 7), VIII("VIII", (short) 8), IX("IX", (short) 9), X("X", (short) 10);
    final private short value;
    final private String key;

    RomanNumeral(String key, short value) {
        this.key = key;
        this.value = value;
    }

    public short getArabic(String key) throws IllegalArgumentException {
        for (RomanNumeral numeral : RomanNumeral.values()) {
            if (numeral.key.equals(key)) {
                return numeral.value;
            }
        }
        throw new IllegalArgumentException("Invalid Roman numeral");
    }
}

