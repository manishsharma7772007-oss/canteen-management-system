
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDashboard extends JFrame {

    public StudentDashboard() {

        setTitle("Canteen Management System - Student Dashboard");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(Color.WHITE);

        header.setBorder(new EmptyBorder(8, 15, 8, 15));

        ImageIcon logo = new ImageIcon("images/logo.png");

        Image logoImage = logo.getImage().getScaledInstance(
                420, 105, Image.SCALE_SMOOTH
        );

        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        // ================= MAIN =================

        JPanel main = new JPanel(new BorderLayout());

        main.setBackground(new Color(245, 246, 250));

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(new Dimension(240, 0));

        sidebar.setBackground(new Color(24, 32, 58));

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel menu = new JLabel("STUDENT MENU");

        menu.setForeground(Color.WHITE);

        menu.setFont(new Font("Arial", Font.BOLD, 20));

        menu.setBorder(new EmptyBorder(25, 25, 25, 10));

        sidebar.add(menu);

        JButton foodMenu = createButton("View Food Menu");

        JButton myOrders = createButton("My Orders");

        JButton logout = createButton("Logout");

        sidebar.add(foodMenu);

        sidebar.add(myOrders);

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(logout);

        main.add(sidebar, BorderLayout.WEST);

        // ================= CONTENT =================

        JPanel content = new JPanel(new GridBagLayout());

        content.setBackground(new Color(245, 246, 250));

        JPanel welcomePanel = new JPanel();

        welcomePanel.setBackground(Color.WHITE);

        welcomePanel.setBorder(
                new EmptyBorder(40, 70, 40, 70)
        );

        JLabel welcome = new JLabel("WELCOME STUDENT");

        welcome.setFont(new Font("Arial", Font.BOLD, 32));

        welcome.setForeground(new Color(50, 45, 190));

        JLabel message = new JLabel(
                "Select View Food Menu to see today's available food."
        );

        message.setFont(new Font("Arial", Font.PLAIN, 16));

        welcomePanel.add(welcome);

        welcomePanel.add(message);

        content.add(welcomePanel);

        main.add(content, BorderLayout.CENTER);

        add(main, BorderLayout.CENTER);

        // ================= FOOTER =================

        JPanel footer = new JPanel();

        footer.setBackground(new Color(24, 32, 58));

        footer.setPreferredSize(new Dimension(100, 45));

        JLabel footerText = new JLabel(
                "© 2026 Poornima Institute of Engineering & Technology | "
                        + "Canteen Management System"
        );

        footerText.setForeground(Color.WHITE);

        footerText.setFont(new Font("Arial", Font.PLAIN, 13));

        footer.add(footerText);

        add(footer, BorderLayout.SOUTH);

        // ================= FOOD MENU =================

        foodMenu.addActionListener(e -> showFoodMenu());

        // ================= ORDERS =================

        myOrders.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "My Orders page will be connected next."
            );

        });

        // ================= LOGOUT =================

        logout.addActionListener(e -> {

            dispose();

            new Login();

        });

        setVisible(true);
    }

    // ================= FOOD MENU =================

    private void showFoodMenu() {

        String sql = "SELECT food_name, category, price, quantity "
                + "FROM food_items WHERE quantity > 0 "
                + "ORDER BY food_id";

        StringBuilder food = new StringBuilder();

        food.append("AVAILABLE FOOD ITEMS\n\n");

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!"
                );

                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                int count = 1;

                while (rs.next()) {

                    String name = rs.getString("food_name");

                    String category = rs.getString("category");

                    double price = rs.getDouble("price");

                    int quantity = rs.getInt("quantity");

                    food.append(count++)
                            .append(". ")
                            .append(name)
                            .append(" (")
                            .append(category)
                            .append(") - ₹")
                            .append(String.format("%.2f", price))
                            .append(" | Available: ")
                            .append(quantity)
                            .append("\n");
                }

                if (count == 1) {

                    food.append(
                            "No food items available currently."
                    );

                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    food.toString(),
                    "Food Menu",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load food menu from database!\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= BUTTON =================

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(new Dimension(240, 50));

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setBorder(
                new EmptyBorder(10, 25, 10, 10)
        );

        button.setBackground(new Color(24, 32, 58));

        button.setForeground(Color.WHITE);

        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setFocusPainted(false);

        return button;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new StudentDashboard()
        );
    }
}
