import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    // UI Components
    private JTextField nameField, daysField, travellersField;
    private JComboBox<String> cityBox, typeBox, budgetBox;
    private JTextArea planArea;
    private JLabel costLabel;

    public MainFrame() {
        // Setup Window
        setTitle("Smart Tourist Guide & Travel Planner");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));

        // 1. Top Panel: Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Trip Details"));

        inputPanel.add(new JLabel("Name"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Destination City"));
        String[] cities = {"Goa", "Mysore", "Ooty", "Coorg", "Bangalore", "Manali"};
        cityBox = new JComboBox<>(cities);
        inputPanel.add(cityBox);

        inputPanel.add(new JLabel("Number of Days"));
        daysField = new JTextField();
        inputPanel.add(daysField);

        inputPanel.add(new JLabel("Number of Travellers"));
        travellersField = new JTextField();
        inputPanel.add(travellersField);

        inputPanel.add(new JLabel("Travel Type"));
        String[] types = {"Family", "Friends", "Solo", "Couple"};
        typeBox = new JComboBox<>(types);
        inputPanel.add(typeBox);

        inputPanel.add(new JLabel("Budget"));
        String[] budgets = {"Low", "Medium", "High"};
        budgetBox = new JComboBox<>(budgets);
        inputPanel.add(budgetBox);

        add(inputPanel, BorderLayout.NORTH);

        // 2. Center Panel: Plan Display Area
        planArea = new JTextArea();
        planArea.setEditable(false);
        planArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(planArea);
        add(scrollPane, BorderLayout.CENTER);

        // 3. Bottom Panel: Cost and Buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        costLabel = new JLabel("Estimated Cost : ₹ 0.0");
        costLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(costLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton generateBtn = new JButton("Generate Plan");
        JButton saveBtn = new JButton("Save Trip");
        JButton viewBtn = new JButton("View Saved Trips");

        buttonPanel.add(generateBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(viewBtn);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        // --- BUTTON ACTIONS ---

        // Action for Generate Plan Button
        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String city = (String) cityBox.getSelectedItem();
                    int days = Integer.parseInt(daysField.getText());
                    int travellers = Integer.parseInt(travellersField.getText());
                    String budget = (String) budgetBox.getSelectedItem();

                    // Generate plan and cost using TravelPlanner class
                    String plan = TravelPlanner.generatePlan(city, days);
                    double cost = TravelPlanner.calculateCost(days, travellers, budget);

                    planArea.setText(plan);
                    costLabel.setText("Estimated Cost : ₹ " + cost);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for Days and Travellers!");
                }
            }
        });

        // Action for Save Trip Button
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String city = (String) cityBox.getSelectedItem();
                int days = Integer.parseInt(daysField.getText());
                int travellers = Integer.parseInt(travellersField.getText());
                String type = (String) typeBox.getSelectedItem();
                String budget = (String) budgetBox.getSelectedItem();
                String plan = planArea.getText();

                if (plan.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please generate a plan first!");
                    return;
                }

                // Save to database using DatabaseHelper class
                boolean success = DatabaseHelper.saveTrip(name, city, days, travellers, type, budget, plan);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Trip saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save trip. Check database connection.");
                }
            }
        });

        // Action for View Saved Trips Button
        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the ViewTripsFrame (we will create this next)
                new ViewTripsFrame().setVisible(true);
            }
        });
    }
}
