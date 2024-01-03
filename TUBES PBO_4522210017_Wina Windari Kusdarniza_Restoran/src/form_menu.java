import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_menu extends JFrame {
    private JTextField tb_namaMenu;
    private JTextField tb_harga;
    private JTextField tb_deskripsi;
    private JTextField tb_kategori;
    private JTextField tb_rating;
    private JTextField tb_stok;
    private JButton btn_simpan;
    private JButton btn_reset;
    private JTable menuTable;
    private DefaultTableModel menuTableModel;
    private JPanel menu_panel;

    public form_menu() {
        // Set up the frame
        setTitle("Form Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);

        // Initialize components
        tb_namaMenu = new JTextField(20);
        tb_harga = new JTextField(20);
        tb_deskripsi = new JTextField(20);
        tb_kategori = new JTextField(20);
        tb_rating = new JTextField(20);
        tb_stok = new JTextField(20);
        btn_simpan = new JButton("Simpan");
        btn_reset = new JButton("Reset");

        menuTableModel = new DefaultTableModel();
        menuTableModel.addColumn("Nama Menu");
        menuTableModel.addColumn("Harga");
        menuTableModel.addColumn("Deskripsi");
        menuTableModel.addColumn("Kategori");
        menuTableModel.addColumn("Rating");
        menuTableModel.addColumn("Stok");

        menuTable = new JTable(menuTableModel);

        // Set layout
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        menuPanel.add(new JLabel("Nama Menu:"), gbc);

        gbc.gridx = 1;
        menuPanel.add(tb_namaMenu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        menuPanel.add(new JLabel("Harga:"), gbc);

        gbc.gridx = 1;
        menuPanel.add(tb_harga, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        menuPanel.add(new JLabel("Deskripsi:"), gbc);

        gbc.gridx = 1;
        menuPanel.add(tb_deskripsi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        menuPanel.add(new JLabel("Kategori:"), gbc);

        gbc.gridx = 1;
        menuPanel.add(tb_kategori, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        menuPanel.add(new JLabel("Rating:"), gbc);

        gbc.gridx = 1;
        menuPanel.add(tb_rating, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        menuPanel.add(new JLabel("Stok:"), gbc);

        gbc.gridx = 1;
        menuPanel.add(tb_stok, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(btn_simpan, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        menuPanel.add(btn_reset, gbc);

        // Add table to the layout
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 8;
        menuPanel.add(new JScrollPane(menuTable), gbc);

        // Add action listeners
        btn_simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click event
                simpanData();
            }
        });

        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click event
                resetData();
            }
        });

        // Add panel to the frame
        add(menuPanel);

        // Display the frame
        setVisible(true);
    }

    private void simpanData() {
        // Get values from text fields
        String namaMenu = tb_namaMenu.getText();
        String harga = tb_harga.getText();
        String deskripsi = tb_deskripsi.getText();
        String kategori = tb_kategori.getText();
        String rating = tb_rating.getText();
        String stok = tb_stok.getText();

        // Add data to the table
        menuTableModel.addRow(new Object[]{namaMenu, harga, deskripsi, kategori, rating, stok});

        // Perform data saving or processing here
        // For simplicity, let's just show a popup message
        JOptionPane.showMessageDialog(this,
                "Data berhasil ditambahkan:\n" +
                        "Nama Menu: " + namaMenu + "\n" +
                        "Harga: " + harga + "\n" +
                        "Deskripsi: " + deskripsi + "\n" +
                        "Kategori: " + kategori + "\n" +
                        "Rating: " + rating + "\n" +
                        "Stok: " + stok,
                "Data Menu Tersimpan",
                JOptionPane.INFORMATION_MESSAGE);

        // Reset data after saving
        resetData();
    }

    private void resetData() {
        // Reset values in text fields
        tb_namaMenu.setText("");
        tb_harga.setText("");
        tb_deskripsi.setText("");
        tb_kategori.setText("");
        tb_rating.setText("");
        tb_stok.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form_menu();
            }
        });
    }
}
