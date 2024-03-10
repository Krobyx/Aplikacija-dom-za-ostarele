import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vloge {
    private JFrame window; // Okno aplikacije
    private Container container; // Glavni vsebnik za elemente uporabniškega vmesnika
    private JLabel mainTitle; // Glavni naslov obrazca
    private JTable table; // Tabela za prikaz vlog
    private DefaultTableModel model; // Model tabele za shranjevanje podatkov

    public Vloge() {
        // Inicializacija okna
        window = new JFrame("Vloge");
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavitev velikosti okna
        window.setBounds(10, 10, 1024, 768); // Nastavitev položaja in velikosti okna
        window.setLayout(new BorderLayout()); // Uporaba BorderLayout za razporeditev komponent
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zapiranje okna ob zaprtju
        window.setLocationRelativeTo(null); // Središčenje okna glede na zaslon
        window.setResizable(false); // Onemogočanje spreminjanja velikosti okna

        // Inicializacija glavnega vsebnika
        container = window.getContentPane();
        container.setLayout(new BorderLayout());

        // Dodajanje glavnega naslova obrazca
        mainTitle = new JLabel("Vloge");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48));
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(mainTitle);

        // Ustvarjanje modela tabele
        model = new DefaultTableModel();
        model.addColumn("ID"); // Dodajanje stolpca "ID"
        model.addColumn("Naziv"); // Dodajanje stolpca "Naziv"
        model.addColumn("Opis"); // Dodajanje stolpca "Opis"

        // Dodajanje vrstic v tabelo (začasni podatki)
        model.addRow(new Object[]{"1", "Vloga 1", "Opis vloge 1"});
        model.addRow(new Object[]{"2", "Vloga 2", "Opis vloge 2"});
        model.addRow(new Object[]{"3", "Vloga 3", "Opis vloge 3"});

        // Ustvarjanje tabele s podanim modelom
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 24));
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Onemogočanje samodejnega prilagajanja velikosti stolpcev

        // Nastavitev preferirane širine stolpcev
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(600);

        // Ustvarjanje plavajočega okvirja za tabelo
        JScrollPane scrollPane = new JScrollPane(table);

        // Ustvarjanje panela z gumbi
        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Dodaj novo vlogo");
        JButton editButton = new JButton("Uredi vlogo");
        JButton deleteButton = new JButton("Izbriši vlogo");
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Dodajanje poslušalcev dogodkov gumbom
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ob kliku na gumb "Dodaj novo vlogo" se prikaže sporočilo
                JOptionPane.showMessageDialog(container, "Odpri okno za dodajanje nove vloge.");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ob kliku na gumb "Uredi vlogo" se preveri izbrana vrstica in prikaže ustrezno sporočilo
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String roleID = (String) model.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(container, "Odpri okno za urejanje vloge z ID: " + roleID);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite vlogo za urejanje.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ob kliku na gumb "Izbriši vlogo" se preveri izbrana vrstica in izbriše ustrezno vrstico iz tabele
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite vlogo za brisanje.");
                }
            }
        });

        // Dodajanje elementov v glavni vsebnik
        container.add(scrollPane, BorderLayout.CENTER); // Dodajanje tabele
        container.add(buttonsPanel, BorderLayout.SOUTH); // Dodajanje panela z gumbi
    }

    // Metoda za prikaz okna
    public void show() {
        window.setVisible(true);
    }
}
