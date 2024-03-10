import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stanovalci {
    private JFrame window; // Okno aplikacije
    private Container container; // Glavni vsebnik za elemente uporabniškega vmesnika
    private JLabel mainTitle; // Glavni naslov obrazca
    private JTable table; // Tabela za prikaz stanovalcev
    private DefaultTableModel model; // Model tabele za shranjevanje podatkov

    public Stanovalci() {
        // Inicializacija okna
        window = new JFrame("Stanovalci");
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
        mainTitle = new JLabel("Stanovalci");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48));
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(mainTitle);

        // Ustvarjanje modela tabele
        model = new DefaultTableModel();
        model.addColumn("ID"); // Dodajanje stolpca "ID"
        model.addColumn("Ime"); // Dodajanje stolpca "Ime"
        model.addColumn("Priimek"); // Dodajanje stolpca "Priimek"
        model.addColumn("Datum rojstva"); // Dodajanje stolpca "Datum rojstva"
        model.addColumn("Spol"); // Dodajanje stolpca "Spol"
        model.addColumn("Datum sprejema"); // Dodajanje stolpca "Datum sprejema"
        model.addColumn("Naslov"); // Dodajanje stolpca "Naslov"
        model.addColumn("Telefon"); // Dodajanje stolpca "Telefon"
        model.addColumn("E-naslov"); // Dodajanje stolpca "E-naslov"
        model.addColumn("Opombe"); // Dodajanje stolpca "Opombe"
        model.addColumn("Soba"); // Dodajanje stolpca "Soba"

        // Dodajanje vrstic v tabelo (začasni podatki)
        model.addRow(new Object[]{"1", "Janez", "Novak", "01.01.1990", "M", "01.01.2022", "Naslov 1", "0123456789", "janez.novak@example.com", "Opombe 1", "101"});
        model.addRow(new Object[]{"2", "Ana", "Kovač", "15.05.1985", "Ž", "01.01.2022", "Naslov 2", "9876543210", "ana.kovac@example.com", "Opombe 2", "202"});
        model.addRow(new Object[]{"3", "Miha", "Horvat", "20.12.1978", "M", "01.01.2022", "Naslov 3", "1234567890", "miha.horvat@example.com", "Opombe 3", "303"});

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
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(200);
        columnModel.getColumn(5).setPreferredWidth(200);
        columnModel.getColumn(6).setPreferredWidth(300);
        columnModel.getColumn(7).setPreferredWidth(200);
        columnModel.getColumn(8).setPreferredWidth(400);
        columnModel.getColumn(9).setPreferredWidth(400);
        columnModel.getColumn(10).setPreferredWidth(100);

        // Ustvarjanje drsnega okna za tabelo
        JScrollPane scrollPane = new JScrollPane(table);

        // Ustvarjanje panela z gumbi
        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Dodaj novega stanovalca");
        JButton editButton = new JButton("Uredi stanovalca");
        JButton deleteButton = new JButton("Izbriši stanovalca");
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Poslušalci dogodkov za gumb "Dodaj nov stanovalca"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodajanje novega stanovalca
                // Tukaj bi morali odpreti novo okno za dodajanje stanovalca
                JOptionPane.showMessageDialog(container, "Odpri okno za dodajanje novega stanovalca.");
            }
        });

        // Poslušalci dogodkov za gumb "Uredi stanovalca"
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Urejanje stanovalca
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Pridobitev ID-ja stanovalca iz izbrane vrstice
                    String residentID = (String) model.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(container, "Odpri okno za urejanje stanovalca z ID: " + residentID);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite stanovalca za urejanje.");
                }
            }
        });

        // Poslušalci dogodkov za gumb "Izbriši stanovalca"
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Brisanje stanovalca
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Odstranitev izbrane vrstice iz tabele
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite stanovalca za brisanje.");
                }
            }
        });

        // Dodajanje tabele v glavni vsebnik
        container.add(scrollPane, BorderLayout.CENTER);
        // Dodajanje panela z gumbi v glavni vsebnik
        container.add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Metoda za prikaz okna
    public void show() {
        window.pack(); // Samodejno prilagodi velikost okna glede na vsebino
        window.setVisible(true); // Naredi okno vidno
    }
}
