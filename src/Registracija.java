import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registracija {

    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel passwordConfirmLabel;
    private JPasswordField passwordConfirmField;
    private JButton registerButton;
    private JButton loginButton;
    private PostgreSQL db;

    public Registracija() {
        try {
            db = new PostgreSQL();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

        window = new JFrame("Registracija");
        window.setPreferredSize(new Dimension(1024, 768));
        window.setBounds(10, 10, 1024, 768);
        window.setLayout(new BorderLayout()); // Nastavimo postavitev okna// Nastavimo minimalno velikost okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container
        container.setLayout(null); // Nastavimo postavitev panela

        mainTitle = new JLabel("Registracija"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Nastavimo poravnavo
        mainTitle.setBounds(10, 50, 1004, 50); // Nastavimo pozicijo in velikost
        container.add(mainTitle); // Dodamo label v panel

        emailLabel = new JLabel("Elektroski naslov:"); // Ustvarimo nov label
        emailLabel.setBounds(10, 150, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(emailLabel); // Dodamo label v panel

        emailField = new JTextField(); // Ustvarimo nov textfield
        emailField.setBounds(10, 190, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(emailField); // Dodamo textfield v panel

        passwordLabel = new JLabel("Geslo:"); // Ustvarimo nov label
        passwordLabel.setBounds(10, 240, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(passwordLabel); // Dodamo label v panel

        passwordField = new JPasswordField(); // Ustvarimo nov textfield
        passwordField.setBounds(10, 280, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(passwordField); // Dodamo textfield v panel

        passwordConfirmLabel = new JLabel("Potrdi geslo:"); // Ustvarimo nov label
        passwordConfirmLabel.setBounds(10, 330, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(passwordConfirmLabel); // Dodamo label v panel

        passwordConfirmField = new JPasswordField(); // Ustvarimo nov textfield
        passwordConfirmField.setBounds(10, 370, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(passwordConfirmField); // Dodamo textfield v panel

        registerButton = new JButton("Registriraj se"); // Ustvarimo nov gumb
        registerButton.setBounds(10, 420, 1004, 40); // Nastavimo pozicijo in velikost
        registerButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        }); // Dodamo listener na gumb
        container.add(registerButton); // Dodamo gumb v panel

        loginButton = new JButton("Pojdi na prijavo"); // Ustvarimo nov gumb
        loginButton.setBounds(10, 650, 1004, 40); // Nastavimo pozicijo in velikost
        loginButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        }); // Dodamo listener na gumb
        container.add(loginButton); // Dodamo gumb v panel
    }

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Tukaj bi izvedli dejansko registracijo uporabnika
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(passwordConfirmField.getPassword());

        // Preverimo, ali so vsa polja izpolnjena
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(window, "Prosimo, izpolnite vsa polja.", "Nepopolni podatki", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Preverimo, ali se gesli ujemata
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(window, "Gesli se ne ujemata.", "Neveljavna gesla", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Registriramo uporabnika
        try {
            // Preverimo, ali je e-poštni naslov že v uporabi
            if (checkIfEmailExists(email)) {
                JOptionPane.showMessageDialog(window, "Ta e-poštni naslov je že v uporabi.", "Napaka", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Dodamo uporabnika v bazo podatkov
            registerUser(email, password);

            // Po uspešni registraciji lahko naredimo kaj takega, kot je prijava uporabnika ali prikaz drugega okna
            JOptionPane.showMessageDialog(window, "Uspešno ste se registrirali.", "Registracija uspešna", JOptionPane.INFORMATION_MESSAGE);
            Prijava prijava = new Prijava(); // Ustvarimo novo okno
            prijava.show(); // Pokažemo novo okno
            window.dispose(); // Zapremo trenutno okno
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Napaka pri registraciji uporabnika.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkIfEmailExists(String email) throws SQLException {
        // Preverimo, ali e-poštni naslov že obstaja v bazi
        String query = "SELECT COUNT(*) AS count FROM uporabniki WHERE el_naslov = '" + email + "'";
        ResultSet resultSet = db.executeQuery(query);
        resultSet.next();
        int count = resultSet.getInt("count");
        return count > 0;
    }

    private void registerUser(String email, String password) throws SQLException {
        // Dodamo uporabnika v bazo podatkov
        String query = "INSERT INTO uporabniki (el_naslov, geslo) VALUES ('" + email + "', '" + password + "')";
        db.executeUpdate(query);
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Prijava prijava = new Prijava(); // Ustvarimo novo okno
        prijava.show(); // Pokažemo novo okno
        window.dispose(); // Zapremo trenutno okno
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
