import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoritveObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel imeLabel;
    private JTextField imeField;
    private JLabel opisLabel;
    private JTextArea opisArea;
    private JLabel zaposleniIdLabel;
    private JComboBox<ZaposleniItem> zaposleniIdComboBox;
    private JLabel stanovalecIdLabel;
    private JComboBox<StanovalecItem> stanovalecIdComboBox;
    private JLabel cenaLabel;
    private JTextField cenaField;
    private JButton shraniButton;
    private int storitevId;
    private PostgreSQL db;

    public StoritveObrazec(int storitevId) {
        this.storitevId = storitevId;

        try {
            db = new PostgreSQL();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

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

        zaposleniIdComboBox = new JComboBox<ZaposleniItem>(); // Ustvarimo nov izbirni seznam
        zaposleniIdComboBox.setBounds(220, 410, 200, 40); // Nastavimo pozicijo in velikost
        try {
            String query = "SELECT * FROM zaposleni WHERE uporabnik_id = " + StateFactory.getInstance().uporabnikId;
            ResultSet rs = db.executeQuery(query); // Izvedemo poizvedbo
            while (rs.next()) { // Gremo čez vse vrstice
                // Dodamo vrstico v model
                zaposleniIdComboBox.addItem(new ZaposleniItem(rs.getInt("id"), rs.getString("ime"), rs.getString("priimek")));
            }
            zaposleniIdComboBox.addItem(new ZaposleniItem(0, "Brez", "zaposlenega"));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri branju podatkov iz podatkovne baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
        container.add(zaposleniIdComboBox); // Dodamo izbirni seznam v container

        stanovalecIdLabel = new JLabel("Stanovalec:"); // Ustvarimo nov label
        stanovalecIdLabel.setBounds(10, 460, 200, 40); // Nastavimo pozicijo in velikost
        container.add(stanovalecIdLabel); // Dodamo label v container

        stanovalecIdComboBox = new JComboBox<StanovalecItem>(); // Ustvarimo nov izbirni seznam
        stanovalecIdComboBox.setBounds(220, 460, 200, 40); // Nastavimo pozicijo in velikost
        try {
            String query = "SELECT * FROM stanovalci WHERE uporabnik_id = " + StateFactory.getInstance().uporabnikId;
            ResultSet rs = db.executeQuery(query); // Izvedemo poizvedbo
            while (rs.next()) { // Gremo čez vse vrstice
                // Dodamo vrstico v model
                stanovalecIdComboBox.addItem(new StanovalecItem(rs.getInt("id"), rs.getString("ime"), rs.getString("priimek")));
            }
            stanovalecIdComboBox.addItem(new StanovalecItem(0, "Brez", "stanovalca"));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri branju podatkov iz podatkovne baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
        container.add(stanovalecIdComboBox); // Dodamo izbirni seznam v container

        cenaLabel = new JLabel("Cena storitve:"); // Ustvarimo nov label
        cenaLabel.setBounds(10, 510, 200, 40); // Nastavimo pozicijo in velikost
        container.add(cenaLabel); // Dodamo label v container

        cenaField = new JTextField(); // Ustvarimo nov textfield
        cenaField.setBounds(220, 510, 200, 40); // Nastavimo pozicijo in velikost
        container.add(cenaField); // Dodamo textfield v container

        shraniButton = new JButton("Shrani"); // Ustvarimo nov gumb
        shraniButton.setBounds(10, 560, 200, 40); // Nastavimo pozicijo in velikost
        shraniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                shraniStoritev();
            }
        });
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno

        if (storitevId > 0) {
            try {
                String query = "SELECT * FROM storitve WHERE id = " + storitevId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId;
                ResultSet rs = db.executeQuery(query); // Izvedemo poizvedbo
                if (rs.next()) {
                    imeField.setText(rs.getString("ime"));
                    opisArea.setText(rs.getString("opis"));
                    for (int i = 0; i < zaposleniIdComboBox.getItemCount(); i++) {
                        if (zaposleniIdComboBox.getItemAt(i).id == rs.getInt("zaposleni_id")) {
                            zaposleniIdComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                    for (int i = 0; i < stanovalecIdComboBox.getItemCount(); i++) {
                        if (stanovalecIdComboBox.getItemAt(i).id == rs.getInt("stanovalec_id")) {
                            stanovalecIdComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                    cenaField.setText(rs.getString("cena"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Napaka pri branju podatkov iz podatkovne baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void shraniStoritev() {
        String ime = imeField.getText();
        String opis = opisArea.getText();
        String zaposleniId = "NULL";
        String stanovalecId = "NULL";

        if (zaposleniIdComboBox.getSelectedIndex() != -1) {
            zaposleniId = zaposleniIdComboBox.getItemAt(zaposleniIdComboBox.getSelectedIndex()).id + "";

            if (zaposleniId.equals("0")) {
                zaposleniId = "NULL";
            }
        }

        if (stanovalecIdComboBox.getSelectedIndex() != -1) {
            stanovalecId = stanovalecIdComboBox.getItemAt(stanovalecIdComboBox.getSelectedIndex()).id + "";

            if (stanovalecId.equals("0")) {
                stanovalecId = "NULL";
            }
        }

        String cena = cenaField.getText();

        if (ime.length() == 0) {
            JOptionPane.showMessageDialog(null, "Prosimo, vnesite ime storitve.", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cena.length() == 0) {
            JOptionPane.showMessageDialog(null, "Prosimo, vnesite ceno storitve.", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (storitevId > 0) {
                String query = "UPDATE storitve SET ime = '" + ime + "', opis = '" + opis + "', zaposleni_id = " + zaposleniId + ", stanovalec_id = " + stanovalecId + ", cena = " + cena + " WHERE id = " + storitevId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId;
                db.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Storitev uspešno posodobljena.", "Obvestilo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String query = "INSERT INTO storitve (ime, opis, zaposleni_id, stanovalec_id, cena, uporabnik_id) VALUES ('" + ime + "', '" + opis + "', " + zaposleniId + ", " + stanovalecId + ", " + cena + ", " + StateFactory.getInstance().uporabnikId + ")";
                db.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Storitev uspešno dodana.", "Obvestilo", JOptionPane.INFORMATION_MESSAGE);
            }
            db.close();
            window.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri shranjevanju podatkov v podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }

    private class ZaposleniItem {
        public int id;
        public String ime;
        public String priimek;

        public ZaposleniItem(int id, String ime, String priimek) {
            this.id = id;
            this.ime = ime;
            this.priimek = priimek;
        }

        public String toString() {
            return this.ime + " " + this.priimek;
        }
    }

    private class StanovalecItem {
        public int id;
        public String ime;
        public String priimek;

        public StanovalecItem(int id, String ime, String priimek) {
            this.id = id;
            this.ime = ime;
            this.priimek = priimek;
        }

        public String toString() {
            return this.ime + " " + this.priimek;
        }
    }
}
