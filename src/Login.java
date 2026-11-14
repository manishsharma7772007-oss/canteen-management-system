
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> roleBox;
    private JButton btnLogin, btnReset;

    // Temporary variables for existing ResetPassword.java
    public static String adminPassword = "admin123";
    public static String studentPassword = "student123";

    public Login() {

        // ================= WINDOW =================
        setTitle("Canteen Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(8, 15, 8, 15));

        ImageIcon originalLogo =
                new ImageIcon("images/logo.png");

        Image logoImage =
                originalLogo.getImage().getScaledInstance(
                        420, 105, Image.SCALE_SMOOTH
                );

        JLabel logoLabel =
                new JLabel(new ImageIcon(logoImage));

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(logoLabel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        // ================= MAIN PANEL =================
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(Color.WHITE);

        // ================= LEFT SIDE =================
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        ImageIcon originalCanteen =
                new ImageIcon("images/canteen.jpg");

        Image canteenImage =
                originalCanteen.getImage().getScaledInstance(
                        500, 400, Image.SCALE_SMOOTH
                );

        JLabel canteenLabel =
                new JLabel(new ImageIcon(canteenImage));

        canteenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        canteenLabel.setVerticalAlignment(SwingConstants.CENTER);

        leftPanel.add(canteenLabel, BorderLayout.CENTER);

        // ================= RIGHT SIDE =================
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 35, 7, 35);

        // ================= TITLE =================
        JLabel title =
                new JLabel("CANTEEN MANAGEMENT SYSTEM");

        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(50, 45, 190));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        gbc.insets = new Insets(10, 35, 25, 35);

        rightPanel.add(title, gbc);

        // ================= USERNAME =================
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));

        gbc.gridy = 1;
        gbc.insets = new Insets(7, 35, 5, 35);

        rightPanel.add(usernameLabel, gbc);

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(400, 42));

        gbc.gridy = 2;
        rightPanel.add(txtUsername, gbc);

        // ================= PASSWORD =================
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));

        gbc.gridy = 3;
        rightPanel.add(passwordLabel, gbc);

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(400, 42));

        gbc.gridy = 4;
        rightPanel.add(txtPassword, gbc);

        // ================= LOGIN AS =================
        JLabel roleLabel = new JLabel("Login As");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 15));

        gbc.gridy = 5;
        rightPanel.add(roleLabel, gbc);

        roleBox = new JComboBox<>(
                new String[]{"Student", "Admin"}
        );

        roleBox.setPreferredSize(new Dimension(400, 42));

        gbc.gridy = 6;
        rightPanel.add(roleBox, gbc);

        // ================= LOGIN BUTTON =================
        btnLogin = new JButton("LOGIN");
        btnLogin.setPreferredSize(new Dimension(400, 48));
        btnLogin.setBackground(new Color(50, 45, 190));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setFocusPainted(false);

        gbc.gridy = 7;
        gbc.insets = new Insets(20, 35, 7, 35);

        rightPanel.add(btnLogin, gbc);

        // ================= RESET BUTTON =================
        btnReset = new JButton("RESET");
        btnReset.setPreferredSize(new Dimension(400, 48));
        btnReset.setFont(new Font("Arial", Font.BOLD, 15));
        btnReset.setFocusPainted(false);

        gbc.gridy = 8;
        gbc.insets = new Insets(7, 35, 10, 35);

        rightPanel.add(btnReset, gbc);

        // ================= LOGIN FUNCTION =================
        btnLogin.addActionListener(e -> loginUser());

        // ================= RESET FUNCTION =================
        btnReset.addActionListener(e -> {
            dispose();
            new ResetPassword();
        });

        // ================= ADD PANELS =================
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(new Color(24, 32, 58));
        footer.setPreferredSize(new Dimension(100, 50));

        JLabel footerText = new JLabel(
                "© 2026 Poornima Institute of Engineering & Technology | "
                        + "Canteen Management System"
        );

        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));

        footer.add(footerText);
        add(footer, BorderLayout.SOUTH);

        // ================= SHOW WINDOW =================
        setVisible(true);
    }

    // ================= LOGIN METHOD =================
    private void loginUser() {

        String username = txtUsername.getText().trim();

        String password = new String(
                txtPassword.getPassword()
        );

        String role = roleBox.getSelectedItem().toString();

        // Empty Fields Validation
        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Username and Password!",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // ================= DATABASE AUTHENTICATION =================
        if (UserDAO.authenticate(username, password, role)) {

            JOptionPane.showMessageDialog(
                    this,
                    role + " Login Successful!",
                    "Login Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            // ROLE BASED DASHBOARD
            if (role.equals("Admin")) {
                new Dashboard();
            } else if (role.equals("Student")) {
                new StudentDashboard();
            }

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username, Password or Role!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Login();
        });
    }
}
