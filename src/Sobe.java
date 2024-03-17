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

public class Sobe {
    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JTable table;
    private DefaultTableModel model;

    private PostgreSQL db;

    public Sobe() {
        try {
            db = new PostgreSQL();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Napaka pri povezavi s podatkovno bazo.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }

        window = new JFrame("Sobe"); // Ustvarimo novo okno
        window.setPreferredSize(new Dimension(1024, 768)); // Nastavimo velikost okna
        window.setBounds(10, 10, 1024, 768); // Nastavimo pozicijo in velikost okna
        window.setLayout(new BorderLayout()); // Nastavimo postavitev okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nastavimo akcijo ob zaprtju okna
        window.setLocationRelativeTo(null); // Nastavimo pozicijo okna na sredino
        window.setResizable(false); // Omogočimo spreminjanje velikosti okna

        container = window.getContentPane(); // Ustvarimo nov container
        container.setLayout(new BorderLayout()); // Nastavimo postavitev panela

        mainTitle = new JLabel("Sobe"); // Ustvarimo nov label
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48)); // Nastavimo velikost in obliko pisave
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Nastavimo poravnavo
        container.add(mainTitle); // Dodamo label v panel

        model = new DefaultTableModel(); // Ustvarimo nov model
        model.addColumn("ID"); // Dodamo stolpec
        model.addColumn("Številka sobe"); // Dodamo stolpec
        model.addColumn("Tip sobe"); // Dodamo stolpec
        model.addColumn("Opombe"); // Dodamo stolpec

        try {
            String query = "SELECT * FROM sobe WHERE uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
            ResultSet resultSet = db.executeQuery(query);
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getInt("id"), resultSet.getString("stevilka_sobe"), resultSet.getString("tip_sobe"), resultSet.getString("opombe")});
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

        TableColumnModel columnModel = table.getColumnModel(); // Ustvarimo nov column model
        columnModel.getColumn(0).setPreferredWidth(50); // Nastavimo preferirano širino stolpca
        columnModel.getColumn(1).setPreferredWidth(200); // Nastavimo preferirano širino stolpca
        columnModel.getColumn(2).setPreferredWidth(200); // Nastavimo preferirano širino stolpca
        columnModel.getColumn(3).setPreferredWidth(500); // Nastavimo preferirano širino stolpca

        JScrollPane scrollPane = new JScrollPane(table); // Ustvarimo nov scroll pane

        JPanel buttonsPanel = new JPanel(); // Ustvarimo nov panel
        JButton addButton = new JButton("Dodaj novo sobo"); // Ustvarimo nov gumb
        JButton editButton = new JButton("Uredi sobo"); // Ustvarimo nov gumb
        JButton deleteButton = new JButton("Izbriši sobo"); // Ustvarimo nov gumb
        JButton refreshButton = new JButton("Osveži");
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(addButton); // Dodamo gumb v panel
        buttonsPanel.add(editButton); // Dodamo gumb v panel
        buttonsPanel.add(deleteButton); // Dodamo gumb v panel

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                try {
                    String query = "SELECT * FROM sobe WHERE uporabnik_id = " + StateFactory.getInstance().uporabnikId + ";";
                    ResultSet resultSet = db.executeQuery(query);
                    while (resultSet.next()) {
                        model.addRow(new Object[]{resultSet.getInt("id"), resultSet.getString("stevilka_sobe"), resultSet.getString("tip_sobe"), resultSet.getString("opombe")});
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Napaka pri pridobivanju podatkov iz baze.", "Napaka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SobeObrazec sobObrazec = new SobeObrazec(0); // Ustvarimo novo instanco razreda SobeObrazec
                sobObrazec.show(); // Pokažemo okno
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for editing room
                // This will open a new window for editing the selected room
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    SobeObrazec sobObrazec = new SobeObrazec(Integer.parseInt(model.getValueAt(selectedRow, 0).toString()));
                    sobObrazec.show();
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite sobo za urejanje.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for deleting room
                // This will delete the selected room from the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Remove selected row from table
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(container, "Prosimo, izberite sobo za brisanje.");
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
