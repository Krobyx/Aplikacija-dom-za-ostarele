import javax.swing.*;
import java.awt.*;

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
    private JLabel errorLabel;

    public Registracija() {
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
        container.add(registerButton); // Dodamo gumb v panel

        loginButton = new JButton("Pojdi na prijavo"); // Ustvarimo nov gumb
        loginButton.setBounds(10, 650, 1004, 40); // Nastavimo pozicijo in velikost
        container.add(loginButton); // Dodamo gumb v panel
    }

    public void show() {
        window.setVisible(true);
    }
}
