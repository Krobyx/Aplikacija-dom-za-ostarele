import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private JComboBox<VlogaItem> vlogaComboBox;
    private JLabel naslovLabel;
    private JTextField naslovField;
    private JLabel telefonLabel;
    private JTextField telefonField;
    private JLabel elNaslovLabel;
    private JTextField elNaslovField;
    private JLabel opombeLabel;
    private JTextArea opombeArea;
    private JButton shraniButton;
    private int zaposleniId;
    private PostgreSQL db;

    public ZaposleniObrazec(int zaposleniId) {
        this.zaposleniId = zaposleniId;

        try {
            db = new PostgreSQL();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

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

        vlogaComboBox = new JComboBox<VlogaItem>(); // Ustvarimo nov izbirni seznam
        vlogaComboBox.setBounds(220, 300, 200, 40); // Nastavimo pozicijo in velikost
        // Dodamo možnosti v izbirni seznam (primer)
        try {
            String query = "SELECT * FROM vloge WHERE uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
            ResultSet resultSet = db.executeQuery(query);
            while (resultSet.next()) {
                vlogaComboBox.addItem(new VlogaItem(resultSet.getInt("id"), resultSet.getString("naziv")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        shraniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                shraniZaposlenega();
            }
        });
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno

        if (zaposleniId > 0) {
            try {
                String query = "SELECT * FROM zaposleni WHERE id = " + zaposleniId + ";";
                ResultSet resultSet = db.executeQuery(query);
                if (resultSet.next()) {
                    imeField.setText(resultSet.getString("ime"));
                    priimekField.setText(resultSet.getString("priimek"));
                    datumRojstvaField.setText(resultSet.getString("datum_rojstva"));
                    naslovField.setText(resultSet.getString("naslov"));
                    telefonField.setText(resultSet.getString("telefon"));
                    elNaslovField.setText(resultSet.getString("el_naslov"));
                    opombeArea.setText(resultSet.getString("opombe"));
                    for (int i = 0; i < vlogaComboBox.getItemCount(); i++) {
                        if (vlogaComboBox.getItemAt(i).id == resultSet.getInt("vloga_id")) {
                            vlogaComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void shraniZaposlenega() {
        String ime = imeField.getText();
        String priimek = priimekField.getText();
        String datumRojstva = datumRojstvaField.getText();
        String naslov = naslovField.getText();
        String telefon = telefonField.getText();
        String elNaslov = elNaslovField.getText();
        String opombe = opombeArea.getText();
        int vlogaId = vlogaComboBox.getItemAt(vlogaComboBox.getSelectedIndex()).id;

        if (ime.isEmpty() || priimek.isEmpty() || datumRojstva.isEmpty() || naslov.isEmpty() || telefon.isEmpty() || elNaslov.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vsa polja morajo biti izpolnjena.", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (zaposleniId > 0) {
            try {
                String query = "UPDATE zaposleni SET ime = '" + ime + "', priimek = '" + priimek + "', datum_rojstva = '" + datumRojstva + "', naslov = '" + naslov + "', telefon = '" + telefon + "', el_naslov = '" + elNaslov + "', opombe = '" + opombe + "', vloga_id = " + vlogaId + " WHERE id = " + zaposleniId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                db.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Zaposleni uspešno posodobljen.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                db.close();
                window.dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Napaka pri posodabljanju zaposlenega.", "Napaka", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                String query = "INSERT INTO zaposleni (ime, priimek, datum_rojstva, naslov, telefon, el_naslov, opombe, vloga_id, uporabnik_id) VALUES ('" + ime + "', '" + priimek + "', '" + datumRojstva + "', '" + naslov + "', '" + telefon + "', '" + elNaslov + "', '" + opombe + "', " + vlogaId + ", " + StateFactory.getInstance().uporabnikId + ");";
                db.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Zaposleni uspešno dodan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                db.close();
                window.dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Napaka pri dodajanju zaposlenega.", "Napaka", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }

    private class VlogaItem {
        public int id;
        public String naziv;

        public VlogaItem(int id, String naziv) {
            this.id = id;
            this.naziv = naziv;
        }

        public String toString() {
            return naziv;
        }
    }
}
