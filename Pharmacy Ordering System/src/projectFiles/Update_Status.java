package projectFiles;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONArray;
import org.json.JSONObject;


import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Update_Status extends JPanel {
	
	private JComboBox<String> cbSortStatus;
	private JTable tblStaffOrders;
	private JTextField tfName, tfItemsOrdered, tfOrderID;
	private JComboBox<String> cbUpdateStatus;
	
	public Update_Status() {
		
		setBackground(Color.WHITE);
		setBounds(174, 20, 501, 420);

    	setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sort By :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 73, 26);
		add(lblNewLabel);
		
		cbSortStatus = new JComboBox<>(new String[]{"All", "pending", "packing", "in transit", "out for delivery", "delivered"});
		cbSortStatus.setBounds(93, 13, 190, 26);
		cbSortStatus.addItemListener(null);
		((JLabel)cbSortStatus.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		add(cbSortStatus);
		
		JButton btnSort = new JButton("Apply");
		btnSort.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSort.setBounds(303, 11, 89, 27);
		add(btnSort);
		
		
		JLabel lblCustomerName = new JLabel("Customer Name :");
		lblCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCustomerName.setBounds(20, 243, 133, 26);
		add(lblCustomerName);
		
		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setBounds(165, 242, 326, 32);
		add(tfName);
		tfName.setColumns(10);
		
		tfItemsOrdered = new JTextField();
		tfItemsOrdered.setEditable(false);
		tfItemsOrdered.setColumns(10);
		tfItemsOrdered.setBounds(109, 285, 382, 32);
		add(tfItemsOrdered);
		
		JLabel lbl = new JLabel("Items:");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbl.setBounds(51, 285, 73, 26);
		add(lbl);
		
		JLabel lblProduct_1 = new JLabel("Status :");
		lblProduct_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblProduct_1.setBounds(285, 329, 64, 26);
		add(lblProduct_1);
		
		cbUpdateStatus = new JComboBox();
		cbUpdateStatus.setModel(new DefaultComboBoxModel(new String[] {"PENDING", "PACKING", "TRANSIT", "DELIVERED", "CANCELED"}));
		cbUpdateStatus.setBounds(358, 331, 133, 26);
		((JLabel)cbUpdateStatus.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		add(cbUpdateStatus);
		
		JLabel lblProduct_2 = new JLabel("Order ID :");
		lblProduct_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblProduct_2.setBounds(20, 329, 89, 26);
		add(lblProduct_2);
		
		tfOrderID = new JTextField();
		tfOrderID.setEditable(false);
		tfOrderID.setColumns(10);
		tfOrderID.setBounds(109, 328, 133, 32);
		add(tfOrderID);
		
		JButton btnUpdateStatus = new JButton("Update");
		btnUpdateStatus.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdateStatus.setBounds(153, 383, 89, 27);
		add(btnUpdateStatus);
		
		JButton btnClearTextFields = new JButton("Clear");
		btnClearTextFields.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClearTextFields.setBounds(260, 383, 89, 27);
		add(btnClearTextFields);
		
		tblStaffOrders = new JTable();
		tblStaffOrders.setBounds(10, 48, 481, 185);
		
		add(tblStaffOrders);
		
		// Set up the table
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Order ID", "Customer Name", "Phone Number", "Address", "Items Ordered", "Status", "Total Price"}, 0);
		tblStaffOrders.setModel(model);
		tblStaffOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(tblStaffOrders, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setBounds(10, 48, 481, 185);
		add(scrollPane);
		
		new Thread(new Runnable() {
            @Override
            public void run() {
            	loadOrders("");
            }
        }).start();
        
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String selectedStatus = cbSortStatus.getSelectedItem().toString();
                        loadOrders(selectedStatus.equals("All") ? "" : selectedStatus);
                    }
                }).start();
            }
        });
        
        setColumnWidths(tblStaffOrders);
        
     // Add table row click listener
        tblStaffOrders.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblStaffOrders.getSelectedRow();
                tfName.setText(model.getValueAt(selectedRow, 1).toString());
                tfItemsOrdered.setText(model.getValueAt(selectedRow, 4).toString());
                tfOrderID.setText(model.getValueAt(selectedRow, 0).toString());
                cbUpdateStatus.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
            }
        });
        
        btnUpdateStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        updateOrderStatus();
                    }
                }).start();
            }
        });

        // Add clear button listener
        btnClearTextFields.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearTextFields();
            }
        });

	}
	
	private void setColumnWidths(JTable table) {
	    TableColumn column;

	    column = table.getColumnModel().getColumn(0); // Order ID
	    column.setPreferredWidth(55);
	    column.setMinWidth(55);
	    column.setMaxWidth(55);

	    column = table.getColumnModel().getColumn(1); // Phone Number
	    column.setPreferredWidth(100);
	    column.setMinWidth(100);
	    column.setMaxWidth(100);

	    column = table.getColumnModel().getColumn(2); // Customer Name
	    column.setPreferredWidth(100);
	    column.setMinWidth(100);
	    column.setMaxWidth(100);

	    column = table.getColumnModel().getColumn(3); // Address
	    column.setPreferredWidth(100);
	    column.setMinWidth(100);
	    column.setMaxWidth(100);

	    column = table.getColumnModel().getColumn(4); // Items Ordered
	    column.setPreferredWidth(250);
	    column.setMinWidth(250);
	    column.setMaxWidth(250);

	    column = table.getColumnModel().getColumn(5); // Status
	    column.setPreferredWidth(90);
	    column.setMinWidth(90);
	    column.setMaxWidth(90);
	    column.setCellRenderer(new BoldCenterRenderer());

	    column = table.getColumnModel().getColumn(6); // Total Price
	    column.setPreferredWidth(60);
	    column.setMinWidth(60);
	    column.setMaxWidth(60);
	}
	
	private void updateOrderStatus() {
	    String orderID = tfOrderID.getText();
	    String newStatus = cbUpdateStatus.getSelectedItem().toString();
	    String customerName = tfName.getText();
	    String itemsOrdered = tfItemsOrdered.getText();
	    
	    // Check if any of the required fields are empty
	    if (orderID.isEmpty() || customerName.isEmpty() || itemsOrdered.isEmpty()) {
	        SwingUtilities.invokeLater(() -> {
	            JOptionPane.showMessageDialog(this, "All fields must be filled to update the order status.", "Error", JOptionPane.ERROR_MESSAGE);
	        });
	        return;
	    }
	    
	    try {
	        URL url = new URL("http://localhost/webServiceProjectDAD/updateOrderStatus.php");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json; utf-8");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setDoOutput(true);

	        String jsonInputString = String.format("{\"orderID\": \"%s\", \"status\": \"%s\"}", orderID, newStatus);

	        try (OutputStream os = conn.getOutputStream()) {
	            byte[] input = jsonInputString.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        int responseCode = conn.getResponseCode();

	        SwingUtilities.invokeLater(() -> {
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                JOptionPane.showMessageDialog(this, "Order status updated successfully!");
	                
	                new Thread(new Runnable() {
	                    @Override
	                    public void run() {
	                        String selectedStatus = cbSortStatus.getSelectedItem().toString();
	                        loadOrders(selectedStatus.equals("All") ? "" : selectedStatus);
	                    }
	                }).start();
	                
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to update order status.");
	            }
	        });

	    } catch (Exception e) {
	        e.printStackTrace();
	        SwingUtilities.invokeLater(() -> {
	            JOptionPane.showMessageDialog(this, "An error occurred while updating the order status.", "Error", JOptionPane.ERROR_MESSAGE);
	        });
	    }
	}
	
	private void loadOrders(String status) {
	    try {
	        String urlString = "http://localhost/webServiceProjectDAD/getAllOrders.php";
	        if (!status.equals("") && !status.equals("All")) {
	            String encodedStatus = URLEncoder.encode(status, StandardCharsets.UTF_8.toString());
	            urlString += "?status=" + encodedStatus;
	        }
	        URL url = new URL(urlString);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");

	        int responseCode = conn.getResponseCode();
	        BufferedReader in;
	        if (responseCode == 200) {
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }

	        String inputLine;
	        StringBuilder content = new StringBuilder();

	        while ((inputLine = in.readLine()) != null) {
	            content.append(inputLine);
	        }
	        in.close();
	        conn.disconnect();

	        if (responseCode == 200) {
	            JSONObject response = new JSONObject(content.toString());
	            JSONArray orders = response.getJSONArray("orders");
	            DefaultTableModel model = (DefaultTableModel) tblStaffOrders.getModel();
	            model.setRowCount(0); // Clear existing rows

	            for (int i = 0; i < orders.length(); i++) {
	                JSONObject order = orders.getJSONObject(i);
	                int orderId = order.getInt("orderID");
	                String customerName = order.getString("customerName");
	                String phoneNum = order.getString("phoneNum");
	                String address = order.getString("address");
	                String items = order.getString("itemOrdered");
	                String orderStatus = order.getString("status");
	                double price = order.getDouble("totalPrice");

	                // Add the data in the correct order
	                model.addRow(new Object[]{orderId, customerName, phoneNum, address, items, orderStatus, String.format("%.2f", price)});
	            }

	            // Set column widths after data is loaded
	            setColumnWidths(tblStaffOrders);

	        } else {
	            try {
	                JSONObject errorResponse = new JSONObject(content.toString());
	                JOptionPane.showMessageDialog(this, "Error: " + errorResponse.getString("error"), "Error", JOptionPane.ERROR_MESSAGE);
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(this, "Error: " + content.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Failed to load orders.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void clearTextFields() {
        tfName.setText("");
        tfItemsOrdered.setText("");
        tfOrderID.setText("");
    }

    private static class BoldRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setFont(c.getFont().deriveFont(Font.BOLD));
            return c;
        }
    }

    private static class BoldCenterRenderer extends DefaultTableCellRenderer {
        public BoldCenterRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setFont(getFont().deriveFont(Font.BOLD));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
            c.setFont(c.getFont().deriveFont(Font.BOLD));
            return c;
        }
    }
}