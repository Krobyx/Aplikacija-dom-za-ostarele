import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private int sobaId;

    public SobeObrazec(int sobaId) {
        this.sobaId = sobaId;

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
        shraniButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shraniSobo();
            }
        }); // Dodamo listener na gumb
        container.add(shraniButton); // Dodamo gumb v panel

        if (sobaId > 0) {
            try {
                PostgreSQL db = PostgreSQL.getInstance();
                String query = "SELECT * FROM sobe WHERE id = " + sobaId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                ResultSet rs = db.executeQuery(query);
                if (rs.next()) {
                    sobaStevilkaField.setText(rs.getString("stevilka_sobe"));
                    tipSobeField.setText(rs.getString("tip_sobe"));
                    opombeArea.setText(rs.getString("opombe"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(window, "Napaka pri pridobivanju podatkov sobe: " + ex.getMessage(), "Napaka", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void shraniSobo() {
        // Pridobimo podatke iz vnosnih polj
        String stevilkaSobe = sobaStevilkaField.getText();
        String tipSobe = tipSobeField.getText();
        String opombe = opombeArea.getText();

        // Preverimo, ali so vsa polja izpolnjena
        if (stevilkaSobe.isEmpty() || tipSobe.isEmpty()) {
            JOptionPane.showMessageDialog(window, "Prosimo, izpolnite vsa polja.", "Nepopolni podatki", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Shranimo podatke o sobi v bazo podatkov
        try {
            if (sobaId > 0) {
                PostgreSQL db = PostgreSQL.getInstance();
                String query = "SELECT update_room('" + stevilkaSobe + "', '" + tipSobe + "', '" + opombe + "', " + sobaId + ", " + StateFactory.getInstance().uporabnikId + ");";
                db.executeQuery(query);
                JOptionPane.showMessageDialog(window, "Soba uspešno posodobljena.", "Posodobljeno", JOptionPane.INFORMATION_MESSAGE);
            } else {
                PostgreSQL db = PostgreSQL.getInstance();
                String query = "SELECT insert_room('" + stevilkaSobe + "', '" + tipSobe + "', '" + opombe + "', " + StateFactory.getInstance().uporabnikId + ");";
                db.executeQuery(query);
                JOptionPane.showMessageDialog(window, "Soba uspešno shranjena.", "Shranjeno", JOptionPane.INFORMATION_MESSAGE);
            }

            window.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(window, "Napaka pri shranjevanju sobe: " + ex.getMessage(), "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
