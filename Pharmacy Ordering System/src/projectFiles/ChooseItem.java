package projectFiles;
import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ChooseItem extends JPanel {
	private static final String API_URL = "http://localhost/webServiceProjectDAD/populateProductList.php?category=";
	private static JTextField tfName;
	private static JTextField tfAddress;
	private static JTextField tfPhoneNum;
	private static JTextField tfQuantityVitamin;
	private static JTextField tfQuantityShampoo;
	private static JTextField tfQuantityToothpaste;
	private static JTextField tfQuantityBodyWash;
	static JComboBox<String> cbBodyWash = new JComboBox<>();
	static JComboBox<String> cbToothpaste = new JComboBox<>();
	static JComboBox<String> cbShampoo = new JComboBox<>();
	static JComboBox<String> cbVitamin = new JComboBox<>();
	
    public ChooseItem() {
    	
    	
        
        addDefaultItem(cbBodyWash);
        addDefaultItem(cbToothpaste);
        addDefaultItem(cbShampoo);
        addDefaultItem(cbVitamin);

        // Populate combo boxes in separate threads
        new Thread(() -> populateComboBox(cbBodyWash, "Body Wash")).start();
        new Thread(() -> populateComboBox(cbToothpaste, "Toothpaste")).start();
        new Thread(() -> populateComboBox(cbShampoo, "Shampoo")).start();
        new Thread(() -> populateComboBox(cbVitamin, "Vitamin")).start();
    	setBackground(new Color(135, 206, 250));
        // Set up the panel
    	setBounds(174, 20, 501, 420);
    	setLayout(null);
    	
    	JLabel lblNewLabel = new JLabel("Choose Item");
    	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
    	lblNewLabel.setBounds(183, 10, 148, 28);
    	add(lblNewLabel);
    	
    	JButton btnSubmit = new JButton("SUBMIT");
    	btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 10));
    	btnSubmit.setBounds(198, 389, 102, 21);
    	add(btnSubmit);
    	
    	JLabel lblName = new JLabel("Name :");
    	lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblName.setBounds(25, 285, 64, 13);
    	add(lblName);
    	
    	tfName = new JTextField();
    	tfName.setEditable(false);
    	tfName.setText(SessionManager.getUsername());
    	tfName.setBounds(25, 301, 190, 28);
    	add(tfName);
    	tfName.setColumns(10);
    	
    	JLabel lblPhoneNumber = new JLabel("Phone Number :");
    	lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblPhoneNumber.setBounds(22, 332, 112, 13);
    	add(lblPhoneNumber);
    	
    	JLabel lblAddress = new JLabel("Address :");
    	lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblAddress.setBounds(288, 285, 64, 13);
    	add(lblAddress);
    	
    	tfAddress = new JTextField();
    	tfAddress.setColumns(10);
    	tfAddress.setBounds(288, 301, 190, 73);
    	add(tfAddress);
    	
    	tfPhoneNum = new JTextField();
    	tfPhoneNum.setColumns(10);
    	tfPhoneNum.setBounds(25, 351, 190, 28);
    	add(tfPhoneNum);
    	
    	JPanel panel = new JPanel();
    	panel.setBackground(SystemColor.inactiveCaptionBorder);
    	panel.setBounds(10, 48, 481, 230);
    	add(panel);
    	panel.setLayout(null);
    	
    	//JComboBox cbVitamin = new JComboBox();
    	cbVitamin.setBounds(247, 106, 173, 29);
    	panel.add(cbVitamin);
    	
    	tfQuantityVitamin = new JTextField();
    	tfQuantityVitamin.setColumns(10);
    	tfQuantityVitamin.setBounds(430, 107, 31, 28);
    	panel.add(tfQuantityVitamin);
    	
    	JLabel lblVitaminSupplements = new JLabel("Vitamin Supplements");
    	lblVitaminSupplements.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
    	lblVitaminSupplements.setBounds(247, 78, 173, 28);
    	panel.add(lblVitaminSupplements);
    	
    	//JComboBox cbShampoo = new JComboBox();
    	cbShampoo.setBounds(10, 106, 173, 29);
    	panel.add(cbShampoo);
    	
    	tfQuantityShampoo = new JTextField();
    	tfQuantityShampoo.setColumns(10);
    	tfQuantityShampoo.setBounds(193, 107, 31, 28);
    	panel.add(tfQuantityShampoo);
    	
    	JLabel lblShampoo = new JLabel("Shampoo");
    	lblShampoo.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
    	lblShampoo.setBounds(10, 77, 112, 28);
    	panel.add(lblShampoo);
    	
    	//JComboBox cbBodyWash = new JComboBox();
    	cbBodyWash.setBounds(10, 38, 173, 29);
    	panel.add(cbBodyWash);
    	
    	tfQuantityBodyWash = new JTextField();
    	tfQuantityBodyWash.setColumns(10);
    	tfQuantityBodyWash.setBounds(193, 39, 31, 28);
    	panel.add(tfQuantityBodyWash);
    	
    	//JComboBox cbToothpaste = new JComboBox();
    	cbToothpaste.setBounds(247, 38, 173, 29);
    	panel.add(cbToothpaste);
    	
    	tfQuantityToothpaste = new JTextField();
    	tfQuantityToothpaste.setColumns(10);
    	tfQuantityToothpaste.setBounds(430, 39, 31, 28);
    	panel.add(tfQuantityToothpaste);
    	
    	JLabel lblToothpaste = new JLabel("Toothpaste");
    	lblToothpaste.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
    	lblToothpaste.setBounds(247, 10, 173, 28);
    	panel.add(lblToothpaste);
    	
    	JLabel lblBodyWash = new JLabel("Body Wash");
    	lblBodyWash.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
    	lblBodyWash.setBounds(10, 10, 112, 28);
    	panel.add(lblBodyWash);
    	
    	btnSubmit.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        new Thread(new Runnable() {
    	            public void run() {
    	                handleSubmit();
    	            }
    	        }).start();
    	    }
    	});
    	
    }
    
    private static void handleSubmit() {
        try {
            StringBuilder orderDetails = new StringBuilder();
            StringBuilder itemsOrdered = new StringBuilder();
            double totalPrice = 0.0;  // Initialize total price

            String name = tfName.getText();
            String phoneNum = tfPhoneNum.getText();
            String address = tfAddress.getText();

            orderDetails.append("Customer Details:\n");
            orderDetails.append("Name: ").append(name).append("\n");
            orderDetails.append("Phone Number: ").append(phoneNum).append("\n");
            orderDetails.append("Address: ").append(address).append("\n\n");

            // Process each item and update totalPrice
            double bodyWashTotal = processOrderItem(cbBodyWash, tfQuantityBodyWash, orderDetails, itemsOrdered);
            if (bodyWashTotal < 0) return;
            totalPrice += bodyWashTotal;

            double toothpasteTotal = processOrderItem(cbToothpaste, tfQuantityToothpaste, orderDetails, itemsOrdered);
            if (toothpasteTotal < 0) return;
            totalPrice += toothpasteTotal;

            double shampooTotal = processOrderItem(cbShampoo, tfQuantityShampoo, orderDetails, itemsOrdered);
            if (shampooTotal < 0) return;
            totalPrice += shampooTotal;

            double vitaminTotal = processOrderItem(cbVitamin, tfQuantityVitamin, orderDetails, itemsOrdered);
            if (vitaminTotal < 0) return;
            totalPrice += vitaminTotal;

            // Format total price to 2 decimal places
            orderDetails.append("Total Price: ").append(String.format("%.2f", totalPrice)).append("\n");

            // Show confirmation dialog
            int result = JOptionPane.showConfirmDialog(null, orderDetails.toString(), "Order Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Deduct quantities only if confirmed
                if (!cbBodyWash.getSelectedItem().equals("Please select...")) {
                    updateProductQuantity((String) cbBodyWash.getSelectedItem(), Integer.parseInt(tfQuantityBodyWash.getText()));
                }
                if (!cbToothpaste.getSelectedItem().equals("Please select...")) {
                    updateProductQuantity((String) cbToothpaste.getSelectedItem(), Integer.parseInt(tfQuantityToothpaste.getText()));
                }
                if (!cbShampoo.getSelectedItem().equals("Please select...")) {
                    updateProductQuantity((String) cbShampoo.getSelectedItem(), Integer.parseInt(tfQuantityShampoo.getText()));
                }
                if (!cbVitamin.getSelectedItem().equals("Please select...")) {
                    updateProductQuantity((String) cbVitamin.getSelectedItem(), Integer.parseInt(tfQuantityVitamin.getText()));
                }

                // Send order details to server
                sendOrderDetails(name, phoneNum, address, itemsOrdered.toString(), String.format("%.2f", totalPrice));
                

                JOptionPane.showMessageDialog(null, "Order confirmed and quantities updated.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Order canceled.", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // resit before confirming order
    private static double processOrderItem(JComboBox<String> comboBox, JTextField quantityField, StringBuilder orderDetails, StringBuilder itemsOrdered) {
        String selectedItem = (String) comboBox.getSelectedItem();
        if (selectedItem == null || selectedItem.equals("Please select...")) return 0.0;

        String quantityText = quantityField.getText();
        if (quantityText == null || quantityText.trim().isEmpty()) return 0.0;

        String[] parts = selectedItem.split(" - ");
        String productName = parts[0];
        double price = Double.parseDouble(parts[1]);
        int requestedQuantity = Integer.parseInt(quantityText.trim());

        if (requestedQuantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0 for " + productName, "Error", JOptionPane.ERROR_MESSAGE);
            return -1.0;
        }

        int availableQuantity = getProductQuantity(productName);
        if (availableQuantity == -1) {
            JOptionPane.showMessageDialog(null, "Error fetching quantity for " + productName, "Error", JOptionPane.ERROR_MESSAGE);
            return -1.0;
        }

        if (requestedQuantity > availableQuantity) {
            JOptionPane.showMessageDialog(null, "Requested quantity for " + productName + " exceeds available stock. Available: " + availableQuantity, "Error", JOptionPane.ERROR_MESSAGE);
            return -1.0;
        }

        double itemTotal = price * requestedQuantity;  // Calculate item total

        // Format price and item total to 2 decimal places
        orderDetails.append(productName).append(" x ").append(requestedQuantity)
                    .append(" @ ").append(String.format("%.2f", price))
                    .append(" each = ").append(String.format("%.2f", itemTotal)).append("\n");

        // Append to itemsOrdered
        if (itemsOrdered.length() > 0) {
            itemsOrdered.append(", ");
        }
        itemsOrdered.append(productName).append(" - ").append(requestedQuantity);

        return itemTotal;  // Return item total to add to overall total price
    }

    
    private static void updateProductQuantity(String selectedItem, int requestedQuantity) {
        try {
            if (selectedItem == null || selectedItem.equals("Please select...")) return;

            String[] parts = selectedItem.split(" - ");
            String productName = parts[0];

            URL url = new URL("http://localhost/webServiceProjectDAD/updateProductQuantity.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            String data = "productName=" + URLEncoder.encode(productName, StandardCharsets.UTF_8.toString()) +
                          "&quantity=" + URLEncoder.encode(String.valueOf(requestedQuantity), StandardCharsets.UTF_8.toString());

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Handle the response if necessary
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void populateComboBox(JComboBox<String> comboBox, String category) {
        try {
            String encodedCategory = URLEncoder.encode(category, StandardCharsets.UTF_8.toString());
            URL url = new URL(API_URL + encodedCategory);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // Log the response for debugging
            System.out.println("Response from server: " + content.toString());

            // Check if the response is a valid JSON array
            if (content.toString().startsWith("[")) {
                JSONArray jsonArray = new JSONArray(content.toString());
                List<String> products = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    products.add(jsonArray.getString(i));
                }

                // Update the combo box on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> {
                    for (String product : products) {
                        comboBox.addItem(product);
                    }
                });
            } else {
                System.out.println("Invalid JSON array format: " + content.toString());
                JOptionPane.showMessageDialog(null, "Invalid data received from the server.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private static void addDefaultItem(JComboBox<String> comboBox) {
        comboBox.addItem("Please select...");
        comboBox.setSelectedIndex(0);
    }
    
    private static int getProductQuantity(String productName) {
        int quantity = -1; // Default value to indicate an error
        try {
            URL url = new URL("http://localhost/webServiceProjectDAD/getProductQuantity.php?productName=" + URLEncoder.encode(productName, StandardCharsets.UTF_8.toString()));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            JSONObject json = new JSONObject(content.toString());
            if (json.has("quantity")) {
                quantity = json.getInt("quantity");
            } else if (json.has("error")) {
                System.out.println("Error: " + json.getString("error"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }
    
    private static void sendOrderDetails(String customerName, String phoneNum, String address, String itemOrdered, String totalPrice) {
        try {
            URL url = new URL("http://localhost/webServiceProjectDAD/addOrder.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            String data = "customerName=" + URLEncoder.encode(customerName, StandardCharsets.UTF_8.toString()) +
                          "&phoneNum=" + URLEncoder.encode(phoneNum, StandardCharsets.UTF_8.toString()) +
                          "&address=" + URLEncoder.encode(address, StandardCharsets.UTF_8.toString()) +
                          "&itemOrdered=" + URLEncoder.encode(itemOrdered, StandardCharsets.UTF_8.toString()) +
                          "&totalPrice=" + URLEncoder.encode(totalPrice, StandardCharsets.UTF_8.toString()) +
                          "&status=" + URLEncoder.encode("pending", StandardCharsets.UTF_8.toString());

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Handle the response if necessary
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}