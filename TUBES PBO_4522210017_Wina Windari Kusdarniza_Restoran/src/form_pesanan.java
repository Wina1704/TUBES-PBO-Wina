import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class form_pesanan extends JFrame implements Pesanan{
    private JComboBox<String> daftarMejaComboBox;
    private JTextField tb_id;
    private JTextField tb_nama;
    private JTextField daftarMenuTextField;
    private JTextField tambahMenuTextField;
    private JTextField jumlahMenuTextField;
    private JTextArea informasiTambahanTextArea;
    private JButton btn_simpan;
    private JButton btn_reset;
    private JButton btn_tambahMenu;
    private JPanel pesanan_panel;
    private JTable recentOrdersTable;
    private DefaultTableModel recentOrdersTableModel;
    private DefaultListModel<String> menuListModel;
    private int idCounter = 1;  // Counter for generating ID
    private PesananOnline pesananOnline;

    public form_pesanan() {
        recentOrdersTableModel = new DefaultTableModel();
        menuListModel = new DefaultListModel<>();

        setTitle("Form Pesanan");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);

        daftarMejaComboBox = new JComboBox<>(new String[]{"Meja 1", "Meja 2", "Meja 3", "Meja 4"});
        tb_id = new JTextField(10);
        tb_id.setVisible(false);
        tb_nama = new JTextField(25);
        daftarMenuTextField = new JTextField(20);
        daftarMenuTextField.setEditable(false);
        tambahMenuTextField = new JTextField(20);
        jumlahMenuTextField = new JTextField(5);
        informasiTambahanTextArea = new JTextArea(5, 20);
        btn_simpan = new JButton("Simpan");
        btn_reset = new JButton("Reset");
        btn_tambahMenu = new JButton("Tambah Menu");

        recentOrdersTable = new JTable(recentOrdersTableModel);
        recentOrdersTableModel.addColumn("ID");
        recentOrdersTableModel.addColumn("Nama");
        recentOrdersTableModel.addColumn("Meja");
        recentOrdersTableModel.addColumn("Daftar Menu");
        recentOrdersTableModel.addColumn("Jumlah Menu");
        recentOrdersTableModel.addColumn("Informasi Tambahan");

        JPanel pesananPanel = new JPanel();
        pesananPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        pesananPanel.add(new JLabel("Nomor Meja:"), gbc);

        gbc.gridx = 1;
        pesananPanel.add(daftarMejaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pesananPanel.add(new JLabel("ID Pesanan:"), gbc);

        gbc.gridx = 1;
        pesananPanel.add(tb_id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pesananPanel.add(new JLabel("Nama Pelanggan:"), gbc);

        gbc.gridx = 1;
        pesananPanel.add(tb_nama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pesananPanel.add(new JLabel("Daftar Menu:"), gbc);

        gbc.gridx = 1;
        pesananPanel.add(daftarMenuTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pesananPanel.add(tambahMenuTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pesananPanel.add(btn_tambahMenu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pesananPanel.add(new JLabel("Jumlah Menu:"), gbc);

        gbc.gridx = 1;
        pesananPanel.add(jumlahMenuTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        pesananPanel.add(new JLabel("Informasi Tambahan:"), gbc);

        gbc.gridx = 1;
        pesananPanel.add(new JScrollPane(informasiTambahanTextArea), gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        pesananPanel.add(btn_simpan, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 9;
        pesananPanel.add(new JScrollPane(recentOrdersTable), gbc);

        btn_simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();
            }
        });

        btn_tambahMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahMenu();
            }
        });

        recentOrdersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = recentOrdersTable.getSelectedRow();
                    if (selectedRow != -1) {
                        tb_id.setText(recentOrdersTableModel.getValueAt(selectedRow, 0).toString());
                        tb_nama.setText(recentOrdersTableModel.getValueAt(selectedRow, 1).toString());

                        // Set nilai pada JComboBox
                        String mejaTerpilih = recentOrdersTableModel.getValueAt(selectedRow, 2).toString();
                        daftarMejaComboBox.setSelectedItem(mejaTerpilih);

                        // Lanjutkan dengan mengisi komponen-komponen lainnya sesuai kebutuhan
                        daftarMenuTextField.setText(recentOrdersTableModel.getValueAt(selectedRow, 3).toString());
                        jumlahMenuTextField.setText(recentOrdersTableModel.getValueAt(selectedRow, 4).toString());
                        informasiTambahanTextArea.setText(recentOrdersTableModel.getValueAt(selectedRow, 5).toString());
                    }
                }
            }
        });

        add(pesananPanel);

        setVisible(true);
    }

    private void simpanData() {
        String mejaTerpilih = (String) daftarMejaComboBox.getSelectedItem();
        String idPesanan = generateNextID();
        String namaPelanggan = tb_nama.getText();
        int jumlahMenu = Integer.parseInt(jumlahMenuTextField.getText());
        String informasiTambahan = informasiTambahanTextArea.getText();
        String daftarMenu = daftarMenuTextField.getText();

        // Set nilai ID yang dihasilkan ke tb_id
        tb_id.setText(idPesanan);

        // Update recent orders table
        updateRecentOrders(idPesanan, namaPelanggan, mejaTerpilih, daftarMenu, jumlahMenu, informasiTambahan);
        // Kosongkan JTextField setelah menyimpan
        resetData();

        JOptionPane.showMessageDialog(this,
                "Data pesanan berhasil disimpan:\n" +
                        "ID Pesanan: " + tb_id.getText() + "\n" +
                        "Nama Pelanggan: " + tb_nama.getText() + "\n" +
                        "Nomor Meja: " + daftarMejaComboBox.getSelectedItem() + "\n" +
                        "Daftar Menu: " + daftarMenuTextField.getText() + "\n" +
                        "Jumlah Menu: " + jumlahMenuTextField.getText() + "\n" +
                        "Informasi Tambahan: " + informasiTambahanTextArea.getText(),
                "Data Tersimpan",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String getIdPesanan() {
        return pesananOnline.getIdPesanan();
    }

    @Override
    public String getNamaPelanggan() {
        return pesananOnline.getNamaPelanggan();
    }

    @Override
    public String getNomorMeja() {
        return pesananOnline.getNomorMeja();
    }

    @Override
    public String getDaftarMenu() {
        return pesananOnline.getDaftarMenu();
    }

    @Override
    public int getJumlahMenu() {
        return pesananOnline.getJumlahMenu();
    }

    @Override
    public String getInformasiTambahan() {
        return pesananOnline.getInformasiTambahan();
    }

    @Override
    public void setIdPesanan(String idPesanan) {
        pesananOnline.setIdPesanan(idPesanan);
    }

    @Override
    public void setNamaPelanggan(String namaPelanggan) {
        pesananOnline.setNamaPelanggan(namaPelanggan);
    }

    @Override
    public void setNomorMeja(String nomorMeja) {
        pesananOnline.setNomorMeja(nomorMeja);
    }

    @Override
    public void setDaftarMenu(String daftarMenu) {
        pesananOnline.setDaftarMenu(daftarMenu);
    }

    @Override
    public void setJumlahMenu(int jumlahMenu) {
        pesananOnline.setJumlahMenu(jumlahMenu);
    }

    @Override
    public void setInformasiTambahan(String informasiTambahan) {
        pesananOnline.setInformasiTambahan(informasiTambahan);
    }

    private void updateRecentOrders(String idPesanan, String namaPelanggan, String mejaTerpilih,
                                    String daftarMenu, int jumlahMenu, String informasiTambahan) {
        recentOrdersTableModel.addRow(new Object[]{idPesanan, namaPelanggan, mejaTerpilih, daftarMenu, jumlahMenu, informasiTambahan});
    }

    private void resetData() {
        daftarMejaComboBox.setSelectedIndex(0);
        tb_id.setText("");
        tb_nama.setText("");
        menuListModel.clear();
        daftarMenuTextField.setText("");
        tambahMenuTextField.setText("");
        jumlahMenuTextField.setText("");
        informasiTambahanTextArea.setText("");
    }

    private void tambahMenu() {
        String menuBaru = tambahMenuTextField.getText();

        if (!menuListModel.contains(menuBaru)) {
            menuListModel.addElement(menuBaru);
        }

        // Perbarui daftarMenuTextField
        updateDaftarMenuTextField();

        tambahMenuTextField.setText("");
    }

    private void updateDaftarMenuTextField() {
        StringBuilder daftarMenu = new StringBuilder();
        for (int i = 0; i < menuListModel.size(); i++) {
            daftarMenu.append(menuListModel.getElementAt(i));
            if (i < menuListModel.size() - 1) {
                daftarMenu.append(", ");
            }
        }
        daftarMenuTextField.setText(daftarMenu.toString());
    }

    private String generateNextID() {
        // Menghasilkan ID berikutnya dalam format "PSN001", "PSN002", dst.
        String formattedID = String.format("PSN%03d", idCounter);
        idCounter++;
        return formattedID;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new form_pesanan());
    }
}
