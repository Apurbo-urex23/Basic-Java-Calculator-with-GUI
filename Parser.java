public class Parser {
    public double parse(String exp) {
        double currentNumber = 0;
        double result = 0;
        double lastValue = 0;
        char operation = '+';
        boolean hasNumber = false;
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
// Invalid character check
            if (!Character.isDigit(ch)
                    && ch != '+'
                    && ch != '-'
                    && ch != '*'
                    && ch != '/'
                    && ch != '%'
                    && ch != '.') {
                throw new IllegalArgumentException();
            }
// Build number
            if (Character.isDigit(ch) || ch == '.') {
                hasNumber = true;
                StringBuilder number = new StringBuilder();
                while (i < exp.length() &&
                        (Character.isDigit(exp.charAt(i))
                                || exp.charAt(i) == '.')) {
                    number.append(exp.charAt(i));
                    i++;
                }
                currentNumber =
                        Double.parseDouble(number.toString());
                i--;
            }
// Percentage
            if (ch == '%') {
                currentNumber = currentNumber / 100;
            }
// Operator OR last character
            if ((!Character.isDigit(ch) && ch != '.')
                    || i == exp.length() - 1) {
                if (!hasNumber) {
                    throw new IllegalArgumentException();
                }
                switch (operation) {
                    case '+':
                        result += lastValue;
                        lastValue = currentNumber;
                        break;
                    case '-':
                        result += lastValue;
                        lastValue = -currentNumber;
                        break;
                    case '*':
                        lastValue = lastValue * currentNumber;
                        break;
                    case '/':
                        if (currentNumber == 0) {
                            throw new ArithmeticException();
                        }
                        lastValue = lastValue / currentNumber;
                        break;
                }
                operation = ch;
                hasNumber = false;
            }
        }
        result += lastValue;
        return result;
    }
}
