package projectFiles;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import Manage_History.BoldCenterRenderer;



public class Check_Status extends JPanel {
	
	private JComboBox<String> cbSortStatus;
	private JTable tblStaffOrders;
	
	public Check_Status() {
		
		setBackground(Color.WHITE);
        // Set up the panel
    	setBounds(174, 20, 529, 420);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sort By :");
		lblNewLabel.setBounds(10, 11, 75, 26);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		add(lblNewLabel);
		
		cbSortStatus = new JComboBox<>(new String[]{"All", "pending", "packing", "in transit", "out for delivery", "delivered"});
		cbSortStatus.setBounds(82, 13, 201, 26);
		cbSortStatus.addItemListener(null);
		((JLabel)cbSortStatus.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		add(cbSortStatus);
		
		JButton btnSort = new JButton("Apply");
		btnSort.setBounds(303, 11, 89, 27);
		btnSort.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(btnSort);
		
		tblStaffOrders = new JTable();
		tblStaffOrders.setBounds(10, 83, 514, 284);
		
		add(tblStaffOrders);
			
		// Set up the table
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Order ID", "Customer Name", "Phone Number", "Address", "Items Ordered", "Status", "Total Price"}, 0);
		tblStaffOrders.setModel(model);
		tblStaffOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(tblStaffOrders, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setBounds(10, 83, 481, 284);
		add(scrollPane);
        
        
     // Load all orders initially
        
        
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