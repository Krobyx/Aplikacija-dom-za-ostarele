import javax.swing.*;

public class Test {
    private JFrame window;
    private JPanel MainPanel;
    private JLabel mainLabel;

    public Test() {
        window = new JFrame("Test");
        window.setContentPane(MainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
    }

    public void show() {
        window.setVisible(true);
    }
}
