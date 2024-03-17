import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VlogeObrazec {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel imeLabel;
    private JTextField imeField;
    private JLabel opisLabel;
    private JTextArea opisArea;
    private JButton shraniButton;
    private int vlogaId;

    public VlogeObrazec(int vlogaId) {
        this.vlogaId = vlogaId;

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
        shraniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shraniVlogo();
            }
        });
        container.add(shraniButton); // Dodamo gumb v container

        window.setVisible(true); // Naredimo okno vidno

        if (vlogaId != 0) {
            try {
                PostgreSQL db = new PostgreSQL();
                String query = "SELECT * FROM vloge WHERE id = " + vlogaId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                ResultSet resultSet = db.executeQuery(query);
                if (resultSet.next()) {
                    imeField.setText(resultSet.getString("naziv"));
                    opisArea.setText(resultSet.getString("opis"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void shraniVlogo() {
        String ime = imeField.getText();
        String opis = opisArea.getText();

        if (ime.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ime vloge ne sme biti prazno.", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            PostgreSQL db = new PostgreSQL();

            if (vlogaId <= 0) {
                String query = "INSERT INTO vloge (naziv, opis, uporabnik_id) VALUES ('" + ime + "', '" + opis + "', " + StateFactory.getInstance().uporabnikId + ");";
                db.executeUpdate(query);
            } else {
                String query = "UPDATE vloge SET naziv = '" + ime + "', opis = '" + opis + "' WHERE id = " + vlogaId + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                db.executeUpdate(query);
            }
            db.close();
            window.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
