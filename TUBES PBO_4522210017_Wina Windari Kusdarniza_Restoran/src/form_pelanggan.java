import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class form_pelanggan extends JFrame {
    private JTextField tb_id;
    private JTextField tb_nama;
    private JTextField tb_alamat;
    private JTextField tb_nomorTelepon;
    private JTextField tb_email;
    private JTextField daftarMenuTextField;
    private JTextField tambahMenuTextField;
    private JButton btn_tambahMenu;
    private JButton btn_simpan;
    private JButton btn_reset;
    private JPanel pelanggan_panel;
    private JTable pelangganTable;
    private DefaultTableModel pelangganTableModel;
    private int currentSelectedRow = -1;
    private int idCounter = 1;


    public form_pelanggan() {
        setTitle("Form Pelanggan Online");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1900, 1600); // Sesuaikan ukuran form

        tb_id = new JTextField(10);
        tb_nama = new JTextField(20);
        tb_alamat = new JTextField(20);
        tb_nomorTelepon = new JTextField(20);
        tb_email = new JTextField(20);
        daftarMenuTextField = new JTextField(20);
        daftarMenuTextField.setEditable(false);
        tambahMenuTextField = new JTextField(20);
        btn_tambahMenu = new JButton("Tambah Menu");
        btn_simpan = new JButton("Simpan");
        btn_reset = new JButton("Reset");

        // Set layout
        JPanel pelangganPanel = new JPanel();
        pelangganPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        pelangganPanel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pelangganPanel.add(tb_id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pelangganPanel.add(new JLabel("Nama:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pelangganPanel.add(tb_nama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pelangganPanel.add(new JLabel("Alamat:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pelangganPanel.add(tb_alamat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pelangganPanel.add(new JLabel("Nomor Telepon:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pelangganPanel.add(tb_nomorTelepon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pelangganPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pelangganPanel.add(tb_email, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pelangganPanel.add(new JLabel("Daftar Menu:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pelangganPanel.add(daftarMenuTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        pelangganPanel.add(tambahMenuTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        pelangganPanel.add(btn_tambahMenu, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 8;
        pelangganTableModel = new DefaultTableModel();
        pelangganTableModel.addColumn("ID");
        pelangganTableModel.addColumn("Nama");
        pelangganTableModel.addColumn("Alamat");
        pelangganTableModel.addColumn("Nomor Telepon");
        pelangganTableModel.addColumn("Email");
        pelangganTableModel.addColumn("Daftar Menu");

        pelangganTable = new JTable(pelangganTableModel);
        JScrollPane pelangganScrollPane = new JScrollPane(pelangganTable);
        pelangganPanel.add(pelangganScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        pelangganPanel.add(btn_simpan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2; // Perbarui gridwidth untuk tombol reset
        gbc.anchor = GridBagConstraints.CENTER;
        pelangganPanel.add(btn_reset, gbc);

        pelangganTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    currentSelectedRow = pelangganTable.getSelectedRow();
                    if (currentSelectedRow != -1) {
                        // Mendapatkan data dari tabel dan mengisinya ke JTextField
                        tb_id.setText(pelangganTableModel.getValueAt(currentSelectedRow, 0).toString());
                        tb_nama.setText(pelangganTableModel.getValueAt(currentSelectedRow, 1).toString());
                        tb_alamat.setText(pelangganTableModel.getValueAt(currentSelectedRow, 2).toString());
                        tb_nomorTelepon.setText(pelangganTableModel.getValueAt(currentSelectedRow, 3).toString());
                        tb_email.setText(pelangganTableModel.getValueAt(currentSelectedRow, 4).toString());
                        daftarMenuTextField.setText(pelangganTableModel.getValueAt(currentSelectedRow, 5).toString());
                    }
                }
            }
        });

        tb_id.setText(generateNextID());

        btn_tambahMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahMenu();
            }
        });

        btn_simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidEmail(tb_email.getText())) {
                    simpanData();
                } else {
                    JOptionPane.showMessageDialog(form_pelanggan.this,
                            "Format email tidak valid! Gunakan format email Gmail.",
                            "Email Tidak Valid",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });


        // Add panel to the frame
        add(pelangganPanel);

        // Display the frame
        setVisible(true);
    }

    private boolean isValidEmail(String email) {
        // Validasi format email menggunakan ekspresi reguler
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:gmail\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void tambahMenu() {
        String menuBaru = tambahMenuTextField.getText();

        // Menambahkan menu baru ke daftarMenuTextField
        if (!menuBaru.isEmpty()) {
            String daftarMenu = daftarMenuTextField.getText();
            if (!daftarMenu.isEmpty()) {
                daftarMenu += ", ";
            }
            daftarMenu += menuBaru;
            daftarMenuTextField.setText(daftarMenu);
            tambahMenuTextField.setText("");
        }
    }
    private void simpanData() {
        String id = generateNextID();
        String nama = tb_nama.getText();
        String alamat = tb_alamat.getText();
        String nomorTelepon = tb_nomorTelepon.getText();
        String email = tb_email.getText();
        String daftarMenu = daftarMenuTextField.getText();

        if (currentSelectedRow == -1) {
            // Jika tidak ada baris yang dipilih, tambahkan data baru
            tambahData(id, nama, alamat, nomorTelepon, email, daftarMenu);
        } else {
            // Jika ada baris yang dipilih, edit data yang sudah ada
            editData(currentSelectedRow, id, nama, alamat, nomorTelepon, email, daftarMenu);
            currentSelectedRow = -1; // Reset currentSelectedRow setelah editing
        }

        // Kosongkan JTextField setelah menyimpan
        reset();

        JOptionPane.showMessageDialog(this,
                "Data pelanggan berhasil disimpan:\n" +
                        "ID: " + id + "\n" +
                        "Nama: " + nama + "\n" +
                        "Alamat: " + alamat + "\n" +
                        "Nomor Telepon: " + nomorTelepon + "\n" +
                        "Email: " + email + "\n" +
                        "Daftar Menu: " + daftarMenu,
                "Data Tersimpan",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String generateNextID() {
        // Menghasilkan ID berikutnya dalam format "PLG001", "PLG002", dst.
        String formattedID = String.format("PLG%03d", idCounter);
        idCounter++;
        return formattedID;
    }

    private void tambahData(String id, String nama, String alamat, String nomorTelepon, String email, String daftarMenu) {
        Object[] rowData = {id, nama, alamat, nomorTelepon, email, daftarMenu};
        pelangganTableModel.addRow(rowData);
        pelangganTableModel.fireTableDataChanged(); // Pembaruan tabel setelah menambah data
    }

    private void editData(int rowIndex, String id, String nama, String alamat, String nomorTelepon, String email, String daftarMenu) {
        pelangganTableModel.setValueAt(id, rowIndex, 0);
        pelangganTableModel.setValueAt(nama, rowIndex, 1);
        pelangganTableModel.setValueAt(alamat, rowIndex, 2);
        pelangganTableModel.setValueAt(nomorTelepon, rowIndex, 3);
        pelangganTableModel.setValueAt(email, rowIndex, 4);
        pelangganTableModel.setValueAt(daftarMenu, rowIndex, 5);
        pelangganTableModel.fireTableDataChanged(); // Pembaruan tabel setelah mengedit data
    }

    private void reset() {
        tb_id.setText("");
        tb_nama.setText("");
        tb_alamat.setText("");
        tb_nomorTelepon.setText("");
        tb_email.setText("");
        daftarMenuTextField.setText("");
        tambahMenuTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form_pelanggan();
            }
        });
    }
}