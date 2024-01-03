import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_restoran extends JFrame {
    private JComboBox<String> daftarMejaComboBox;
    private JTextField daftarMenuTextField;
    private JButton btnSimpan;
    private JPanel resto_panel;
    private JButton btn_kembali;

    public form_restoran() {
        // Set up the frame
        setTitle("Form Restoran");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        // Initialize components
        daftarMejaComboBox = new JComboBox<>(new String[]{"Meja 1", "Meja 2", "Meja 3", "Meja 4"});
        daftarMenuTextField = new JTextField(20);
        btnSimpan = new JButton("Simpan");

        // Set layout
        JPanel restoPanel = new JPanel();
        restoPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        restoPanel.add(new JLabel("Daftar Meja:"), gbc);

        gbc.gridx = 1;
        restoPanel.add(daftarMejaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        restoPanel.add(new JLabel("Daftar Menu:"), gbc);

        gbc.gridx = 1;
        restoPanel.add(daftarMenuTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        restoPanel.add(btnSimpan, gbc);

        // Add action listener to the "Simpan" button
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click event
                simpanData();
            }
        });

        // Add panel to the frame
        add(restoPanel);

        // Display the frame
        setVisible(true);
    }

    private void simpanData() {
        // Get selected item from JComboBox and text from JTextField
        String mejaTerpilih = (String) daftarMejaComboBox.getSelectedItem();
        String daftarMenu = daftarMenuTextField.getText();

        // Perform data saving or processing here
        // For simplicity, let's just show a popup message
        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan:\nMeja: " + mejaTerpilih +
                "\nDaftar Menu: " + daftarMenu);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form_restoran();
            }
        });
    }
}