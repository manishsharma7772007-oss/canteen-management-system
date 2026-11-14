import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResetPassword extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JComboBox<String> roleBox;

    public ResetPassword() {

        setTitle("Canteen Management System - Reset Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());


        // ================= HEADER =================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(Color.WHITE);

        header.setBorder(
                new EmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );


        ImageIcon originalLogo =
                new ImageIcon("images/logo.png");

        Image logoImage =
                originalLogo.getImage()
                        .getScaledInstance(
                                420,
                                105,
                                Image.SCALE_SMOOTH
                        );

        JLabel logoLabel =
                new JLabel(
                        new ImageIcon(logoImage)
                );

        logoLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        header.add(
                logoLabel,
                BorderLayout.CENTER
        );

        add(
                header,
                BorderLayout.NORTH
        );


        // ================= MAIN =================

        JPanel mainPanel =
                new JPanel(new GridBagLayout());

        mainPanel.setBackground(Color.WHITE);


        JPanel resetPanel =
                new JPanel(new GridBagLayout());

        resetPanel.setBackground(Color.WHITE);

        resetPanel.setBorder(
                new EmptyBorder(
                        25,
                        45,
                        25,
                        45
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        6,
                        0,
                        6,
                        0
                );


        // ================= TITLE =================

        JLabel title =
                new JLabel(
                        "RESET PASSWORD"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(
                        50,
                        45,
                        190
                )
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        20,
                        0
                );

        resetPanel.add(
                title,
                gbc
        );


        // ================= USERNAME =================

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        gbc.gridy = 1;

        resetPanel.add(
                usernameLabel,
                gbc
        );


        txtUsername =
                new JTextField();

        txtUsername.setPreferredSize(
                new Dimension(
                        400,
                        42
                )
        );

        gbc.gridy = 2;

        resetPanel.add(
                txtUsername,
                gbc
        );


        // ================= ROLE =================

        JLabel roleLabel =
                new JLabel("Login As");

        roleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        gbc.gridy = 3;

        resetPanel.add(
                roleLabel,
                gbc
        );


        roleBox =
                new JComboBox<>(
                        new String[]{
                                "Student",
                                "Admin"
                        }
                );

        roleBox.setPreferredSize(
                new Dimension(
                        400,
                        42
                )
        );

        gbc.gridy = 4;

        resetPanel.add(
                roleBox,
                gbc
        );


        // ================= NEW PASSWORD =================

        JLabel newPasswordLabel =
                new JLabel(
                        "New Password"
                );

        newPasswordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        gbc.gridy = 5;

        resetPanel.add(
                newPasswordLabel,
                gbc
        );


        txtNewPassword =
                new JPasswordField();

        txtNewPassword.setPreferredSize(
                new Dimension(
                        400,
                        42
                )
        );

        gbc.gridy = 6;

        resetPanel.add(
                txtNewPassword,
                gbc
        );


        // ================= CONFIRM PASSWORD =================

        JLabel confirmLabel =
                new JLabel(
                        "Confirm New Password"
                );

        confirmLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        gbc.gridy = 7;

        resetPanel.add(
                confirmLabel,
                gbc
        );


        txtConfirmPassword =
                new JPasswordField();

        txtConfirmPassword.setPreferredSize(
                new Dimension(
                        400,
                        42
                )
        );

        gbc.gridy = 8;

        resetPanel.add(
                txtConfirmPassword,
                gbc
        );


        // ================= RESET BUTTON =================

        JButton btnReset =
                new JButton(
                        "RESET PASSWORD"
                );

        btnReset.setPreferredSize(
                new Dimension(
                        400,
                        48
                )
        );

        btnReset.setBackground(
                new Color(
                        50,
                        45,
                        190
                )
        );

        btnReset.setForeground(
                Color.WHITE
        );

        btnReset.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        btnReset.setFocusPainted(false);

        gbc.gridy = 9;

        gbc.insets =
                new Insets(
                        18,
                        0,
                        6,
                        0
                );

        resetPanel.add(
                btnReset,
                gbc
        );


        // ================= BACK BUTTON =================

        JButton btnBack =
                new JButton(
                        "BACK TO LOGIN"
                );

        btnBack.setPreferredSize(
                new Dimension(
                        400,
                        45
                )
        );

        btnBack.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        btnBack.setFocusPainted(false);

        gbc.gridy = 10;

        resetPanel.add(
                btnBack,
                gbc
        );


        mainPanel.add(resetPanel);

        add(
                mainPanel,
                BorderLayout.CENTER
        );


        // ================= RESET LOGIC =================

        btnReset.addActionListener(e -> {

            String username =
                    txtUsername.getText().trim();

            String role =
                    roleBox.getSelectedItem()
                            .toString();

            String newPassword =
                    new String(
                            txtNewPassword
                                    .getPassword()
                    );

            String confirmPassword =
                    new String(
                            txtConfirmPassword
                                    .getPassword()
                    );


            // Empty fields
            if (username.isEmpty()
                    || newPassword.isEmpty()
                    || confirmPassword.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields!",
                        "Reset Password",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // Password match
            if (!newPassword.equals(
                    confirmPassword)) {

                JOptionPane.showMessageDialog(
                        this,
                        "New Password and Confirm Password do not match!",
                        "Reset Password",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // Minimum password length
            if (newPassword.length() < 4) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password must contain at least 4 characters!",
                        "Reset Password",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // ================= ADMIN =================

            if (role.equals("Admin")) {

                if (!username.equals("admin")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Admin username not found!",
                            "Reset Password",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                // Change Admin Password
                Login.adminPassword =
                        newPassword;
            }


            // ================= STUDENT =================

            else {

                if (!username.equals("student")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student username not found!",
                            "Reset Password",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                // Change Student Password
                Login.studentPassword =
                        newPassword;
            }


            // ================= SUCCESS =================

            JOptionPane.showMessageDialog(
                    this,
                    "Password reset successfully!\n\n"
                            + "Username: "
                            + username
                            + "\nRole: "
                            + role
                            + "\n\n"
                            + "You can now login with your new password.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            dispose();

            new Login();
        });


        // ================= BACK TO LOGIN =================

        btnBack.addActionListener(e -> {

            dispose();

            new Login();
        });


        // ================= FOOTER =================

        JPanel footer =
                new JPanel();

        footer.setBackground(
                new Color(
                        24,
                        32,
                        58
                )
        );

        footer.setPreferredSize(
                new Dimension(
                        100,
                        50
                )
        );


        JLabel footerText =
                new JLabel(
                        "© 2026 Poornima Institute of Engineering & Technology | "
                                + "Canteen Management System"
                );

        footerText.setForeground(
                Color.WHITE
        );

        footerText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        footer.add(footerText);

        add(
                footer,
                BorderLayout.SOUTH
        );


        setVisible(true);
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new ResetPassword()
        );
    }
}