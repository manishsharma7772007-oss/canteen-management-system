import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle(
                "Canteen Management System - Admin Dashboard"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setLayout(
                new BorderLayout()
        );


        // ================= HEADER =================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(Color.WHITE);

        header.setBorder(
                new EmptyBorder(
                        8, 15, 8, 15
                )
        );


        ImageIcon logo =
                new ImageIcon(
                        "images/logo.png"
                );

        Image logoImage =
                logo.getImage()
                        .getScaledInstance(
                                420,
                                105,
                                Image.SCALE_SMOOTH
                        );

        JLabel logoLabel =
                new JLabel(
                        new ImageIcon(
                                logoImage
                        )
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

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(
                new Color(
                        245,
                        246,
                        250
                )
        );


        // ================= SIDEBAR =================

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        240,
                        0
                )
        );

        sidebar.setBackground(
                new Color(
                        24,
                        32,
                        58
                )
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel menu =
                new JLabel(
                        "ADMIN MENU"
                );

        menu.setForeground(
                Color.WHITE
        );

        menu.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        10
                )
        );

        sidebar.add(menu);


        JButton foodMenu =
                createButton(
                        "Food Menu"
                );

        JButton orders =
                createButton(
                        "Orders"
                );

        JButton students =
                createButton(
                        "Students"
                );

        JButton records =
                createButton(
                        "View Records"
                );

        JButton logout =
                createButton(
                        "Logout"
                );


        sidebar.add(foodMenu);
        sidebar.add(orders);
        sidebar.add(students);
        sidebar.add(records);

        sidebar.add(
                Box.createVerticalGlue()
        );

        sidebar.add(logout);


        main.add(
                sidebar,
                BorderLayout.WEST
        );


        // ================= CONTENT =================

        JPanel content =
                new JPanel(
                        new BorderLayout()
                );

        content.setBackground(
                new Color(
                        245,
                        246,
                        250
                )
        );


        JLabel title =
                new JLabel(
                        "ADMIN DASHBOARD"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                new Color(
                        50,
                        45,
                        190
                )
        );

        title.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        20,
                        20
                )
        );

        content.add(
                title,
                BorderLayout.NORTH
        );


        // ================= CARDS =================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        cards.setBackground(
                new Color(
                        245,
                        246,
                        250
                )
        );

        cards.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        30,
                        30
                )
        );


        cards.add(
                createCard(
                        "TOTAL FOOD ITEMS",
                        "25"
                )
        );

        cards.add(
                createCard(
                        "TODAY'S ORDERS",
                        "48"
                )
        );

        cards.add(
                createCard(
                        "TOTAL STUDENTS",
                        "350"
                )
        );

        cards.add(
                createCard(
                        "PENDING ORDERS",
                        "12"
                )
        );


        content.add(
                cards,
                BorderLayout.CENTER
        );


        main.add(
                content,
                BorderLayout.CENTER
        );

        add(
                main,
                BorderLayout.CENTER
        );


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
                        45
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

        footer.add(
                footerText
        );

        add(
                footer,
                BorderLayout.SOUTH
        );


        // ================= FOOD MENU =================

        foodMenu.addActionListener(e -> {

            dispose();

            new FoodMenu();

        });


        // ================= LOGOUT =================

        logout.addActionListener(e -> {

            dispose();

            new Login();

        });


        setVisible(true);
    }


    // ================= BUTTON =================

    private JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        240,
                        50
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        10,
                        10
                )
        );

        button.setBackground(
                new Color(
                        24,
                        32,
                        58
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(false);

        return button;
    }


    // ================= CARD =================

    private JPanel createCard(
            String title,
            String number
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );


        JLabel numberLabel =
                new JLabel(number);

        numberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        40
                )
        );

        numberLabel.setForeground(
                new Color(
                        50,
                        45,
                        190
                )
        );


        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                numberLabel,
                BorderLayout.CENTER
        );


        return card;
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard();
        });
    }
}
