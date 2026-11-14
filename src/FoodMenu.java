
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodMenu extends JFrame {

    private JTextField txtFoodId;
    private JTextField txtFoodName;
    private JTextField txtPrice;
    private JTextField txtQuantity;

    private JComboBox<String> categoryBox;

    private JTable foodTable;
    private DefaultTableModel tableModel;

    public FoodMenu() {

        setTitle("Canteen Management System - Food Menu");
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

        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(new Color(245, 246, 250));

        JLabel title = new JLabel("FOOD MENU MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(50, 45, 190));
        title.setBorder(new EmptyBorder(20, 30, 10, 20));

        main.add(title, BorderLayout.NORTH);

        // ================= FORM =================

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createLineBorder(
                new Color(220, 220, 220)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Food ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Food ID"), gbc);

        txtFoodId = new JTextField();
        txtFoodId.setPreferredSize(new Dimension(220, 38));

        gbc.gridx = 1;
        form.add(txtFoodId, gbc);

        // Food Name
        gbc.gridx = 2;
        form.add(new JLabel("Food Name"), gbc);

        txtFoodName = new JTextField();
        txtFoodName.setPreferredSize(new Dimension(220, 38));

        gbc.gridx = 3;
        form.add(txtFoodName, gbc);

        // Category
        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(new JLabel("Category"), gbc);

        categoryBox = new JComboBox<>(new String[]{
                "Snacks",
                "Breakfast",
                "Lunch",
                "Beverages",
                "Fast Food",
                "Dessert"
        });

        categoryBox.setPreferredSize(new Dimension(220, 38));

        gbc.gridx = 1;
        form.add(categoryBox, gbc);

        // Price
        gbc.gridx = 2;
        form.add(new JLabel("Price"), gbc);

        txtPrice = new JTextField();
        txtPrice.setPreferredSize(new Dimension(220, 38));

        gbc.gridx = 3;
        form.add(txtPrice, gbc);

        // Quantity
        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(new JLabel("Quantity"), gbc);

        txtQuantity = new JTextField();
        txtQuantity.setPreferredSize(new Dimension(220, 38));

        gbc.gridx = 1;
        form.add(txtQuantity, gbc);

        // Buttons
        JButton btnAdd = new JButton("ADD FOOD");
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnDelete = new JButton("DELETE");
        JButton btnClear = new JButton("CLEAR");

        gbc.gridx = 2;
        gbc.gridy = 2;
        form.add(btnAdd, gbc);

        gbc.gridx = 3;
        form.add(btnUpdate, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        form.add(btnDelete, gbc);

        gbc.gridx = 3;
        form.add(btnClear, gbc);

        // ================= TABLE =================

        String[] columns = {
                "Food ID",
                "Food Name",
                "Category",
                "Price",
                "Quantity"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        foodTable = new JTable(tableModel);
        foodTable.setRowHeight(30);
        foodTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(foodTable);

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.setBackground(new Color(245, 246, 250));
        center.setBorder(new EmptyBorder(10, 30, 20, 30));

        center.add(form, BorderLayout.NORTH);
        center.add(scrollPane, BorderLayout.CENTER);

        main.add(center, BorderLayout.CENTER);
        add(main, BorderLayout.CENTER);

        // ================= FOOTER =================

        JPanel footer = new JPanel();
        footer.setBackground(new Color(24, 32, 58));
        footer.setPreferredSize(new Dimension(100, 45));

        JButton back = new JButton("BACK");

        JLabel footerText = new JLabel(
                "© 2026 Poornima Institute of Engineering & Technology | "
                        + "Canteen Management System"
        );

        footerText.setForeground(Color.WHITE);

        footer.add(footerText);
        footer.add(back);

        add(footer, BorderLayout.SOUTH);

        // ================= LOAD DATABASE DATA =================

        loadFoodData();

        // ================= ADD FOOD =================

        btnAdd.addActionListener(e -> addFood());

        // ================= UPDATE FOOD =================

        btnUpdate.addActionListener(e -> updateFood());

        // ================= DELETE FOOD =================

        btnDelete.addActionListener(e -> deleteFood());

        // ================= CLEAR =================

        btnClear.addActionListener(e -> clearFields());

        // ================= TABLE CLICK =================

        foodTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = foodTable.getSelectedRow();

                if (row >= 0) {

                    int modelRow = foodTable.convertRowIndexToModel(row);

                    txtFoodId.setText(
                            tableModel.getValueAt(modelRow, 0).toString()
                    );

                    txtFoodName.setText(
                            tableModel.getValueAt(modelRow, 1).toString()
                    );

                    categoryBox.setSelectedItem(
                            tableModel.getValueAt(modelRow, 2).toString()
                    );

                    txtPrice.setText(
                            tableModel.getValueAt(modelRow, 3).toString()
                    );

                    txtQuantity.setText(
                            tableModel.getValueAt(modelRow, 4).toString()
                    );
                }
            }
        });

        // ================= BACK =================

        back.addActionListener(e -> {
            dispose();
            new Dashboard();
        });

        setVisible(true);
    }

    // ================= LOAD FOOD DATA =================

    private void loadFoodData() {

        tableModel.setRowCount(0);

        String sql = "SELECT food_id, food_name, category, price, quantity "
                + "FROM food_items ORDER BY food_id";

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

                while (rs.next()) {

                    tableModel.addRow(new Object[]{
                            rs.getInt("food_id"),
                            rs.getString("food_name"),
                            rs.getString("category"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    });
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load food records!\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ================= VALIDATE INPUT =================

    private boolean validateFields() {

        if (txtFoodId.getText().trim().isEmpty()
                || txtFoodName.getText().trim().isEmpty()
                || txtPrice.getText().trim().isEmpty()
                || txtQuantity.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return false;
        }

        try {

            int id = Integer.parseInt(txtFoodId.getText().trim());
            double price = Double.parseDouble(txtPrice.getText().trim());
            int quantity = Integer.parseInt(txtQuantity.getText().trim());

            if (id <= 0 || price < 0 || quantity < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "ID must be positive. Price and quantity cannot be negative."
                );
                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Food ID and Quantity must be whole numbers.\n"
                            + "Price must be a valid number."
            );

            return false;
        }

        return true;
    }

    // ================= ADD FOOD =================

    private void addFood() {

        if (!validateFields()) {
            return;
        }

        String sql = "INSERT INTO food_items "
                + "(food_id, food_name, category, price, quantity) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!"
                );
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, Integer.parseInt(txtFoodId.getText().trim()));
                ps.setString(2, txtFoodName.getText().trim());
                ps.setString(3, categoryBox.getSelectedItem().toString());
                ps.setDouble(4, Double.parseDouble(txtPrice.getText().trim()));
                ps.setInt(5, Integer.parseInt(txtQuantity.getText().trim()));

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Food added successfully!"
            );

            loadFoodData();
            clearFields();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add food!\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ================= UPDATE FOOD =================

    private void updateFood() {

        int row = foodTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Select a food item first!"
            );
            return;
        }

        if (!validateFields()) {
            return;
        }

        int modelRow = foodTable.convertRowIndexToModel(row);

        int oldFoodId = Integer.parseInt(
                tableModel.getValueAt(modelRow, 0).toString()
        );

        String sql = "UPDATE food_items SET food_id = ?, "
                + "food_name = ?, category = ?, price = ?, quantity = ? "
                + "WHERE food_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!"
                );
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, Integer.parseInt(txtFoodId.getText().trim()));
                ps.setString(2, txtFoodName.getText().trim());
                ps.setString(3, categoryBox.getSelectedItem().toString());
                ps.setDouble(4, Double.parseDouble(txtPrice.getText().trim()));
                ps.setInt(5, Integer.parseInt(txtQuantity.getText().trim()));
                ps.setInt(6, oldFoodId);

                int result = ps.executeUpdate();

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Food updated successfully!"
                    );

                    loadFoodData();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Food record not found!"
                    );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update food!\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ================= DELETE FOOD =================

    private void deleteFood() {

        int row = foodTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Select a food item first!"
            );
            return;
        }

        int modelRow = foodTable.convertRowIndexToModel(row);

        int foodId = Integer.parseInt(
                tableModel.getValueAt(modelRow, 0).toString()
        );

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this food item?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM food_items WHERE food_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!"
                );
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, foodId);

                int result = ps.executeUpdate();

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Food deleted successfully!"
                    );

                    loadFoodData();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Food record not found!"
                    );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete food!\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ================= CLEAR FIELDS =================

    private void clearFields() {

        txtFoodId.setText("");
        txtFoodName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");

        categoryBox.setSelectedIndex(0);

        foodTable.clearSelection();
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new FoodMenu());
    }
}
