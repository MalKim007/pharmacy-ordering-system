package projectFiles;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Report_Sales extends JPanel {
	
	private JLabel lblTotal_Item, lblTotalDelivered, lblTotal_InProcess, lblTotalUsers, lblTotalSales;
	
    public Report_Sales() {
    	setBackground(new Color(119, 136, 153));
        // Set up the panel
    	setBounds(174, 20, 501, 420);
    	setLayout(null);
    	
    	JLabel lblNewLabel = new JLabel("Report Sales");
    	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
    	lblNewLabel.setBounds(186, 23, 110, 33);
    	add(lblNewLabel);
    	
    	JPanel panel = new JPanel();
    	panel.setBackground(SystemColor.inactiveCaptionBorder);
    	panel.setBounds(20, 77, 451, 308);
    	add(panel);
    	panel.setLayout(null);
    	
    	JPanel panel_1 = new JPanel();
    	panel_1.setBackground(new Color(173, 216, 230));
    	panel_1.setBounds(49, 37, 160, 60);
    	panel.add(panel_1);
    	panel_1.setLayout(null);
    	
    	JLabel lblNewLabel_1 = new JLabel("Total Item");
    	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblNewLabel_1.setBounds(10, 10, 66, 13);
    	panel_1.add(lblNewLabel_1);
    	
    	lblTotal_Item = new JLabel("0");
    	lblTotal_Item.setFont(new Font("Tahoma", Font.BOLD, 25));
    	lblTotal_Item.setBounds(53, 20, 107, 40);
    	panel_1.add(lblTotal_Item);
    	
    	JPanel panel_1_1 = new JPanel();
    	panel_1_1.setLayout(null);
    	panel_1_1.setBackground(new Color(152, 251, 152));
    	panel_1_1.setBounds(49, 124, 127, 60);
    	panel.add(panel_1_1);
    	
    	JLabel lblNewLabel_1_1 = new JLabel("Total Delivered");
    	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblNewLabel_1_1.setBounds(10, 10, 107, 13);
    	panel_1_1.add(lblNewLabel_1_1);
    	
    	lblTotalDelivered = new JLabel("0");
    	lblTotalDelivered.setFont(new Font("Tahoma", Font.BOLD, 20));
    	lblTotalDelivered.setBounds(47, 20, 80, 40);
    	panel_1_1.add(lblTotalDelivered);
    	
    	JPanel panel_1_3 = new JPanel();
    	panel_1_3.setLayout(null);
    	panel_1_3.setBackground(new Color(238, 232, 170));
    	panel_1_3.setBounds(49, 206, 127, 60);
    	panel.add(panel_1_3);
    	
    	JLabel lblNewLabel_1_3 = new JLabel("Total Inprocess");
    	lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblNewLabel_1_3.setBounds(10, 10, 107, 13);
    	panel_1_3.add(lblNewLabel_1_3);
    	
    	lblTotal_InProcess = new JLabel("0");
    	lblTotal_InProcess.setFont(new Font("Tahoma", Font.BOLD, 20));
    	lblTotal_InProcess.setBounds(49, 20, 78, 40);
    	panel_1_3.add(lblTotal_InProcess);
    	
    	JPanel panel_1_1_1 = new JPanel();
    	panel_1_1_1.setLayout(null);
    	panel_1_1_1.setBackground(SystemColor.textHighlightText);
    	panel_1_1_1.setBounds(211, 122, 213, 144);
    	panel.add(panel_1_1_1);
    	
    	JLabel lblNewLabel_1_1_1 = new JLabel("Total Sales");
    	lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    	lblNewLabel_1_1_1.setBounds(49, 10, 119, 25);
    	panel_1_1_1.add(lblNewLabel_1_1_1);
    	
    	lblTotalSales = new JLabel("RM 0.00");
    	lblTotalSales.setFont(new Font("Tahoma", Font.BOLD, 25));
    	lblTotalSales.setBounds(45, 65, 158, 40);
    	panel_1_1_1.add(lblTotalSales);
    	
    	JPanel panel_1_2 = new JPanel();
    	panel_1_2.setBounds(264, 37, 160, 60);
    	panel.add(panel_1_2);
    	panel_1_2.setLayout(null);
    	panel_1_2.setBackground(new Color(255, 99, 71));
    	
    	JLabel lblNewLabel_1_2 = new JLabel("Total User");
    	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
    	lblNewLabel_1_2.setBounds(10, 10, 66, 13);
    	panel_1_2.add(lblNewLabel_1_2);
    	
    	lblTotalUsers = new JLabel("0");
    	lblTotalUsers.setFont(new Font("Tahoma", Font.BOLD, 25));
    	lblTotalUsers.setBounds(67, 20, 93, 40);
    	panel_1_2.add(lblTotalUsers);
    	
    	JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(200, 350, 100, 30);
        btnRefresh.addActionListener(e -> updateReport());
        add(btnRefresh);

        
        
        new Thread(new Runnable() {
            @Override
            public void run() {
            	updateReport();
            }
        }).start();
    }
    
    private void updateReport() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String totalItems = getReportData("totalItems", "totalItems");
                String totalDelivered = getReportData("totalDelivered", "totalDelivered");
                String totalInProcess = getReportData("totalInProcess", "totalInProcess");
                String totalUsers = getReportData("totalUsers", "totalUsers");
                String totalSales = "RM " + getReportData("totalSales", "totalSales");

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        lblTotal_Item.setText(totalItems);
                        lblTotalDelivered.setText(totalDelivered);
                        lblTotal_InProcess.setText(totalInProcess);
                        lblTotalUsers.setText(totalUsers);
                        lblTotalSales.setText(totalSales);
                    }
                });
            }
        }).start();
    }

    private String getReportData(String action, String key) {
        try {
            URL url = new URL("http://localhost/webServiceProjectDAD/report.php?action=" + action);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                in.close();

                // Log the response content
                System.out.println("Response for action " + action + ": " + content.toString());

                JSONObject jsonResponse = new JSONObject(content.toString());
                return jsonResponse.optString(key, "0");
            } else {
                System.out.println("HTTP error code: " + conn.getResponseCode());
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
