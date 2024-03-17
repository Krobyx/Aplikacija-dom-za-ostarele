import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {

    private JFrame window;
    private Container container;

    private JLabel mainTitle;

    public Home() {
        window = new JFrame("Storitve Obrazec"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(null); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container

        mainTitle = new JLabel("Nadzorna plošča"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setBounds(10, 50, 1004, 50); // Nastavimo pozicijo in velikost
        container.add(mainTitle); // Dodamo label v container

        JButton stanovalci = new JButton("Stanovalci");
        stanovalci.setBounds(10, 150, 200, 40);
        stanovalci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stanovalci stanovalci = new Stanovalci();
                stanovalci.show();
            }
        });
        container.add(stanovalci);

        JButton vloge = new JButton("Vloge");
        vloge.setBounds(10, 200, 200, 40);
        vloge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vloge vloge = new Vloge();
                vloge.show();
            }
        });
        container.add(vloge);

        JButton zaposleni = new JButton("Zaposleni");
        zaposleni.setBounds(10, 250, 200, 40);
        zaposleni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zaposleni zaposleni = new Zaposleni();
                zaposleni.show();
            }
        });
        container.add(zaposleni);

        JButton storitve = new JButton("Storitve");
        storitve.setBounds(10, 300, 200, 40);
        storitve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Storitve storitve = new Storitve();
                storitve.show();
            }
        });
        container.add(storitve);

        JButton odjava = new JButton("Sobe");
        odjava.setBounds(10, 350, 200, 40);
        odjava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sobe sobe = new Sobe();
                sobe.show();
            }
        });
        container.add(odjava);

        JButton logout = new JButton("Odjava");
        logout.setBounds(10, 400, 200, 40);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        container.add(logout);

        window.setVisible(true); // Naredimo okno vidno
    }


    private void logout() {
        // Implementacija odjave
        System.out.println("Odjava...");
        // Dodajte kodo za izhod iz aplikacije ali prijavo
    }

    public void show() {
        window.setVisible(true);
    }
}
