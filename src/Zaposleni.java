import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zaposleni {
    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JTable table;
    private DefaultTableModel model;

    public Zaposleni() {
        window = new JFrame("Zaposleni"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(new BorderLayout()); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container
        container.setLayout(new BorderLayout()); // Nastavimo postavitev panela

        mainTitle = new JLabel("Zaposleni"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Nastavimo poravnavo
        container.add(mainTitle); // Dodamo label v panel

        model = new DefaultTableModel(); // Ustvarimo nov model
        model.addColumn("ID"); // Dodamo stolpec
        model.addColumn("Ime"); // Dodamo stolpec
        model.addColumn("Priimek"); // Dodamo stolpec
        model.addColumn("Datum rojstva"); // Dodamo stolpec
        model.addColumn("Vloga"); // Dodamo stolpec
        model.addColumn("Naslov"); // Dodamo stolpec
        model.addColumn("Telefon"); // Dodamo stolpec
        model.addColumn("E-pošta"); // Dodamo stolpec
        model.addColumn("Opombe"); // Dodamo stolpec

        // Dodajanje nekaterih vzorčnih podatkov
        model.addRow(new Object[]{"1", "Janez", "Novak", "01.01.1980", "Negovalec", "Celovška cesta 123, Ljubljana", "01 234 567", "janez.novak@example.com", "Opombe 1"});
        model.addRow(new Object[]{"2", "Ana", "Kovač", "15.05.1975", "Medicinska sestra", "Prešernova ulica 45, Maribor", "02 345 678", "ana.kovac@example.com", "Opombe 2"});
        model.addRow(new Object[]{"3", "Peter", "Horvat", "10.10.1985", "Kuhar", "Trubarjeva cesta 56, Celje", "03 456 789", "peter.horvat@example.com", "Opombe 3"});

        table = new JTable(model); // Ustvarimo novo tabelo
        table.setFont(new Font("Arial", Font.PLAIN, 24)); // Nastavimo pisavo
        table.setRowHeight(30); // Nastavimo visino vrstice
        table.setDefaultEditor(Object.class, null); // Onemogočimo urejanje celic
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Onemogočimo samodejno prilagajanje velikosti

        // Nastavimo preferirane širine stolpcev
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(300);
        columnModel.getColumn(5).setPreferredWidth(400);
        columnModel.getColumn(6).setPreferredWidth(300);
        columnModel.getColumn(7).setPreferredWidth(400);
        columnModel.getColumn(8).setPreferredWidth(400);

        // Ustvarimo drsnega okna za tabelo
        JScrollPane scrollPane = new JScrollPane(table);

        // Ustvarimo panel z gumbi
        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Dodaj novega zaposlenega");
        JButton editButton = new JButton("Uredi zaposlenega");
        JButton deleteButton = new JButton("Izbriši zaposlenega");
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Dodamo poslušalce dogodkov za gumb "Dodaj nov zaposlenega"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodajanje novega zaposlenega
                // Tukaj bi morali odpreti novo okno za dodajanje zaposlenega
                JOptionPane.showMessageDialog(container, "Odpri okno za dodajanje novega zaposlenega.");
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Uredi zaposlenega"
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Urejanje zaposlenega
                // Tukaj bi morali odpreti novo okno za urejanje izbranega zaposlenega
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Pridobimo ID zaposlenega iz izbrane vrstice
                    String employeeID = (String) model.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(container, "Odpri okno za urejanje zaposlenega z ID: " + employeeID);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite zaposlenega za urejanje.");
                }
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Izbriši zaposlenega"
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Brisanje zaposlenega
                // To bo izbrisalo izbranega zaposlenega iz tabele
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Odstranimo izbrano vrstico iz tabele
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite zaposlenega za brisanje.");
                }
            }
        });

        container.add(scrollPane, BorderLayout.CENTER); // Dodamo tabelo v panel
        container.add(buttonsPanel, BorderLayout.SOUTH); // Dodamo panel z gumbi v panel
    }

    public void show() {
        window.setVisible(true); // Naredimo okno vidno
    }
}
