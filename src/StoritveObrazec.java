import javax.swing.*;
import java.awt.*;

public class StoritveObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel imeLabel;
    private JTextField imeField;
    private JLabel opisLabel;
    private JTextArea opisArea;
    private JLabel zaposleniIdLabel;
    private JComboBox<String> zaposleniIdComboBox;
    private JLabel stanovalecIdLabel;
    private JComboBox<String> stanovalecIdComboBox;
    private JButton shraniButton;

    public StoritveObrazec() {
        window = new JFrame("Storitve Obrazec"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(null); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container

        mainTitle = new JLabel("Storitve Obrazec"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setBounds(10, 50, 1004, 50); // Nastavimo pozicijo in velikost
        container.add(mainTitle); // Dodamo label v container

        imeLabel = new JLabel("Ime storitve:"); // Ustvarimo nov label
        imeLabel.setBounds(10, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(imeLabel); // Dodamo label v container

        imeField = new JTextField(); // Ustvarimo nov textfield
        imeField.setBounds(220, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(imeField); // Dodamo textfield v container

        opisLabel = new JLabel("Opis storitve:"); // Ustvarimo nov label
        opisLabel.setBounds(10, 200, 200, 40); // Nastavimo pozicijo in velikost
        container.add(opisLabel); // Dodamo label v container

        opisArea = new JTextArea(); // Ustvarimo novo text area
        opisArea.setBounds(220, 200, 600, 200); // Nastavimo pozicijo in velikost
        container.add(opisArea); // Dodamo text area v container


        zaposleniIdLabel = new JLabel("Zaposleni:"); // Ustvarimo nov label
        zaposleniIdLabel.setBounds(10, 410, 200, 40); // Nastavimo pozicijo in velikost
        container.add(zaposleniIdLabel); // Dodamo label v container

        zaposleniIdComboBox = new JComboBox<String>(); // Ustvarimo nov izbirni seznam
        zaposleniIdComboBox.setBounds(220, 410, 200, 40); // Nastavimo pozicijo in velikost
        // Dodamo možnosti v izbirni seznam (primer)
        zaposleniIdComboBox.addItem("1");
        zaposleniIdComboBox.addItem("2");
        zaposleniIdComboBox.addItem("3");
        container.add(zaposleniIdComboBox); // Dodamo izbirni seznam v container

        stanovalecIdLabel = new JLabel("Stanovalec:"); // Ustvarimo nov label
        stanovalecIdLabel.setBounds(10, 460, 200, 40); // Nastavimo pozicijo in velikost
        container.add(stanovalecIdLabel); // Dodamo label v container

        stanovalecIdComboBox = new JComboBox<String>(); // Ustvarimo nov izbirni seznam
        stanovalecIdComboBox.setBounds(220, 460, 200, 40); // Nastavimo pozicijo in velikost
        // Dodamo možnosti v izbirni seznam (primer)
        stanovalecIdComboBox.addItem("101");
        stanovalecIdComboBox.addItem("202");
        stanovalecIdComboBox.addItem("303");
        container.add(stanovalecIdComboBox); // Dodamo izbirni seznam v container

        shraniButton = new JButton("Shrani"); // Ustvarimo nov gumb
        shraniButton.setBounds(10, 550, 100, 40); // Nastavimo pozicijo in velikost
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
