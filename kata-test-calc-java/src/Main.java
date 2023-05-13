import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(greetings);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toUpperCase();
        System.out.println("Математика говорит, что результат равен " + calc(userInput));
    }

    static String calc(String input) throws IllegalArgumentException {

        String[] items = parseString(input);

        Summand num1 = convertToNumber(items[0]);
        Summand num2 = convertToNumber(items[2]);

        if (num1.isArabic != num2.isArabic) throw new IllegalArgumentException("Серенький волчок укусил вас за бочок." +
                "А все из-за того, что вы пытались свести арабские и римские цифры. Ай-ай-ай!");

        checkValueRange(num1);
        checkValueRange(num2);

        char operation;
        if (items[1].length() == 1) operation = items[1].charAt(0);
        else throw new IllegalArgumentException("Не удалось распознать математический оператор.");

        short shortResult = performAMathOperation(num1, num2, operation);

        String result;
        if (num1.isArabic) result = String.valueOf(shortResult);
        else result = ArabicToRomanConverter.arabicToRoman(shortResult);
        return result;
    }

    static String[] parseString(String inputString) throws IllegalArgumentException {
        String[] parts = inputString.split("\\s+");
        if (parts.length != 3)
            throw new IllegalArgumentException("Строка должна содержать два числа и знак математической операции между ними.");
        return parts;
    }

    static Summand convertToNumber(String input) {
        try {
            short value = Short.parseShort(input);
            return new Summand(value, true);
        } catch (NumberFormatException e) {
            return romanToArabic(input);
        }
    }

    static void checkValueRange(Summand arg) throws IllegalArgumentException {
        if (arg.number < 0 || arg.number > 10)
            throw new IllegalArgumentException("Аргумент не входит в диапазон от 1 до 10.");
    }

    static Summand romanToArabic(String romanNumeral) throws IllegalArgumentException {
        try {
            RomanNumeral numeral = RomanNumeral.valueOf(romanNumeral.toUpperCase());
            short value = numeral.getArabic(romanNumeral);
            return new Summand(value, false);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Один из аргументов не является числом.");
        }
    }

    static short performAMathOperation(
            Summand arg1,
            Summand arg2,
            char operation
    ) throws IllegalArgumentException {
        short num1 = arg1.number, num2 = arg2.number;
        return switch (operation) {
            case '+' -> (short) (num1 + num2);
            case '-' -> (short) (num1 - num2);
            case '*' -> (short) (num1 * num2);
            case '/' -> (short) (num1 / num2);
            default -> throw new IllegalArgumentException("Можно использовать только +,-,*, или /.");
        };
    }

    static final String greetings = """
                            
            Введите два целых числа и знак математической операции между ними.
            Можно вводить числа от одного до десяти включительно, но не больше и не меньше.
            Используйте либо арабские, либо римские цифры.
            Возможны операции сложения, вычитания, умножения и деления.
            Для сложения используйте знак + ,
            Для вычитания используйте знак - ,
            Для умножения используйте знак * ,
            Для деления используйте знак / .
            Примеры ввода '1 + 1', V - IV, 2 * 5, XII / III
            Обращаю ваше внимание, что будет выполнено только одно математическое действие,
            к примеру действие вроде 1 + 1 - 81 выполнено не будет.
            Попытки совершения математических действий между римскими и арабскими цифрами
            приведут к тому, что из леса придёт серенький волчок и укусит вас за бочок, а оно вам надо?
            Попытка получить отрицательное римское число окончится ничем, ведь Римляне не знали отрицательных значений,
            да что там, у них даже с нулями так и не был достигнут консенсус, так что я бы не советовал.

            Расчет результата математической операции будет выведен на экран.
            Результат деления всегда будет целым числом, остаток от деления будет отброшен.
            Желаю вам счастливой математики!

            """;
}