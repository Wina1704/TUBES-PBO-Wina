import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_karyawan extends JFrame {
    private JTextField tb_namaKaryawan;
    private JTextField tb_alamatKaryawan;
    private JTextField tb_nomorTelepon;
    private JTextField tb_email;
    private JTextField tb_posisi;
    private JTextField tb_gaji;
    private JButton btn_simpan;
    private JButton btn_reset;
    private JTable karyawanTable;
    private DefaultTableModel karyawanTableModel;
    private JPanel karyawan_panel;

    public form_karyawan() {
        // Set up the frame
        setTitle("Form Karyawan");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600); // Sesuaikan ukuran form

        // Initialize components
        tb_namaKaryawan = new JTextField(20);
        tb_alamatKaryawan = new JTextField(20);
        tb_nomorTelepon = new JTextField(20);
        tb_email = new JTextField(20);
        tb_posisi = new JTextField(20);
        tb_gaji = new JTextField(20);
        btn_simpan = new JButton("Simpan");
        btn_reset = new JButton("Reset");

        karyawanTableModel = new DefaultTableModel();
        karyawanTableModel.addColumn("Nama Karyawan");
        karyawanTableModel.addColumn("Alamat Karyawan");
        karyawanTableModel.addColumn("Nomor Telepon");
        karyawanTableModel.addColumn("Email");
        karyawanTableModel.addColumn("Posisi");
        karyawanTableModel.addColumn("Gaji");

        karyawanTable = new JTable(karyawanTableModel);

        // Set layout
        JPanel karyawanPanel = new JPanel();
        karyawanPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        karyawanPanel.add(new JLabel("Nama Karyawan:"), gbc);

        gbc.gridx = 1;
        karyawanPanel.add(tb_namaKaryawan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        karyawanPanel.add(new JLabel("Alamat Karyawan:"), gbc);

        gbc.gridx = 1;
        karyawanPanel.add(tb_alamatKaryawan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        karyawanPanel.add(new JLabel("Nomor Telepon:"), gbc);

        gbc.gridx = 1;
        karyawanPanel.add(tb_nomorTelepon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        karyawanPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        karyawanPanel.add(tb_email, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        karyawanPanel.add(new JLabel("Posisi:"), gbc);

        gbc.gridx = 1;
        karyawanPanel.add(tb_posisi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        karyawanPanel.add(new JLabel("Gaji:"), gbc);

        gbc.gridx = 1;
        karyawanPanel.add(tb_gaji, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        karyawanPanel.add(btn_simpan, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        karyawanPanel.add(btn_reset, gbc);

        // Add table to the layout
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 8;
        karyawanPanel.add(new JScrollPane(karyawanTable), gbc);

        // Add action listeners
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

        // Add panel to the frame
        add(karyawanPanel);

        // Display the frame
        setVisible(true);
    }

    private void simpanData() {
        // Implementasi penyimpanan data karyawan
        String namaKaryawan = tb_namaKaryawan.getText();
        String alamatKaryawan = tb_alamatKaryawan.getText();
        String nomorTelepon = tb_nomorTelepon.getText();
        String email = tb_email.getText();
        String posisi = tb_posisi.getText();
        double gaji = Double.parseDouble(tb_gaji.getText());

        // Add data to the table
        karyawanTableModel.addRow(new Object[]{namaKaryawan, alamatKaryawan, nomorTelepon, email, posisi, gaji});

        // Simpan data ke database atau lakukan operasi lainnya
        JOptionPane.showMessageDialog(this,
                "Data karyawan berhasil disimpan:\n" +
                        "Nama Karyawan: " + namaKaryawan + "\n" +
                        "Alamat Karyawan: " + alamatKaryawan + "\n" +
                        "Nomor Telepon: " + nomorTelepon + "\n" +
                        "Email: " + email + "\n" +
                        "Posisi: " + posisi + "\n" +
                        "Gaji: " + gaji,
                "Data Tersimpan",
                JOptionPane.INFORMATION_MESSAGE);

        // Reset data after saving
        resetData();
    }

    private void resetData() {
        // Reset nilai di setiap JTextField
        tb_namaKaryawan.setText("");
        tb_alamatKaryawan.setText("");
        tb_nomorTelepon.setText("");
        tb_email.setText("");
        tb_posisi.setText("");
        tb_gaji.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form_karyawan();
            }
        });
    }
}
