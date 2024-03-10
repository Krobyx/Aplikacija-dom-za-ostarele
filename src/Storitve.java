import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Storitve {
    private JFrame window;
    private Container container;
    private JLabel mainTitle;
    private JTable table;
    private DefaultTableModel model;

    public Storitve() {
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

        // Dodamo vzorčne podatke
        model.addRow(new Object[]{"1", "Čiščenje", "Redno čiščenje sob", "30.00"});
        model.addRow(new Object[]{"2", "Negovanje", "Pomoč pri osebni negi", "25.00"});
        model.addRow(new Object[]{"3", "Prevoz", "Prevoz na izlete in nakupe", "20.00"});
        model.addRow(new Object[]{"4", "Kuharske storitve", "Priprava obrokov", "35.00", "Peter Horvat"});
        model.addRow(new Object[]{"5", "Pomoč pri sprehodu", "Pomoč pri sprehodu", "15.00", "Janez Novak", "Mojca Kovač"});

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
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Dodamo poslušalce dogodkov za gumb "Dodaj novo storitev"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(container, "Odpri okno za dodajanje nove storitve.");
            }
        });

        // Dodamo poslušalce dogodkov za gumb "Uredi storitev"
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String serviceID = (String) model.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(container, "Odpri okno za urejanje storitve z ID: " + serviceID);
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
                    model.removeRow(selectedRow);
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
