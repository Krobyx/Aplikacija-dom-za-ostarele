import javax.swing.*;
import java.awt.*;

public class VlogeObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel imeLabel;
    private JTextField imeField;
    private JLabel opisLabel;
    private JTextArea opisArea;
    private JButton shraniButton;

    public VlogeObrazec() {
        window = new JFrame("Vloge Obrazec"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(null); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container

        mainTitle = new JLabel("Vloge Obrazec"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setBounds(10, 50, 1004, 50); // Nastavimo pozicijo in velikost
        container.add(mainTitle); // Dodamo label v container

        imeLabel = new JLabel("Ime vloge:"); // Ustvarimo nov label
        imeLabel.setBounds(10, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(imeLabel); // Dodamo label v container

        imeField = new JTextField(); // Ustvarimo nov textfield
        imeField.setBounds(220, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(imeField); // Dodamo textfield v container

        opisLabel = new JLabel("Opis vloge:"); // Ustvarimo nov label
        opisLabel.setBounds(10, 200, 200, 40); // Nastavimo pozicijo in velikost
        container.add(opisLabel); // Dodamo label v container

        opisArea = new JTextArea(); // Ustvarimo novo text area
        opisArea.setBounds(220, 200, 600, 200); // Nastavimo pozicijo in velikost
        container.add(opisArea); // Dodamo text area v container

        shraniButton = new JButton("Shrani"); // Ustvarimo nov gumb
        shraniButton.setBounds(10, 450, 100, 40); // Nastavimo pozicijo in velikost
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
