import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TruckManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProjectDatabase projectDatabase = new ProjectDatabase();
            ProjectMethods projectMethods = new ProjectMethods();

            String type1 = "Electronics";
            String type2 = "Groceries";
            String type3 = "Apparel";
            String type4 = "Furniture";
            String type5 = "Pharmaceuticals";
            String[] batchTypes = { type1, type2, type3, type4, type5 };

            projectMethods.createShelfType(projectDatabase, type1);
            projectMethods.createShelfType(projectDatabase, type2);
            projectMethods.createShelfType(projectDatabase, type3);
            projectMethods.createShelfType(projectDatabase, type4);
            projectMethods.createShelfType(projectDatabase, type5);

            Worker w1 = new Worker("Suresh Gupta", "9111111111", "suresh@warehouse.com", "SureshG123");
            Worker w2 = new Worker("Meena Kumari", "9111111112", "meena@warehouse.com", "MeenaK123");
            Worker w3 = new Worker("Amit Patel", "9111111113", "amit@warehouse.com", "AmitP123");
            Worker w4 = new Worker("Sunita Rao", "9111111114", "sunita@warehouse.com", "SunitaR123");
            Worker w5 = new Worker("Vikram Singh", "9111111115", "vikram@warehouse.com", "VikramS123");
            Worker w6 = new Worker("Anjali Desai", "9111111116", "anjali@warehouse.com", "AnjaliD123");
            Worker w7 = new Worker("Priya Sharma", "9111111117", "priya@warehouse.com", "PriyaS123");

            projectDatabase.workerList.add(w1);
            projectDatabase.workerList.add(w2);
            projectDatabase.workerList.add(w3);
            projectDatabase.workerList.add(w4);
            projectDatabase.workerList.add(w5);
            projectDatabase.workerList.add(w6);
            projectDatabase.workerList.add(w7);

             projectDatabase.userPasswords.put("admin@warehouse.com", "admin123");
            projectDatabase.userTypes.put("admin@warehouse.com", "admin");
            projectDatabase.userPasswords.put(w1.getWorkerEmail(), "SureshG123");
            projectDatabase.userTypes.put(w1.getWorkerEmail(), "worker");
            projectDatabase.userPasswords.put(w2.getWorkerEmail(), "MeenaK123");
            projectDatabase.userTypes.put(w2.getWorkerEmail(), "worker");
            projectDatabase.userPasswords.put(w3.getWorkerEmail(), "AmitP123");
            projectDatabase.userTypes.put(w3.getWorkerEmail(), "worker");
            projectDatabase.userPasswords.put(w4.getWorkerEmail(), "SunitaR123");
            projectDatabase.userTypes.put(w4.getWorkerEmail(), "worker");
            projectDatabase.userPasswords.put(w5.getWorkerEmail(), "VikramS123");
            projectDatabase.userTypes.put(w5.getWorkerEmail(), "worker");
            projectDatabase.userPasswords.put(w6.getWorkerEmail(), "AnjaliD123");
            projectDatabase.userTypes.put(w6.getWorkerEmail(), "worker");
            projectDatabase.userPasswords.put(w7.getWorkerEmail(), "PriyaS123");
            projectDatabase.userTypes.put(w7.getWorkerEmail(), "worker");

            Driver d1 = new Driver("Rajesh Kumar", "9876543210");
            Driver d2 = new Driver("Aman Singh", "9876543211");
            Driver d3 = new Driver("Vijay Rao", "9876543212");
            Driver d4 = new Driver("Sanjay Verma", "9876543213");
            Driver d5 = new Driver("Deepak Kumar", "9876543214");
            Driver d6 = new Driver("Manoj Sharma", "9876543215");
            Driver d7 = new Driver("Anil Yadav", "9876543216");
            Driver d8 = new Driver("Ravi Prakash", "9876543217");
            Driver d9 = new Driver("Nitin Gadkari", "9876543218");
            Driver d10 = new Driver("Rohan Mehra", "9876543219");
            projectDatabase.driverList.add(d1);
            projectDatabase.driverList.add(d2);
            projectDatabase.driverList.add(d3);
            projectDatabase.driverList.add(d4);
            projectDatabase.driverList.add(d5);
            projectDatabase.driverList.add(d6);
            projectDatabase.driverList.add(d7);
            projectDatabase.driverList.add(d8);
            projectDatabase.driverList.add(d9);
            projectDatabase.driverList.add(d10);

            TypeTruck x1 = new TypeTruck("incoming");
            TypeTruck x2 = new TypeTruck("outgoing");
            Truck t1 = new Truck("MH01AB1234", "Tata 407");
            Truck t2 = new Truck("MH02CD5678", "Mahindra Pickup");
            Truck t3 = new Truck("MH03EF9012", "Ashok Leyland");
            Truck t4 = new Truck("MH04GH3456", "Eicher Pro");
            Truck t5 = new Truck("MH05IJ7890", "BharatBenz");
            
            Truck t6 = new Truck("MH06KL1234", "Tata Ace");
            Truck t7 = new Truck("MH07MN5678", "Maruti Super Carry");
            Truck t8 = new Truck("MH08OP9012", "Force Urbania");
            Truck t9 = new Truck("MH09QR3456", "Isuzu D-Max");
            Truck t10 = new Truck("MH10ST7890", "Volvo FH");

            projectDatabase.truckList.add(t1);
            projectDatabase.truckList.add(t2);
            projectDatabase.truckList.add(t3);
            projectDatabase.truckList.add(t4);
            projectDatabase.truckList.add(t5);
            projectDatabase.truckList.add(t6);
            projectDatabase.truckList.add(t7);
            projectDatabase.truckList.add(t8);
            projectDatabase.truckList.add(t9);
            projectDatabase.truckList.add(t10);

            projectDatabase.truckTypeHashMap.put(t1, x1);
            projectDatabase.truckTypeHashMap.put(t2, x1);
            projectDatabase.truckTypeHashMap.put(t3, x1);
            projectDatabase.truckTypeHashMap.put(t4, x1);
            projectDatabase.truckTypeHashMap.put(t5, x1);
            projectDatabase.truckTypeHashMap.put(t6, x2);
            projectDatabase.truckTypeHashMap.put(t7, x2);
            projectDatabase.truckTypeHashMap.put(t8, x2);
            projectDatabase.truckTypeHashMap.put(t9, x2);
            projectDatabase.truckTypeHashMap.put(t10, x2);

            projectDatabase.truckDrivers.put(t1, d1);
            projectDatabase.truckDrivers.put(t2, d2);
            projectDatabase.truckDrivers.put(t3, d3);
            projectDatabase.truckDrivers.put(t4, d4);
            projectDatabase.truckDrivers.put(t5, d5);
            projectDatabase.truckDrivers.put(t6, d6);
            projectDatabase.truckDrivers.put(t7, d7);
            projectDatabase.truckDrivers.put(t8, d8);
            projectDatabase.truckDrivers.put(t9, d9);
            projectDatabase.truckDrivers.put(t10, d10);

            for (Truck truck : projectDatabase.truckList) {
                for (int i = 0; i < 5; i++) {
                    String batchId = "B" + projectMethods.setBatchIdNum();
                    String type = batchTypes[projectMethods.batchNum % 5];
                    int quantity = 100 + (projectMethods.batchNum % 50);
                    int price = 50 + (projectMethods.batchNum % 20);
                    String desc = type + " batch " + (i + 1) + " for " + truck.truckNumberPlate;

                    Batch batch = new Batch(batchId, quantity, price, desc, type);

                    projectDatabase.batchList.add(batch);
                    truck.addBatch(batch);
                    projectDatabase.assignedBatchesList.add(batch);

                    projectMethods.batchNum++;
                }
            }

            for (int i = 0; i < 10; i++) {
                String batchId = "B" + projectMethods.setBatchIdNum();
                String type = batchTypes[projectMethods.batchNum % 5];
                int quantity = 75 + i;
                int price = 30 + i;
                String desc = "Unassigned " + type + " batch";

                Batch batch = new Batch(batchId, quantity, price, desc, type);
                projectDatabase.batchList.add(batch);

                projectMethods.batchNum++;
            }

            JFrame frame = new JFrame("Warehouse Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            loginMenu(frame, projectDatabase, projectMethods);
        });
    }

    public static void loginMenu(JFrame frame, ProjectDatabase projectDatabase, ProjectMethods projectMethods) {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("WAREHOUSE LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        JLabel emailLabel = new JLabel("Email (Username):");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(25);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(emailField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(25);
        passField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passField, gbc);

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(errorLabel, gbc);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            String storedPassword = projectDatabase.userPasswords.get(email);
            String userType = projectDatabase.userTypes.get(email);

            if (storedPassword != null && storedPassword.equals(password)) {
                if (userType.equals("admin")) {
                    AdminMenu(frame, projectDatabase, projectMethods);
                } else if (userType.equals("worker")) {
                    WorkerMenu(frame, projectDatabase, projectMethods);
                }
            } else {
                errorLabel.setText("Invalid email or password. Please try again.");
            }
        });

        passField.addActionListener(e -> loginButton.doClick());
        emailField.addActionListener(e -> passField.requestFocusInWindow());

        String imagePath = "D:\\Downloads\\whm\\w2.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JPanel imagePanel;

        if (imageIcon.getIconWidth() == -1) {
            imagePanel = new JPanel(new GridBagLayout());
            JLabel errorImageLabel = new JLabel("Image not found: " + imagePath);
            errorImageLabel.setFont(new Font("Arial", Font.BOLD, 16));
            errorImageLabel.setForeground(Color.RED);
            imagePanel.add(errorImageLabel);
            imagePanel.setBackground(Color.DARK_GRAY);
        } else {
            imagePanel = new JPanel(new GridBagLayout());
            imagePanel.setBackground(Color.WHITE);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, loginPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(0);

        frame.setContentPane(splitPane);
        frame.revalidate();
        frame.repaint();
    }

    private static void AdminMenu(JFrame frame, ProjectDatabase projectDatabase, ProjectMethods projectMethods) {
        MenuAdmin menuAdminClass = new MenuAdmin();
        menuAdminClass.menuAdmin(frame, projectDatabase, projectMethods);
    }

    private static void WorkerMenu(JFrame frame, ProjectDatabase projectDatabase, ProjectMethods projectMethods) {
        MenuWorker menuWorkerClass = new MenuWorker();
        menuWorkerClass.showWorkerMenu(frame, projectDatabase, projectMethods);
    }

}
