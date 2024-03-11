import javax.swing.*;
import java.awt.*;

public class ZaposleniObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel imeLabel;
    private JTextField imeField;
    private JLabel priimekLabel;
    private JTextField priimekField;
    private JLabel datumRojstvaLabel;
    private JTextField datumRojstvaField;
    private JLabel vlogaLabel;
    private JComboBox<String> vlogaComboBox;
    private JLabel naslovLabel;
    private JTextField naslovField;
    private JLabel telefonLabel;
    private JTextField telefonField;
    private JLabel elNaslovLabel;
    private JTextField elNaslovField;
    private JLabel opombeLabel;
    private JTextArea opombeArea;
    private JButton shraniButton;

    public ZaposleniObrazec() {
        window = new JFrame("Zaposleni Obrazec"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(null); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container

        mainTitle = new JLabel("Zaposleni Obrazec"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setBounds(10, 50, 1004, 50); // Nastavimo pozicijo in velikost
        container.add(mainTitle); // Dodamo label v container

        imeLabel = new JLabel("Ime:"); // Ustvarimo nov label
        imeLabel.setBounds(10, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(imeLabel); // Dodamo label v container

        imeField = new JTextField(); // Ustvarimo nov textfield
        imeField.setBounds(220, 150, 200, 40); // Nastavimo pozicijo in velikost
        container.add(imeField); // Dodamo textfield v container

        priimekLabel = new JLabel("Priimek:"); // Ustvarimo nov label
        priimekLabel.setBounds(10, 200, 200, 40); // Nastavimo pozicijo in velikost
        container.add(priimekLabel); // Dodamo label v container

        priimekField = new JTextField(); // Ustvarimo nov textfield
        priimekField.setBounds(220, 200, 200, 40); // Nastavimo pozicijo in velikost
        container.add(priimekField); // Dodamo textfield v container

        datumRojstvaLabel = new JLabel("Datum rojstva:"); // Ustvarimo nov label
        datumRojstvaLabel.setBounds(10, 250, 200, 40); // Nastavimo pozicijo in velikost
        container.add(datumRojstvaLabel); // Dodamo label v container

        datumRojstvaField = new JTextField(); // Ustvarimo nov textfield
        datumRojstvaField.setBounds(220, 250, 200, 40); // Nastavimo pozicijo in velikost
        container.add(datumRojstvaField); // Dodamo textfield v container

        vlogaLabel = new JLabel("Vloga:"); // Ustvarimo nov label
        vlogaLabel.setBounds(10, 300, 200, 40); // Nastavimo pozicijo in velikost
        container.add(vlogaLabel); // Dodamo label v container

        vlogaComboBox = new JComboBox<String>(); // Ustvarimo nov izbirni seznam
        vlogaComboBox.setBounds(220, 300, 200, 40); // Nastavimo pozicijo in velikost
        // Dodamo možnosti v izbirni seznam (primer)
        vlogaComboBox.addItem("Administrator");
        vlogaComboBox.addItem("Skrbnik");
        vlogaComboBox.addItem("Recepcionist");
        container.add(vlogaComboBox); // Dodamo izbirni seznam v container

        naslovLabel = new JLabel("Naslov:"); // Ustvarimo nov label
        naslovLabel.setBounds(10, 350, 200, 40); // Nastavimo pozicijo in velikost
        container.add(naslovLabel); // Dodamo label v container

        naslovField = new JTextField(); // Ustvarimo nov textfield
        naslovField.setBounds(220, 350, 200, 40); // Nastavimo pozicijo in velikost
        container.add(naslovField); // Dodamo textfield v container

        telefonLabel = new JLabel("Telefon:"); // Ustvarimo nov label
        telefonLabel.setBounds(10, 400, 200, 40); // Nastavimo pozicijo in velikost
        container.add(telefonLabel); // Dodamo label v container

        telefonField = new JTextField(); // Ustvarimo nov textfield
        telefonField.setBounds(220, 400, 200, 40); // Nastavimo pozicijo in velikost
        container.add(telefonField); // Dodamo textfield v container

        elNaslovLabel = new JLabel("E-pošta:"); // Ustvarimo nov label
        elNaslovLabel.setBounds(10, 450, 200, 40); // Nastavimo pozicijo in velikost
        container.add(elNaslovLabel); // Dodamo label v container

        elNaslovField = new JTextField(); // Ustvarimo nov textfield
        elNaslovField.setBounds(220, 450, 200, 40); // Nastavimo pozicijo in velikost
        container.add(elNaslovField); // Dodamo textfield v container

        opombeLabel = new JLabel("Opombe:"); // Ustvarimo nov label
        opombeLabel.setBounds(10, 500, 200, 40); // Nastavimo pozicijo in velikost
        container.add(opombeLabel); // Dodamo label v container

        opombeArea = new JTextArea(); // Ustvarimo novo text area
        opombeArea.setBounds(220, 500, 600, 150); // Nastavimo pozicijo in velikost
        container.add(opombeArea); // Dodamo text area v container

        shraniButton = new JButton("Shrani"); // Ustvarimo nov gumb
        shraniButton.setBounds(10, 680, 100, 40); // Nastavimo pozicijo in velikost
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
