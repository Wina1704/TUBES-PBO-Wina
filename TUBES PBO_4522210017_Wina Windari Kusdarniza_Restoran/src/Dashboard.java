import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JButton btn_karyawan;
    private JButton btn_menu;
    private JButton btn_pelanggan;
    private JButton btn_pesanan;
    private JPanel dashboard_panel;
    private JButton btn_exit;

    public Dashboard() {
        btn_karyawan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bukaFormRestoran();
            }
        });

        btn_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bukaFormMenu();
            }
        });

        btn_pelanggan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bukaFormPelanggan();
            }
        });

        btn_pesanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bukaFormPesanan();
            }
        });

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void bukaFormRestoran() {
        setVisible(false);

        form_karyawan formRestoran = new form_karyawan();

        // Add a WindowListener to the form_restoran to show the Dashboard when form_restoran is closed
        formRestoran.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Show the Dashboard when form_restoran is closed
                setVisible(true);
            }
        });
    }

    private void bukaFormMenu() {

        form_menu formMenu = new form_menu();

        // Add a WindowListener to the FormMenu to show the Dashboard when FormMenu is closed
        formMenu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Show the Dashboard when FormMenu is closed
            }
        });
    }

    private void bukaFormPesanan() {

        form_pesanan formPesanan = new form_pesanan();

        // Add a WindowListener to the FormMenu to show the Dashboard when FormMenu is closed
        formPesanan.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Show the Dashboard when FormMenu is closed
            }
        });
    }

    private void bukaFormPelanggan() {

        form_pelanggan formPelanggan = new form_pelanggan();

        // Add a WindowListener to the FormMenu to show the Dashboard when FormMenu is closed
        formPelanggan.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Show the Dashboard when FormMenu is closed
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Set look and feel to the system's look and feel
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Create the JFrame
                JFrame frame = new JFrame("Dashboard");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create an instance of Dashboard and set its panel as the content pane
                Dashboard dashboard = new Dashboard();
                frame.setContentPane(dashboard.dashboard_panel);

                // Set the size of the frame
                frame.setSize(new Dimension(800, 600)); // Adjust the dimensions as needed

                // Center the frame on the screen
                frame.setLocationRelativeTo(null);

                // Display the frame
                frame.setVisible(true);
            }
        });
    }
}
