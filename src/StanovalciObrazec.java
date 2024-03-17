import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StanovalciObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel imeLabel;
    private JTextField imeField;
    private JLabel priimekLabel;
    private JTextField priimekField;
    private JLabel datumRojstvaLabel;
    private JTextField datumRojstvaField;
    private JLabel spolLabel;
    private JRadioButton moskiRadioButton;
    private JRadioButton zenskaRadioButton;
    private JLabel datumSprejemaLabel;
    private JTextField datumSprejemaField;
    private JLabel naslovLabel;
    private JTextField naslovField;
    private JLabel telefonLabel;
    private JTextField telefonField;
    private JLabel elNaslovLabel;
    private JTextField elNaslovField;
    private JLabel opombeLabel;
    private JTextArea opombeArea;
    private JLabel sobaIdLabel;
    private JComboBox<SobaItem> sobaIdComboBox;
    private JButton shraniButton;

    private int stanovalecId;
    private PostgreSQL db;

    public StanovalciObrazec(int stanovalecId) {
        this.stanovalecId = stanovalecId;

        try {
            db = new PostgreSQL();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

        window = new JFrame("Stanovalci Obrazec"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 900)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 900); // Nastavimo pozicijo in velikost okna
        window.setLayout(null); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container

        mainTitle = new JLabel("Stanovalci Obrazec"); // Ustvarimo nov label
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

        spolLabel = new JLabel("Spol:"); // Ustvarimo nov label
        spolLabel.setBounds(10, 300, 200, 40); // Nastavimo pozicijo in velikost
        container.add(spolLabel); // Dodamo label v container

        ButtonGroup spolButtonGroup = new ButtonGroup(); // Skupina gumbov za spol
        moskiRadioButton = new JRadioButton("Moški");
        moskiRadioButton.setBounds(220, 300, 100, 40);
        spolButtonGroup.add(moskiRadioButton);
        container.add(moskiRadioButton);

        zenskaRadioButton = new JRadioButton("Ženska");
        zenskaRadioButton.setBounds(320, 300, 100, 40);
        spolButtonGroup.add(zenskaRadioButton);
        container.add(zenskaRadioButton);

        datumSprejemaLabel = new JLabel("Datum sprejema:"); // Ustvarimo nov label
        datumSprejemaLabel.setBounds(10, 350, 200, 40); // Nastavimo pozicijo in velikost
        container.add(datumSprejemaLabel); // Dodamo label v container

        datumSprejemaField = new JTextField(); // Ustvarimo nov textfield
        datumSprejemaField.setBounds(220, 350, 200, 40); // Nastavimo pozicijo in velikost
        container.add(datumSprejemaField); // Dodamo textfield v container

        naslovLabel = new JLabel("Naslov:"); // Ustvarimo nov label
        naslovLabel.setBounds(10, 400, 200, 40); // Nastavimo pozicijo in velikost
        container.add(naslovLabel); // Dodamo label v container

        naslovField = new JTextField(); // Ustvarimo nov textfield
        naslovField.setBounds(220, 400, 200, 40); // Nastavimo pozicijo in velikost
        container.add(naslovField); // Dodamo textfield v container

        telefonLabel = new JLabel("Telefon:"); // Ustvarimo nov label
        telefonLabel.setBounds(10, 450, 200, 40); // Nastavimo pozicijo in velikost
        container.add(telefonLabel); // Dodamo label v container

        telefonField = new JTextField(); // Ustvarimo nov textfield
        telefonField.setBounds(220, 450, 200, 40); // Nastavimo pozicijo in velikost
        container.add(telefonField); // Dodamo textfield v container

        elNaslovLabel = new JLabel("E-pošta:"); // Ustvarimo nov label
        elNaslovLabel.setBounds(10, 500, 200, 40); // Nastavimo pozicijo in velikost
        container.add(elNaslovLabel); // Dodamo label v container

        elNaslovField = new JTextField(); // Ustvarimo nov textfield
        elNaslovField.setBounds(220, 500, 200, 40); // Nastavimo pozicijo in velikost
        container.add(elNaslovField); // Dodamo textfield v container

        opombeLabel = new JLabel("Opombe:"); // Ustvarimo nov label
        opombeLabel.setBounds(10, 550, 200, 40); // Nastavimo pozicijo in velikost
        container.add(opombeLabel); // Dodamo label v container

        opombeArea = new JTextArea(); // Ustvarimo novo text area
        opombeArea.setBounds(220, 550, 600, 150); // Nastavimo pozicijo in velikost
        container.add(opombeArea); // Dodamo text area v container

        sobaIdLabel = new JLabel("Številka sobe:"); // Ustvarimo nov label
        sobaIdLabel.setBounds(10, 700, 200, 40); // Nastavimo pozicijo in velikost
        container.add(sobaIdLabel); // Dodamo label v container

        sobaIdComboBox = new JComboBox<SobaItem>(); // Ustvarimo nov izbirni seznam
        sobaIdComboBox.setBounds(220, 700, 200, 40); // Nastavimo pozicijo in velikost
        // Dodamo možnosti v izbirni seznam (primer)
        try {
            String query = "SELECT * FROM sobe WHERE uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
            ResultSet resultSet = db.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String stevilka = resultSet.getString("stevilka_sobe");
                SobaItem item = new SobaItem(id, stevilka);
                sobaIdComboBox.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        container.add(sobaIdComboBox); // Dodamo izbirni seznam v container

        shraniButton = new JButton("Shrani"); // Ustvarimo nov gumb
        shraniButton.setBounds(10, 750, 100, 40); // Nastavimo pozicijo in velikost
        shraniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shraniStanovalca();
            }
        });
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno

        if (stanovalecId != 0) {
            try {
                String query = "SELECT * FROM stanovalci WHERE id = " + stanovalecId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                ResultSet resultSet = db.executeQuery(query);
                if (resultSet.next()) {
                    imeField.setText(resultSet.getString("ime"));
                    priimekField.setText(resultSet.getString("priimek"));
                    datumRojstvaField.setText(resultSet.getString("datum_rojstva"));
                    String spol = resultSet.getString("spol");
                    if (spol.equals("M")) {
                        moskiRadioButton.setSelected(true);
                    } else {
                        zenskaRadioButton.setSelected(true);
                    }
                    datumSprejemaField.setText(resultSet.getString("datum_sprejema"));
                    naslovField.setText(resultSet.getString("naslov"));
                    telefonField.setText(resultSet.getString("telefon"));
                    elNaslovField.setText(resultSet.getString("el_naslov"));
                    opombeArea.setText(resultSet.getString("opombe"));
                    int sobaId = resultSet.getInt("soba_id");
                    for (int i = 0; i < sobaIdComboBox.getItemCount(); i++) {
                        SobaItem item = sobaIdComboBox.getItemAt(i);
                        if (item.getId() == sobaId) {
                            sobaIdComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void shraniStanovalca() {
        String ime = imeField.getText();
        String priimek = priimekField.getText();
        String datumRojstva = datumRojstvaField.getText();
        String spol = moskiRadioButton.isSelected() ? "M" : "Ž";
        String datumSprejema = datumSprejemaField.getText();
        String naslov = naslovField.getText();
        String telefon = telefonField.getText();
        String elNaslov = elNaslovField.getText();
        String opombe = opombeArea.getText();
        int sobaId = sobaIdComboBox.getItemAt(sobaIdComboBox.getSelectedIndex()).getId();

        if (ime.isEmpty() || priimek.isEmpty() || datumRojstva.isEmpty() || datumSprejema.isEmpty() || naslov.isEmpty() || telefon.isEmpty() || elNaslov.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Prosimo, izpolnite vsa polja.", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (stanovalecId <= 0) {
                String query = "INSERT INTO stanovalci (ime, priimek, datum_rojstva, spol, datum_sprejema, naslov, telefon, el_naslov, opombe, soba_id, uporabnik_id) VALUES ('" + ime + "', '" + priimek + "', '" + datumRojstva + "', '" + spol + "', '" + datumSprejema + "', '" + naslov + "', '" + telefon + "', '" + elNaslov + "', '" + opombe + "', " + sobaId + ", " + StateFactory.getInstance().uporabnikId + ");";
                db.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Stanovalec uspešno dodan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String query = "UPDATE stanovalci SET ime = '" + ime + "', priimek = '" + priimek + "', datum_rojstva = '" + datumRojstva + "', spol = '" + spol + "', datum_sprejema = '" + datumSprejema + "', naslov = '" + naslov + "', telefon = '" + telefon + "', el_naslov = '" + elNaslov + "', opombe = '" + opombe + "', soba_id = " + sobaId + " WHERE id = " + stanovalecId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                db.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Stanovalec uspešno posodobljen.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
            db.close();
            window.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri shranjevanju stanovalca.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }

    private class SobaItem {
        private int id;
        private String stevilka;

        public SobaItem(int id, String stevilka) {
            this.id = id;
            this.stevilka = stevilka;
        }

        public int getId() {
            return id;
        }

        public String toString() {
            return stevilka;
        }
    }
}
