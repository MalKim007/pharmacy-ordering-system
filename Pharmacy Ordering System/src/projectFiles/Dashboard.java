package projectFiles;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Dashboard extends JFrame implements ActionListener {

    private JFrame frmSupplementStockManagement;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard window = new Dashboard();
                    window.frmSupplementStockManagement.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Dashboard() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmSupplementStockManagement = new JFrame();
        frmSupplementStockManagement.setTitle("Supplement Stock Management System");
        frmSupplementStockManagement.setBounds(100, 100, 600, 500);
        frmSupplementStockManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSupplementStockManagement.getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        panel_1.setBounds(0, 0, 209, 463);
        frmSupplementStockManagement.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-69, 0, 278, 463);
        panel_1.add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/BackgorundDashboard.jpg")));

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.menu);
        panel.setBounds(212, 0, 374, 463);
        frmSupplementStockManagement.getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(SystemColor.controlHighlight);
        panel_2.setBounds(10, 10, 354, 63);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_1_1 = new JLabel("Pharmacy Ordering");
        lblNewLabel_1_1.setBounds(76, 10, 192, 22);
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setForeground(Color.RED);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1_1.setBackground(Color.DARK_GRAY);
        panel_2.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1 = new JLabel("System");
        lblNewLabel_1.setBounds(142, 31, 69, 22);
        panel_2.add(lblNewLabel_1);
        lblNewLabel_1.setBackground(Color.DARK_GRAY);
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(SystemColor.controlHighlight);
        panel_3.setBounds(10, 90, 354, 363);
        panel.add(panel_3);
        panel_3.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Username :");
        lblNewLabel_2.setBounds(17, 128, 67, 15);
        panel_3.add(lblNewLabel_2);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));

        JLabel lblNewLabel_1_1_1 = new JLabel("Login First!");
        lblNewLabel_1_1_1.setBounds(105, 33, 104, 22);
        panel_3.add(lblNewLabel_1_1_1);
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setForeground(SystemColor.activeCaption);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(105, 121, 227, 31);
        panel_3.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(104, 193, 228, 31);
        panel_3.add(textFieldPassword);
        textFieldPassword.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Password :");
        lblNewLabel_2_1.setBounds(17, 196, 77, 23);
        panel_3.add(lblNewLabel_2_1);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBounds(22, 283, 310, 45);
        panel_3.add(btnNewButton);
        btnNewButton.setBackground(SystemColor.textHighlight);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();
            }
        });
    }

    private void login() {
    	String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        try {
            String apiURL = "http://localhost/webServiceProjectDAD/login.php";
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            
            String urlParameters = "username=" + username + "&password=" + password;
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = urlParameters.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line.trim());
            }
            in.close();
            conn.disconnect();

            System.out.println("Server Response: " + content.toString());

            JSONObject jsonResponse = new JSONObject(content.toString());
            String status = jsonResponse.getString("status");

            if (status.equals("success")) {
            	
            	SessionManager.setUsername(username);
            	
                String role = jsonResponse.getString("role");
                
                frmSupplementStockManagement.setVisible(false);
                
                if (role.equals("customer")) {
                    DashboardCustomer DC = new DashboardCustomer();
                    DC.setVisible(true);
                } else if (role.equals("staff")) {
                    DashboardStaff DS = new DashboardStaff();
                    DS.setVisible(true);
                }
            } else {
                String message = jsonResponse.getString("message");
                JOptionPane.showMessageDialog(frmSupplementStockManagement, message, "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
