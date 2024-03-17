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

public class Storitve {
    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JTable table;
    private DefaultTableModel model;
    private PostgreSQL db;

    public Storitve() {
        try {
            db = new PostgreSQL();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

        window = new JFrame("Storitve"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(new BorderLayout()); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container
        container.setLayout(new BorderLayout()); // Nastavimo postavitev panela

        mainTitle = new JLabel("Storitve"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Nastavimo poravnavo
        container.add(mainTitle); // Dodamo label v panel

        // Ustvarimo model za tabelo
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Ime storitve");
        model.addColumn("Opis");
        model.addColumn("Cena");
        model.addColumn("Zaposleni");
        model.addColumn("Stanovalec");

        try {
            String query = "SELECT s.*, (z.ime || ' ' || z.priimek) AS zaposleni, (st.ime || ' ' || st.priimek) AS stanovalec FROM storitve s LEFT JOIN zaposleni z ON s.zaposleni_id = z.id LEFT JOIN stanovalci st ON s.stanovalec_id = st.id WHERE s.uporabnik_id = " + StateFactory.getInstance().uporabnikId;
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id"),
                    rs.getString("ime"),
                    rs.getString("opis"),
                    rs.getString("cena"),
                    rs.getString("zaposleni"),
                    rs.getString("stanovalec")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz podatkovne baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

        // Ustvarimo tabelo in ji nastavimo pisavo ter višino vrstice
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 24));
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null); // Onemogočimo urejanje celic
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Onemogočimo samodejno prilagajanje velikosti

        // Nastavimo preferirane širine stolpcev
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(200);
        columnModel.getColumn(5).setPreferredWidth(200);


        // Ustvarimo drsnega okna za tabelo
        JScrollPane scrollPane = new JScrollPane(table);

        // Ustvarimo panel z gumbi
        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Dodaj novo storitev");
        JButton editButton = new JButton("Uredi storitev");
        JButton deleteButton = new JButton("Izbriši storitev");
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
                    String query = "SELECT s.*, (z.ime || ' ' || z.priimek) AS zaposleni, (st.ime || ' ' || st.priimek) AS stanovalec FROM storitve s LEFT JOIN zaposleni z ON s.zaposleni_id = z.id LEFT JOIN stanovalci st ON s.stanovalec_id = st.id WHERE s.uporabnik_id = " + StateFactory.getInstance().uporabnikId;
                    ResultSet rs = db.executeQuery(query);
                    while (rs.next()) {
                        model.addRow(new Object[] {
                            rs.getString("id"),
                            rs.getString("ime"),
                            rs.getString("opis"),
                            rs.getString("cena"),
                            rs.getString("zaposleni"),
                            rs.getString("stanovalec")
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz podatkovne baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Dodaj novo storitev"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoritveObrazec storitveObrazec = new StoritveObrazec(0);
                storitveObrazec.show();
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Uredi storitev"
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String serviceID = model.getValueAt(selectedRow, 0).toString();
                    StoritveObrazec storitveObrazec = new StoritveObrazec(Integer.parseInt(serviceID));
                    storitveObrazec.show();
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite storitev za urejanje.");
                }
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Izbriši storitev"
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        String serviceID = model.getValueAt(selectedRow, 0).toString();
                        String query = "DELETE FROM storitve WHERE id = " + serviceID + " AND uporabnik_id = " + StateFactory.getInstance().uporabnikId;
                        db.executeUpdate(query);
                        model.removeRow(selectedRow);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(container, "Napaka pri brisanju storitve.", "Napaka", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite storitev za brisanje.");
                }
            }
        });

        // Dodamo tabelo in panele z gumbi na glavni panel
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void show() {
        window.setVisible(true);
    }
}
