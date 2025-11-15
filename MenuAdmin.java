import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuAdmin {

    private static final Color COLOR_BACKGROUND = new Color(240, 242, 245);
    private static final Color COLOR_SIDEBAR = new Color(38, 43, 51);
    private static final Color COLOR_SIDEBAR_HOVER = new Color(55, 61, 70);
    private static final Color COLOR_TEXT_LIGHT = new Color(230, 230, 230);
    private static final Color COLOR_TEXT_DARK = new Color(40, 40, 40);
    private static final Color COLOR_ACCENT = new Color(0, 120, 215);
    private static final Color COLOR_DANGER = new Color(220, 53, 69);
    private static final Color COLOR_WHITE = Color.WHITE;
    private static final Color COLOR_BORDER = new Color(220, 220, 220);

    private static final Font FONT_TITLE = new Font("Arial", Font.BOLD, 28);
    private static final Font FONT_HEADER = new Font("Arial", Font.BOLD, 16);
    private static final Font FONT_BODY = new Font("Arial", Font.PLAIN, 14);
    private static final Font FONT_BUTTON = new Font("Arial", Font.BOLD, 14);
    private static final Font FONT_SIDEBAR = new Font("Arial", Font.BOLD, 16);

    private static CardLayout cardLayout;
    private static JPanel contentPanel;

    static class MenuButton extends JPanel {
        public MenuButton(String text, String cardName, JFrame frame, ProjectDatabase db, ProjectMethods methods) {
            super(new FlowLayout(FlowLayout.LEFT, 20, 0));
            setBackground(COLOR_SIDEBAR);
            setBorder(new EmptyBorder(10, 15, 10, 25));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

            JLabel label = new JLabel(text);
            label.setForeground(COLOR_TEXT_LIGHT);
            label.setFont(FONT_SIDEBAR);
            add(label);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    setBackground(COLOR_SIDEBAR_HOVER);
                }

                public void mouseExited(MouseEvent e) {
                    setBackground(COLOR_SIDEBAR);
                }

                public void mouseClicked(MouseEvent e) {
                    if ("LOGOUT".equals(cardName)) {
                        TruckManagementApp.loginMenu(frame, db, methods);
                    } else {
                        cardLayout.show(contentPanel, cardName);
                    }
                }
            });
        }
    }

    private static JLabel createSectionHeader(String text) {
        JLabel header = new JLabel(text);
        header.setFont(FONT_HEADER);
        header.setForeground(COLOR_ACCENT);
        header.setBorder(new EmptyBorder(20, 25, 10, 25));
        return header;
    }

    private static JButton createStyledButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFont(FONT_BUTTON);
        button.setBackground(background);
        button.setForeground(COLOR_WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 25, 12, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private static JPanel createStyledFormPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 20, 20, 20),
                BorderFactory.createLineBorder(COLOR_BORDER)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JSeparator(), gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;

        return panel;
    }

    private static void styleTable(JTable table) {
        table.setFont(FONT_BODY);
        table.setRowHeight(28);
        table.setGridColor(COLOR_BORDER);
        table.setBackground(COLOR_WHITE);
        table.setSelectionBackground(COLOR_ACCENT.brighter());
        table.setSelectionForeground(COLOR_TEXT_DARK);

        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_HEADER);
        header.setBackground(COLOR_SIDEBAR);
        header.setForeground(COLOR_TEXT_LIGHT);
        header.setBorder(BorderFactory.createLineBorder(COLOR_SIDEBAR));
    }

    public void menuAdmin(JFrame frame, ProjectDatabase projectDatabase, ProjectMethods projectMethods) {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(COLOR_BACKGROUND);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(COLOR_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(280, 0));

        sidebar.add(createSectionHeader("TRUCK"));
        sidebar.add(new MenuButton("Show Trucks", "SHOW_TRUCKS", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Add Truck", "ADD_TRUCK", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Remove Truck", "REMOVE_TRUCK", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("View Truck Status", "VIEW_STATUS", frame, projectDatabase, projectMethods));

        sidebar.add(createSectionHeader("BATCH"));
        sidebar.add(new MenuButton("Show Batches", "SHOW_BATCHES", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Add Batch", "ADD_BATCH", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Remove Batch", "REMOVE_BATCH", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Assign Batch to Truck", "ASSIGN_BATCH", frame, projectDatabase, projectMethods));

        sidebar.add(createSectionHeader("PERSONNEL"));
        sidebar.add(new MenuButton("Show Drivers", "SHOW_DRIVERS", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Add Driver", "ADD_DRIVER", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Show Workers", "SHOW_WORKERS", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Add Worker", "ADD_WORKER", frame, projectDatabase, projectMethods));

        sidebar.add(createSectionHeader("WAREHOUSE"));
        sidebar.add(new MenuButton("View Shelves", "VIEW_SHELVES", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Add Shelf Type", "ADD_SHELF_TYPE", frame, projectDatabase, projectMethods));

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(createSectionHeader("SYSTEM"));
        sidebar.add(new MenuButton("Logout", "LOGOUT", frame, projectDatabase, projectMethods));

        contentPanel.add(createWelcomePanel(), "WELCOME_PANEL");
        contentPanel.add(createShowTrucksPanel(frame, projectDatabase, projectMethods), "SHOW_TRUCKS");
        contentPanel.add(createAddTruckPanel(frame, projectDatabase, projectMethods), "ADD_TRUCK");
        contentPanel.add(createRemoveTruckPanel(frame, projectDatabase, projectMethods), "REMOVE_TRUCK");
        contentPanel.add(createShowBatchesPanel(frame, projectDatabase, projectMethods), "SHOW_BATCHES");
        contentPanel.add(createAddBatchPanel(frame, projectDatabase, projectMethods), "ADD_BATCH");
        contentPanel.add(createRemoveBatchPanel(frame, projectDatabase, projectMethods), "REMOVE_BATCH");
        contentPanel.add(createSetTruckToBatchPanel(frame, projectDatabase, projectMethods), "ASSIGN_BATCH");
        contentPanel.add(createViewTruckStatusPanel(frame, projectDatabase, projectMethods), "VIEW_STATUS");
        contentPanel.add(createShowDriversPanel(frame, projectDatabase, projectMethods), "SHOW_DRIVERS");
        contentPanel.add(createAddDriverPanel(frame, projectDatabase, projectMethods), "ADD_DRIVER");
        contentPanel.add(createShowWorkersPanel(frame, projectDatabase, projectMethods), "SHOW_WORKERS");
        contentPanel.add(createAddWorkerPanel(frame, projectDatabase, projectMethods), "ADD_WORKER");
        contentPanel.add(createViewShelvesPanel(frame, projectDatabase, projectMethods), "VIEW_SHELVES");
        contentPanel.add(createAddShelfTypePanel(frame, projectDatabase, projectMethods), "ADD_SHELF_TYPE");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "WELCOME_PANEL");
        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_BACKGROUND);
        JLabel welcomeLabel = new JLabel("Welcome, Admin.");
        welcomeLabel.setFont(FONT_TITLE);
        welcomeLabel.setForeground(COLOR_TEXT_DARK);

        JLabel subtitleLabel = new JLabel("Select an option from the menu to begin.");
        subtitleLabel.setFont(FONT_HEADER);
        subtitleLabel.setForeground(COLOR_TEXT_DARK.darker());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        panel.add(welcomeLabel, gbc);
        gbc.gridy = 1;
        panel.add(subtitleLabel, gbc);

        return panel;
    }

    private static JPanel createAddTruckPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Add New Truck");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel plateLabel = new JLabel("Plate Number:");
        plateLabel.setFont(FONT_HEADER);
        panel.add(plateLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField truckNumberPlateTF = new JTextField(20);
        truckNumberPlateTF.setFont(FONT_BODY);
        panel.add(truckNumberPlateTF, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel modelLabel = new JLabel("Company and Model:");
        modelLabel.setFont(FONT_HEADER);
        panel.add(modelLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField companyAndModelTF = new JTextField(20);
        companyAndModelTF.setFont(FONT_BODY);
        panel.add(companyAndModelTF, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel typeLabel = new JLabel("Truck Type:");
        typeLabel.setFont(FONT_HEADER);
        panel.add(typeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        String[] truckTypes = { "incoming", "outgoing" };
        JComboBox<String> truckTypeCombo = new JComboBox<>(truckTypes);
        truckTypeCombo.setFont(FONT_BODY);
        panel.add(truckTypeCombo, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton addButton = createStyledButton("Add Truck", COLOR_ACCENT);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        addButton.addActionListener(e -> {
            String plateNumber = truckNumberPlateTF.getText();
            String companyModel = companyAndModelTF.getText();
            String truckType = (String) truckTypeCombo.getSelectedItem();

            if (plateNumber.isEmpty() || companyModel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Truck truck = new Truck(plateNumber, companyModel);
            TypeTruck typeTruck = new TypeTruck(truckType);
            projectDatabase.truckList.add(truck);
            projectDatabase.truckTypeHashMap.put(truck, typeTruck);

            JOptionPane.showMessageDialog(frame, "Truck added successfully!");
            truckNumberPlateTF.setText("");
            companyAndModelTF.setText("");
            contentPanel.add(createShowTrucksPanel(frame, projectDatabase, projectMethods), "SHOW_TRUCKS");

            cardLayout.show(contentPanel, "SHOW_TRUCKS");
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "SHOW_TRUCKS"));

        return panel;
    }

    private static JPanel createRemoveTruckPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Remove Truck");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel plateLabel = new JLabel("Truck Plate Number:");
        plateLabel.setFont(FONT_HEADER);
        panel.add(plateLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField plateTF = new JTextField(20);
        plateTF.setFont(FONT_BODY);
        panel.add(plateTF, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton removeButton = createStyledButton("Remove Truck", COLOR_DANGER);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        removeButton.addActionListener(e -> {
            String plateNumber = plateTF.getText();
            if (projectMethods.findTruckById(projectDatabase, plateNumber)) {
                projectMethods.removeTruckById(projectDatabase, plateNumber);
                JOptionPane.showMessageDialog(frame, "Truck removed successfully!");
                plateTF.setText("");
                contentPanel.add(createShowTrucksPanel(frame, projectDatabase, projectMethods), "SHOW_TRUCKS");
                cardLayout.show(contentPanel, "SHOW_TRUCKS");
            } else {
                JOptionPane.showMessageDialog(frame, "Truck not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "SHOW_TRUCKS"));

        return panel;
    }

    private static JPanel createShowTrucksPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Trucks");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = { "PLATE NUMBER", "COMPANY & MODEL", "TYPE" };
        ArrayList<Truck> truckList = projectDatabase.getTruckList();
        Object[][] data = new Object[truckList.size()][3];
        for (int i = 0; i < truckList.size(); i++) {
            Truck truck = truckList.get(i);
            data[i][0] = truck.truckNumberPlate;
            data[i][1] = truck.truckCompanyAndModel;
            data[i][2] = projectDatabase.truckTypeHashMap.get(truck).getTruckType();
        }

        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private static JPanel createAddBatchPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Add New Batch");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel batchIdLabel = new JLabel("Batch ID:");
        batchIdLabel.setFont(FONT_HEADER);
        panel.add(batchIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel batchId = new JLabel("B" + projectMethods.setBatchIdNum());
        batchId.setFont(FONT_BODY);
        panel.add(batchId, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel batchTypeLabel = new JLabel("Batch Type:");
        batchTypeLabel.setFont(FONT_HEADER);
        panel.add(batchTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField batchTypeTF = new JTextField(20);
        batchTypeTF.setFont(FONT_BODY);
        panel.add(batchTypeTF, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel batchItemQuantity = new JLabel("Item Quantity:");
        batchItemQuantity.setFont(FONT_HEADER);
        panel.add(batchItemQuantity, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField batchItemQuantityTF = new JTextField(20);
        batchItemQuantityTF.setFont(FONT_BODY);
        panel.add(batchItemQuantityTF, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel batchItemPrice = new JLabel("Single Item Price:");
        batchItemPrice.setFont(FONT_HEADER);
        panel.add(batchItemPrice, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField batchItemPriceTF = new JTextField(20);
        batchItemPriceTF.setFont(FONT_BODY);
        panel.add(batchItemPriceTF, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel batchDescription = new JLabel("Description:");
        batchDescription.setFont(FONT_HEADER);
        panel.add(batchDescription, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField batchDescriptionTF = new JTextField(20);
        batchDescriptionTF.setFont(FONT_BODY);
        panel.add(batchDescriptionTF, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton enterBatchDetails = createStyledButton("Add Batch", COLOR_ACCENT);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(enterBatchDetails);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        enterBatchDetails.addActionListener(e -> {
            try {
                int quantity = Integer.parseInt(batchItemQuantityTF.getText());
                int price = Integer.parseInt(batchItemPriceTF.getText());
                String description = batchDescriptionTF.getText();
                String type = batchTypeTF.getText();

                if (description.isEmpty() || type.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int total = quantity * price;
                Batch batch = new Batch(batchId.getText(), quantity, price, description, type);
                projectDatabase.batchList.add(batch);
                projectMethods.batchNum++;
                JOptionPane.showMessageDialog(frame, "Batch added successfully\nTotal Price: " + total);

                batchItemQuantityTF.setText("");
                batchItemPriceTF.setText("");
                batchDescriptionTF.setText("");
                batchTypeTF.setText("");
                batchId.setText("B" + projectMethods.setBatchIdNum());

                contentPanel.add(createShowBatchesPanel(frame, projectDatabase, projectMethods), "SHOW_BATCHES");
                cardLayout.show(contentPanel, "SHOW_BATCHES");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Quantity and Price must be valid numbers.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "SHOW_BATCHES"));

        return panel;
    }

    private static JPanel createRemoveBatchPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Remove Batch");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel batchIdLabel = new JLabel("Batch ID:");
        batchIdLabel.setFont(FONT_HEADER);
        panel.add(batchIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField batchIdTF = new JTextField(20);
        batchIdTF.setFont(FONT_BODY);
        panel.add(batchIdTF, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton removeButton = createStyledButton("Remove Batch", COLOR_DANGER);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        removeButton.addActionListener(e -> {
            String batchId = batchIdTF.getText();
            if (projectMethods.findBatchById(projectDatabase, batchId)) {
                projectMethods.removeBatchById(projectDatabase, batchId);
                JOptionPane.showMessageDialog(frame, "Batch removed successfully!");
                batchIdTF.setText("");
                contentPanel.add(createShowBatchesPanel(frame, projectDatabase, projectMethods), "SHOW_BATCHES");
                cardLayout.show(contentPanel, "SHOW_BATCHES");
            } else {
                JOptionPane.showMessageDialog(frame, "Batch not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "SHOW_BATCHES"));

        return panel;
    }

    private static JPanel createShowBatchesPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Batches");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = { "BATCH ID", "TYPE", "QUANTITY", "PRICE", "TOTAL COST" };
        ArrayList<Batch> batchList = projectDatabase.getBatchList();
        Object[][] data = new Object[batchList.size()][5];
        for (int i = 0; i < batchList.size(); i++) {
            Batch batch = batchList.get(i);
            data[i][0] = batch.batchId;
            data[i][1] = batch.batchType;
            data[i][2] = batch.batchItemQuantity;
            data[i][3] = batch.batchItemPrice;
            data[i][4] = batch.batchTotalCost;
        }

        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private static JPanel createSetTruckToBatchPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Assign Batch to Truck");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel truckLabel = new JLabel("Truck Plate Number:");
        truckLabel.setFont(FONT_HEADER);
        panel.add(truckLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField truckNumberPlateTF = new JTextField(20);
        truckNumberPlateTF.setFont(FONT_BODY);
        panel.add(truckNumberPlateTF, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel batchLabel = new JLabel("Batch ID:");
        batchLabel.setFont(FONT_HEADER);
        panel.add(batchLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField batchIdTF = new JTextField(20);
        batchIdTF.setFont(FONT_BODY);
        panel.add(batchIdTF, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton assignButton = createStyledButton("Assign", COLOR_ACCENT);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(assignButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        assignButton.addActionListener(e -> {
            String truckId = truckNumberPlateTF.getText();
            String batchId = batchIdTF.getText();

            if (projectMethods.findTruckById(projectDatabase, truckId)) {
                if (projectMethods.findBatchById(projectDatabase, batchId)) {
                    Truck truck = projectMethods.getTruckById(projectDatabase, truckId);
                    Batch batch = projectMethods.getBatchById(projectDatabase, batchId);
                    truck.addBatch(batch);
                    JOptionPane.showMessageDialog(frame, "Batch assigned to truck successfully!");
                    truckNumberPlateTF.setText("");
                    batchIdTF.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Batch not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Truck not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "WELCOME_PANEL"));

        return panel;
    }

    private static JPanel createViewTruckStatusPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("View Truck Status");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel searchLabel = new JLabel("Truck Plate:");
        searchLabel.setFont(FONT_HEADER);
        panel.add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField searchTF = new JTextField(15);
        searchTF.setFont(FONT_BODY);
        panel.add(searchTF, gbc);

        gbc.gridx = 2;
        JButton searchButton = createStyledButton("View Status", COLOR_ACCENT);
        panel.add(searchButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel resultsPanel = new JPanel(new BorderLayout(10, 10));
        resultsPanel.setBackground(COLOR_WHITE);
        panel.add(resultsPanel, gbc);

        searchButton.addActionListener(e -> {
            String truckId = searchTF.getText();
            resultsPanel.removeAll();

            if (projectMethods.findTruckById(projectDatabase, truckId)) {
                Truck truck = projectMethods.getTruckById(projectDatabase, truckId);

                String[] columnNames = { "BATCH ID", "TYPE", "QUANTITY", "UPDATE STATUS" };
                Object[][] data = new Object[truck.assignedBatches.size()][4];
                int updated = 0;
                int notUpdated = 0;
                for (int i = 0; i < truck.assignedBatches.size(); i++) {
                    Batch batch = truck.assignedBatches.get(i);
                    boolean status = truck.getBatchStatus(batch.getBatchId());
                    data[i][0] = batch.getBatchId();
                    data[i][1] = batch.getBatchType();
                    data[i][2] = batch.batchItemQuantity;
                    data[i][3] = status ? "YES" : "NO";
                    if (status)
                        updated++;
                    else
                        notUpdated++;
                }

                JTable table = new JTable(new DefaultTableModel(data, columnNames)) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                styleTable(table);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
                resultsPanel.add(scrollPane, BorderLayout.CENTER);

                JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
                infoPanel.setBackground(COLOR_WHITE);
                JLabel updatedLabel = new JLabel("UPDATED: " + updated);
                updatedLabel.setFont(FONT_HEADER);
                JLabel notUpdatedLabel = new JLabel("NOT UPDATED: " + notUpdated);
                notUpdatedLabel.setFont(FONT_HEADER);
                infoPanel.add(updatedLabel);
                infoPanel.add(notUpdatedLabel);
                resultsPanel.add(infoPanel, BorderLayout.SOUTH);

            } else {
                JLabel errorLabel = new JLabel("Truck not found!");
                errorLabel.setFont(FONT_HEADER);
                errorLabel.setForeground(COLOR_DANGER);
                resultsPanel.add(errorLabel, BorderLayout.NORTH);
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });

        return panel;
    }

    private static JPanel createAddDriverPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Add New Driver");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Driver Name:");
        nameLabel.setFont(FONT_HEADER);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField driverNameTextField = new JTextField(20);
        driverNameTextField.setFont(FONT_BODY);
        panel.add(driverNameTextField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel contactLabel = new JLabel("Driver Contact:");
        contactLabel.setFont(FONT_HEADER);
        panel.add(contactLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField driverContactTextField = new JTextField(20);
        driverContactTextField.setFont(FONT_BODY);
        panel.add(driverContactTextField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton enterDriverDetails = createStyledButton("Add Driver", COLOR_ACCENT);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(enterDriverDetails);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        enterDriverDetails.addActionListener(e -> {
            String name = driverNameTextField.getText();
            String contact = driverContactTextField.getText();

            if (name.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Driver driver = new Driver(name, contact);
            projectDatabase.driverList.add(driver);
            JOptionPane.showMessageDialog(frame, "Driver added successfully!");
            driverContactTextField.setText("");
            driverNameTextField.setText("");

            contentPanel.add(createShowDriversPanel(frame, projectDatabase, projectMethods), "SHOW_DRIVERS");
            cardLayout.show(contentPanel, "SHOW_DRIVERS");
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "SHOW_DRIVERS"));

        return panel;
    }

    private static JPanel createShowDriversPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Drivers");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = { "DRIVER NAME", "CONTACT" };
        ArrayList<Driver> driverList = projectDatabase.getDriverList();
        Object[][] data = new Object[driverList.size()][2];
        for (int i = 0; i < driverList.size(); i++) {
            Driver driver = driverList.get(i);
            data[i][0] = driver.driverName;
            data[i][1] = driver.driverContact;
        }

        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private static JPanel createAddWorkerPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Add New Worker");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Worker Name:");
        nameLabel.setFont(FONT_HEADER);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField workerNameTextField = new JTextField(20);
        workerNameTextField.setFont(FONT_BODY);
        panel.add(workerNameTextField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel contactLabel = new JLabel("Worker Contact:");
        contactLabel.setFont(FONT_HEADER);
        panel.add(contactLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField workerContactTextField = new JTextField(20);
        workerContactTextField.setFont(FONT_BODY);
        panel.add(workerContactTextField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel emailLabel = new JLabel("Worker Email:");
        emailLabel.setFont(FONT_HEADER);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField workerEmailTextField = new JTextField(20);
        workerEmailTextField.setFont(FONT_BODY);
        panel.add(workerEmailTextField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passLabel = new JLabel("Worker Password:");
        passLabel.setFont(FONT_HEADER);
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField workerPasswordPF = new JPasswordField(20);
        workerPasswordPF.setFont(FONT_BODY);
        panel.add(workerPasswordPF, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton enterWorkerDetails = createStyledButton("Add Worker", COLOR_ACCENT);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(enterWorkerDetails);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        enterWorkerDetails.addActionListener(e -> {
            String name = workerNameTextField.getText();
            String contact = workerContactTextField.getText();
            String email = workerEmailTextField.getText();
            String password = new String(workerPasswordPF.getPassword());

            if (name.isEmpty() || contact.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (projectDatabase.userPasswords.containsKey(email)) {
                JOptionPane.showMessageDialog(frame, "This email is already registered.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Worker worker = new Worker(name, contact, email, password);
            projectDatabase.workerList.add(worker);

            projectDatabase.userPasswords.put(email, password);
            projectDatabase.userTypes.put(email, "worker");

            JOptionPane.showMessageDialog(frame, "Worker added successfully!");

            workerContactTextField.setText("");
            workerNameTextField.setText("");
            workerEmailTextField.setText("");
            workerPasswordPF.setText("");

            contentPanel.add(createShowWorkersPanel(frame, projectDatabase, projectMethods), "SHOW_WORKERS");
            cardLayout.show(contentPanel, "SHOW_WORKERS");
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "SHOW_WORKERS"));

        return panel;
    }

    private static JPanel createShowWorkersPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Workers");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = { "WORKER NAME", "CONTACT", "EMAIL" };
        ArrayList<Worker> workerList = projectDatabase.getWorkerList();
        Object[][] data = new Object[workerList.size()][3];
        for (int i = 0; i < workerList.size(); i++) {
            Worker worker = workerList.get(i);
            data[i][0] = worker.workerName;
            data[i][1] = worker.workerContact;
            data[i][2] = worker.getWorkerEmail();
        }

        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private static JPanel createAddShelfTypePanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel panel = createStyledFormPanel("Add Shelf Type");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;

        gbc.gridx = 0;
        JLabel shelfTypeLabel = new JLabel("Shelf Type Name:");
        shelfTypeLabel.setFont(FONT_HEADER);
        panel.add(shelfTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField shelfTypeTF = new JTextField(20);
        shelfTypeTF.setFont(FONT_BODY);
        panel.add(shelfTypeTF, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_WHITE);

        JButton addButton = createStyledButton("Add Shelf Type", COLOR_ACCENT);
        JButton backButton = createStyledButton("Cancel", COLOR_SIDEBAR_HOVER);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        addButton.addActionListener(e -> {
            String shelfType = shelfTypeTF.getText();
            if (!shelfType.isEmpty()) {
                projectMethods.createShelfType(projectDatabase, shelfType);
                JOptionPane.showMessageDialog(frame, "Shelf type '" + shelfType + "' added!");
                shelfTypeTF.setText("");
                contentPanel.add(createViewShelvesPanel(frame, projectDatabase, projectMethods), "VIEW_SHELVES");
                cardLayout.show(contentPanel, "VIEW_SHELVES");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter shelf type!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "VIEW_SHELVES"));

        return panel;
    }

    private static JPanel createViewShelvesPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("View Shelves");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        selectPanel.setBackground(COLOR_BACKGROUND);
        JLabel typeLabel = new JLabel("Select Shelf Type:");
        typeLabel.setFont(FONT_HEADER);
        selectPanel.add(typeLabel);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBackground(COLOR_BACKGROUND);
        mainPanel.add(tableContainer, BorderLayout.CENTER);

        ArrayList<String> shelfTypes = projectDatabase.getShelfTypeList();
        if (shelfTypes.isEmpty()) {
            JLabel noTypesLabel = new JLabel("No shelf types created yet!");
            noTypesLabel.setFont(FONT_HEADER);
            selectPanel.add(noTypesLabel);
        } else {
            JComboBox<String> typeCombo = new JComboBox<>(shelfTypes.toArray(new String[0]));
            typeCombo.setFont(FONT_BODY);
            selectPanel.add(typeCombo);

            typeCombo.addActionListener(e -> {
                String selectedType = (String) typeCombo.getSelectedItem();
                tableContainer.removeAll();

                ArrayList<Shelf> shelves = projectMethods.getShelvesOfType(projectDatabase, selectedType);
                String[] columnNames = { "SHELF NUMBER", "STATUS", "BATCH ID" };
                Object[][] data = new Object[shelves.size()][3];
                for (int i = 0; i < shelves.size(); i++) {
                    Shelf shelf = shelves.get(i);
                    data[i][0] = shelf.getShelfNumber();
                    data[i][1] = shelf.isFilled() ? "FILLED" : "NOT FILLED";
                    data[i][2] = shelf.getBatchId() != null ? shelf.getBatchId() : "-";
                }

                JTable table = new JTable(new DefaultTableModel(data, columnNames)) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                styleTable(table);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
                tableContainer.add(scrollPane, BorderLayout.CENTER);

                tableContainer.revalidate();
                tableContainer.repaint();
            });

            if (typeCombo.getItemCount() > 0) {
                typeCombo.setSelectedIndex(0);
            }
        }

        mainPanel.add(selectPanel, BorderLayout.CENTER);
        mainPanel.add(tableContainer, BorderLayout.SOUTH);
        mainPanel.remove(tableContainer);
        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.remove(selectPanel);
        mainPanel.add(selectPanel, BorderLayout.AFTER_LAST_LINE);

        mainPanel.remove(tableContainer);
        mainPanel.remove(selectPanel);

        JPanel centerContent = new JPanel(new BorderLayout(10, 10));
        centerContent.setBackground(COLOR_BACKGROUND);
        centerContent.add(selectPanel, BorderLayout.NORTH);
        centerContent.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(centerContent, BorderLayout.CENTER);

        return mainPanel;
    }
}