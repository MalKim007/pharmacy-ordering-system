package projectFiles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manage_History extends JPanel {
	private JTextField tfTotalPrice;
	private JTable tblCustomerOrders;
	private JComboBox<String> cbSortStatus;
	
    public Manage_History() {
    	setBackground(SystemColor.inactiveCaption);
        // Set up the panel
    	setBounds(174, 20, 501, 420);
    	setLayout(null);
    	
    	cbSortStatus = new JComboBox<>(new String[]{"All", "pending", "packing", "in transit", "out for delivery", "delivered"});
    	
    	JLabel lblNewLabel = new JLabel("History Order");
    	lblNewLabel.setBounds(140, 10, 188, 28);
    	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
    	add(lblNewLabel);
    	
    	cbSortStatus.setBounds(150, 54, 253, 28);
    	cbSortStatus.setBackground(SystemColor.window);
    	add(cbSortStatus);
    	
    	JLabel lblNewLabel_1 = new JLabel("Sort Order by Status:");
    	lblNewLabel_1.setBounds(15, 58, 127, 20);
    	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
    	add(lblNewLabel_1);
    	
    	JButton btnSort = new JButton("");

    	btnSort.setBounds(413, 53, 36, 29);
    	btnSort.setIcon(new ImageIcon(Manage_History.class.getResource("/Images/search_163723 (1).png")));
    	add(btnSort);
    	
    	tfTotalPrice = new JTextField();
    	tfTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	tfTotalPrice.setBounds(70, 369, 108, 28);
    	add(tfTotalPrice);
    	tfTotalPrice.setEditable(false);
    	tfTotalPrice.setColumns(10);
    	
    	JLabel lblNewLabel_2 = new JLabel("RM");
    	lblNewLabel_2.setBounds(34, 376, 45, 13);
    	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
    	add(lblNewLabel_2);
    	
    	JLabel lblNewLabel_2_1 = new JLabel("Total Spending:");
    	lblNewLabel_2_1.setBounds(70, 352, 99, 13);
    	lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
    	lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
    	add(lblNewLabel_2_1);
    	
    	tblCustomerOrders = new JTable();
    	tblCustomerOrders.setBounds(1, 25, 499, 0);
    	add(tblCustomerOrders);
    	
    	// Set up the table
    	// Set up the table
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Order ID", "Items Ordered", "Status", "Total Price"}, 0);
        tblCustomerOrders.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tblCustomerOrders);

     // Set column widths
        setColumnWidths(tblCustomerOrders);

        // Set bounds for components
        scrollPane.setBounds(15, 106, 476, 233);
        add(scrollPane);
        
     // Load customer orders
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadCustomerOrders("");
            }
        }).start();
        
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String selectedStatus = cbSortStatus.getSelectedItem().toString();
                        loadCustomerOrders(selectedStatus.equals("All") ? "" : selectedStatus);
                    }
                }).start();
            }
        });
       
    }
   

	private void setColumnWidths(JTable table) {
        TableColumn column;

        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(55);
        column.setMinWidth(55);
        column.setMaxWidth(55);
        column.setCellRenderer(new CenterRenderer());

        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(261);
        column.setMinWidth(261);
        column.setMaxWidth(261);

        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(85);
        column.setMinWidth(85);
        column.setMaxWidth(85);
        column.setCellRenderer(new BoldCenterRenderer());

        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(80);
        column.setMinWidth(80);
        column.setMaxWidth(80);
        column.setCellRenderer(new CenterRenderer());
    }
    
    private static class CenterRenderer extends DefaultTableCellRenderer {
        public CenterRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
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
    
    private void loadCustomerOrders(String status) {
        String username = SessionManager.getUsername();
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No user logged in.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8.toString());
            String urlString = "http://localhost/webServiceProjectDAD/getCustomerOrders.php?username=" + encodedUsername;
            if (!status.equals("") && !status.equals("All")) {
                String encodedStatus = URLEncoder.encode(status, StandardCharsets.UTF_8.toString());
                urlString += "&status=" + encodedStatus;
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
                DefaultTableModel model = (DefaultTableModel) tblCustomerOrders.getModel();
                model.setRowCount(0); // Clear existing rows
                double totalPrice = 0.0;

                for (int i = 0; i < orders.length(); i++) {
                    JSONObject order = orders.getJSONObject(i);
                    int orderId = order.getInt("orderID");
                    String items = order.getString("itemOrdered");
                    String orderStatus = order.getString("status");
                    double price = order.getDouble("totalPrice");
                    totalPrice += price;

                    model.addRow(new Object[]{orderId, items, orderStatus, String.format("%.2f", price)});
                }

                tfTotalPrice.setText(String.format("%.2f", totalPrice));
            } else {
                try {
                    JSONObject errorResponse = new JSONObject(content.toString());
                    JOptionPane.showMessageDialog(this, "Note: " + errorResponse.getString("error"), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (JSONException e) {
                    JOptionPane.showMessageDialog(this, "Note: " + content.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load orders.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
