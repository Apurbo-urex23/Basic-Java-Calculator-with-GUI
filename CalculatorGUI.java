import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField display;

    private final String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "%", "+",
            "C", "="
    };

    private Calculator calculator;

    public CalculatorGUI() {

        calculator = new Calculator();

        setTitle("Basic Calculator");
        setSize(380, 550);
        ImageIcon icon = new ImageIcon("D:\\JAVA SOFTWARE\\CALCULATOR\\src\\calculator.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Dark background
        getContentPane().setBackground(new Color(24, 24, 24));

        // Display
        display = new JTextField();

        display.setFont(new Font("Arial", Font.BOLD, 32));

        display.setHorizontalAlignment(JTextField.RIGHT);

        display.setEditable(false);

        display.setBackground(new Color(35, 35, 35));

        display.setForeground(Color.WHITE);

        display.setCaretColor(Color.WHITE);

        display.setBorder(BorderFactory.createEmptyBorder(
                20, 15, 20, 15
        ));

        display.setPreferredSize(new Dimension(0, 90));

        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.setBackground(new Color(24, 24, 24));

        for (String text : buttons) {

            JButton button = new JButton(text);

            button.setFont(new Font("Arial", Font.BOLD, 24));

            button.setFocusPainted(false);

            button.setBorderPainted(false);

            button.setForeground(Color.WHITE);

            // Operator colors
            if (text.equals("+")
                    || text.equals("-")
                    || text.equals("*")
                    || text.equals("/")
                    || text.equals("=")
                    || text.equals("%")) {

                button.setBackground(new Color(255, 170, 0));

            } else if (text.equals("C")) {

                button.setBackground(new Color(220, 70, 70));

            } else {

                button.setBackground(new Color(50, 50, 50));
            }

            button.addActionListener(this);

            panel.add(button);
        }

        panel.setBorder(BorderFactory.createEmptyBorder(
                10, 10, 10, 10
        ));

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command =
                ((JButton) e.getSource()).getText();

        switch (command) {

            case "C":

                display.setText("");

                break;

            case "=":

                try {

                    String expression = display.getText();

                    double result =
                            calculator.evaluate(expression);

                    display.setText(String.valueOf(result));

                } catch (Exception ex) {

                    display.setText("Error");
                }

                break;

            default:

                display.setText(
                        display.getText() + command
                );
        }
    }
}