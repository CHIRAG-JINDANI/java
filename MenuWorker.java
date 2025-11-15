import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuWorker {

    private static final Color COLOR_BACKGROUND = new Color(240, 242, 245);
    private static final Color COLOR_SIDEBAR = new Color(38, 43, 51);
    private static final Color COLOR_SIDEBAR_HOVER = new Color(55, 61, 70);
    private static final Color COLOR_TEXT_LIGHT = new Color(230, 230, 230);
    private static final Color COLOR_TEXT_DARK = new Color(40, 40, 40);
    private static final Color COLOR_ACCENT = new Color(0, 120, 215);
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
            setBorder(new EmptyBorder(20, 25, 20, 25));
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

    public void showWorkerMenu(JFrame frame, ProjectDatabase projectDatabase, ProjectMethods projectMethods) {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(COLOR_BACKGROUND);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(COLOR_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(280, 0));

        sidebar.add(createSectionHeader("STATIONS"));
        sidebar.add(new MenuButton("Incoming Station", "INCOMING", frame, projectDatabase, projectMethods));
        sidebar.add(new MenuButton("Outgoing Station", "OUTGOING", frame, projectDatabase, projectMethods));

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(createSectionHeader("SYSTEM"));
        sidebar.add(new MenuButton("Logout", "LOGOUT", frame, projectDatabase, projectMethods));

        contentPanel.add(createWelcomePanel(), "WELCOME_PANEL");
        contentPanel.add(createStationPanel(frame, projectDatabase, projectMethods, "incoming"), "INCOMING");
        contentPanel.add(createStationPanel(frame, projectDatabase, projectMethods, "outgoing"), "OUTGOING");

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
        JLabel welcomeLabel = new JLabel("Welcome, Worker.");
        welcomeLabel.setFont(FONT_TITLE);
        welcomeLabel.setForeground(COLOR_TEXT_DARK);

        JLabel subtitleLabel = new JLabel("Select a station from the menu to begin.");
        subtitleLabel.setFont(FONT_HEADER);
        subtitleLabel.setForeground(COLOR_TEXT_DARK.darker());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        panel.add(welcomeLabel, gbc);
        gbc.gridy = 1;
        panel.add(subtitleLabel, gbc);

        return panel;
    }

    private static JPanel createStationPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods, String stationType) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String title = stationType.equals("incoming") ? "Incoming Station" : "Outgoing Station";
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = { "TRUCK PLATE", "COMPANY & MODEL", "DRIVER" };

        java.util.List<Truck> stationTrucks = new java.util.ArrayList<>();
        for (Map.Entry<Truck, TypeTruck> entry : projectDatabase.truckTypeHashMap.entrySet()) {
            if (entry.getValue().getTruckType().equals(stationType)) {
                stationTrucks.add(entry.getKey());
            }
        }

        Object[][] data = new Object[stationTrucks.size()][3];
        int i = 0;
        for (Truck truck : stationTrucks) {
            Driver driver = projectDatabase.truckDrivers.get(truck);
            data[i][0] = truck.getTruckNumberPlate();
            data[i][1] = truck.getTruckCompanyAndModel();
            data[i][2] = (driver != null) ? driver.driverName : "N/A";
            i++;
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel findPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        findPanel.setBackground(COLOR_BACKGROUND);

        JLabel findLabel = new JLabel("Find Truck:");
        findLabel.setFont(FONT_HEADER);
        findPanel.add(findLabel);

        JTextField findTF = new JTextField(20);
        findTF.setFont(FONT_BODY);
        findPanel.add(findTF);

        JButton findButton = createStyledButton("Find", COLOR_ACCENT);
        findPanel.add(findButton);

        mainPanel.add(findPanel, BorderLayout.SOUTH);

        findButton.addActionListener(e -> {
            String truckId = findTF.getText();
            if (projectMethods.findTruckById(projectDatabase, truckId)) {
                Truck truck = projectMethods.getTruckById(projectDatabase, truckId);
                if (projectDatabase.truckTypeHashMap.get(truck).getTruckType().equals(stationType)) {
                    String panelName = "DETAILS_" + truckId;
                    JPanel detailsPanel = createTruckBatchDetailsPanel(frame, projectDatabase, projectMethods, truck,
                            stationType);
                    contentPanel.add(detailsPanel, panelName);
                    cardLayout.show(contentPanel, panelName);
                } else {
                    JOptionPane.showMessageDialog(frame, "This truck is not at the " + stationType + " station!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Truck not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return mainPanel;
    }

    private static JPanel createTruckBatchDetailsPanel(JFrame frame, ProjectDatabase projectDatabase,
            ProjectMethods projectMethods, Truck truck, String stationType) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String title = "Truck: " + truck.getTruckNumberPlate() + " (" + stationType.toUpperCase() + ")";
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = { "BATCH ID", "TYPE", "QUANTITY", "UPDATE STATUS" };
        Object[][] data = new Object[truck.assignedBatches.size()][4];
        for (int i = 0; i < truck.assignedBatches.size(); i++) {
            Batch batch = truck.assignedBatches.get(i);
            data[i][0] = batch.getBatchId();
            data[i][1] = batch.getBatchType();
            data[i][2] = batch.batchItemQuantity;
            data[i][3] = truck.getBatchStatus(batch.getBatchId()) ? "YES" : "NO";
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        updatePanel.setBackground(COLOR_BACKGROUND);

        JLabel batchLabel = new JLabel("Batch ID:");
        batchLabel.setFont(FONT_HEADER);
        updatePanel.add(batchLabel);

        JTextField batchTextField = new JTextField(15);
        batchTextField.setFont(FONT_BODY);
        updatePanel.add(batchTextField);

        String buttonText = stationType.equals("incoming") ? "Move to Shelf" : "Load on Truck";
        JButton putButton = createStyledButton(buttonText, COLOR_ACCENT);
        updatePanel.add(putButton);

        JButton backButton = createStyledButton("Back to Station", COLOR_SIDEBAR_HOVER);
        updatePanel.add(backButton);

        mainPanel.add(updatePanel, BorderLayout.SOUTH);

        putButton.addActionListener(e -> {
            String batchId = batchTextField.getText();
            boolean found = false;
            for (Batch batch : truck.assignedBatches) {
                if (batch.getBatchId().equals(batchId)) {
                    found = true;
                    if (truck.getBatchStatus(batchId)) {
                        JOptionPane.showMessageDialog(frame, "Batch '" + batchId + "' has already been processed.",
                                "Already Done", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    if (stationType.equals("incoming")) {
                        projectMethods.assignBatchToShelf(projectDatabase, batch);
                        truck.updateBatchStatus(batchId, true);
                        JOptionPane.showMessageDialog(frame, "Batch moved to shelf!");
                    } else {
                        projectMethods.removeBatchFromShelf(projectDatabase, batchId);
                        truck.updateBatchStatus(batchId, true);
                        JOptionPane.showMessageDialog(frame, "Batch loaded to truck!");
                    }

                    String panelName = "DETAILS_" + truck.getTruckNumberPlate();
                    JPanel newDetailsPanel = createTruckBatchDetailsPanel(frame, projectDatabase, projectMethods, truck,
                            stationType);
                    contentPanel.add(newDetailsPanel, panelName);
                    cardLayout.show(contentPanel, panelName);
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Batch not assigned to this truck!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            String cardName = stationType.equals("incoming") ? "INCOMING" : "OUTGOING";
            cardLayout.show(contentPanel, cardName);
        });

        return mainPanel;
    }
}