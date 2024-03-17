import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stanovalci {
    private JFrame window; // Okno aplikacije
    private Container container; // Glavni vsebnik za elemente uporabniškega vmesnika
    private JLabel mainTitle; // Glavni naslov obrazca
    private JTable table; // Tabela za prikaz stanovalcev
    private DefaultTableModel model; // Model tabele za shranjevanje podatkov
    private PostgreSQL db;

    public Stanovalci() {
        try {
            db = new PostgreSQL();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

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

        // Dodajanje vrstic v tabelo
        try {
            String query = "SELECT * FROM stanovalci st, sobe s WHERE st.soba_id = s.id AND st.uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
            ResultSet resultSet = db.executeQuery(query);
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getInt("id"), resultSet.getString("ime"), resultSet.getString("priimek"), resultSet.getString("datum_rojstva"), resultSet.getString("spol"), resultSet.getString("datum_sprejema"), resultSet.getString("naslov"), resultSet.getString("telefon"), resultSet.getString("el_naslov"), resultSet.getString("opombe"), resultSet.getString("stevilka_sobe")});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

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
        JButton refreshButton = new JButton("Osveži");
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                try {
                    String query = "SELECT * FROM stanovalci st, sobe s WHERE st.soba_id = s.id AND st.uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                    ResultSet resultSet = db.executeQuery(query);
                    while (resultSet.next()) {
                        model.addRow(new Object[]{resultSet.getInt("id"), resultSet.getString("ime"), resultSet.getString("priimek"), resultSet.getString("datum_rojstva"), resultSet.getString("spol"), resultSet.getString("datum_sprejema"), resultSet.getString("naslov"), resultSet.getString("telefon"), resultSet.getString("el_naslov"), resultSet.getString("opombe"), resultSet.getString("stevilka_sobe")});
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Poslušalci dogodkov za gumb "Dodaj nov stanovalca"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodajanje novega stanovalca
                // Tukaj bi morali odpreti novo okno za dodajanje stanovalca
                StanovalciObrazec obrazec = new StanovalciObrazec(0);
                obrazec.show();
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
                    String residentID = model.getValueAt(selectedRow, 0).toString();
                    StanovalciObrazec obrazec = new StanovalciObrazec(Integer.parseInt(residentID));
                    obrazec.show();
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
                    try {
                        String residentID = model.getValueAt(selectedRow, 0).toString();
                        String query = "DELETE FROM stanovalci WHERE id = " + residentID + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                        db.executeUpdate(query);
                        model.removeRow(selectedRow);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Napaka pri brisanju stanovalca.", "Napaka", JOptionPane.ERROR_MESSAGE);
                    }
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
