import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

public class Zaposleni {
    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JTable table;
    private DefaultTableModel model;
    private PostgreSQL db;

    public Zaposleni() {
        try {
            db = PostgreSQL.getInstance(); // Pridobimo instanco razreda PostgreSQL
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

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

        try {
            String query = "SELECT * FROM zaposleni z, vloge v WHERE z.vloga_id = v.id AND z.uporabnik_id = " + StateFactory.getInstance().uporabnikId;
            ResultSet rs = db.executeQuery(query); // Izvedemo poizvedbo
            while (rs.next()) { // Gremo čez vse vrstice
                // Dodamo vrstico v model
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("ime"),
                        rs.getString("priimek"),
                        rs.getString("datum_rojstva"),
                        rs.getString("naziv"),
                        rs.getString("naslov"),
                        rs.getString("telefon"),
                        rs.getString("el_naslov"),
                        rs.getString("opombe")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

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
        JButton refreshButton = new JButton("Osveži");
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Osvežimo tabelo
                model.setRowCount(0); // Odstranimo vse vrstice iz tabele
                try {
                    String query = "SELECT * FROM zaposleni z, vloge v WHERE z.vloga_id = v.id AND z.uporabnik_id = " + StateFactory.getInstance().uporabnikId;
                    ResultSet rs = db.executeQuery(query); // Izvedemo poizvedbo
                    while (rs.next()) { // Gremo čez vse vrstice
                        // Dodamo vrstico v model
                        model.addRow(new Object[]{
                                rs.getString("id"),
                                rs.getString("ime"),
                                rs.getString("priimek"),
                                rs.getString("datum_rojstva"),
                                rs.getString("naziv"),
                                rs.getString("naslov"),
                                rs.getString("telefon"),
                                rs.getString("el_naslov"),
                                rs.getString("opombe")
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Dodaj nov zaposlenega"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodajanje novega zaposlenega
                // Tukaj bi morali odpreti novo okno za dodajanje zaposlenega
                ZaposleniObrazec obrazec = new ZaposleniObrazec(0);
                obrazec.show();
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
                    String employeeID = model.getValueAt(selectedRow, 0).toString();
                    ZaposleniObrazec obrazec = new ZaposleniObrazec(Integer.parseInt(employeeID));
                    obrazec.show();
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
                    try {
                        String employeeID = model.getValueAt(selectedRow, 0).toString();
                        String query = "SELECT delete_employee(" + employeeID + ", " + StateFactory.getInstance().uporabnikId + ")";
                        db.executeUpdate(query);
                        model.removeRow(selectedRow);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Napaka pri brisanju zaposlenega.", "Napaka", JOptionPane.ERROR_MESSAGE);
                    }
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
