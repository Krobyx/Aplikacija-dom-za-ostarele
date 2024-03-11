import javax.swing.*;
import java.awt.*;

public class SobeObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel sobaStevilkaLabel;
    private JTextField sobaStevilkaField;
    private JLabel tipSobeLabel;
    private JTextField tipSobeField;
    private JLabel opombeLabel;
    private JTextArea opombeArea;
    private JButton shraniButton;

    public SobeObrazec() {
        window = new JFrame("SobeObrazec"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(new BorderLayout()); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container
        container.setLayout(null); // Nastavimo postavitev panela

        mainTitle = new JLabel("Sobe Obrazec"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Nastavimo poravnavo
        mainTitle.setBounds(10, 50, 1004, 50); // Nastavimo pozicijo in velikost
        container.add(mainTitle); // Dodamo label v panel

        sobaStevilkaLabel = new JLabel("Številka sobe:"); // Ustvarimo nov label
        sobaStevilkaLabel.setBounds(10, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(sobaStevilkaLabel); // Dodamo label v panel

        sobaStevilkaField = new JTextField(); // Ustvarimo nov textfield
        sobaStevilkaField.setBounds(220, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(sobaStevilkaField); // Dodamo textfield v panel

        tipSobeLabel = new JLabel("Tip sobe:"); // Ustvarimo nov label
        tipSobeLabel.setBounds(10, 200, 200, 40); // Nastavimo pozicijo in velikost
        container.add(tipSobeLabel); // Dodamo label v panel

        tipSobeField = new JTextField(); // Ustvarimo nov textfield
        tipSobeField.setBounds(220, 200, 200, 40); // Nastavimo pozicijo in velikost
        container.add(tipSobeField); // Dodamo textfield v panel

        opombeLabel = new JLabel("Opombe:"); // Ustvarimo nov label
        opombeLabel.setBounds(10, 250, 200, 40); // Nastavimo pozicijo in velikost
        container.add(opombeLabel); // Dodamo label v panel

        opombeArea = new JTextArea(); // Ustvarimo novo text area
        opombeArea.setBounds(220, 250, 600, 200); // Nastavimo pozicijo in velikost
        container.add(opombeArea); // Dodamo text area v panel

        shraniButton = new JButton("Shrani"); // Ustvarimo nov gumb
        shraniButton.setBounds(10, 500, 100, 40); // Nastavimo pozicijo in velikost
        container.add(shraniButton); // Dodamo gumb v panel
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
