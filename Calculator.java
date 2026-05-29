public class Calculator {
    private Parser parser;
    public Calculator() {
        parser = new Parser();
    }
    public double evaluate(String exp) {
        exp = exp.replaceAll(" ", "");
        return parser.parse(exp);
    }
}