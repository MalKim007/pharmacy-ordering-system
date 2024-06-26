package projectFiles;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DashboardStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardStaff frame = new DashboardStaff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DashboardStaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(104, 104, 255));
		panel.setBounds(10, 20, 154, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(42, 26, 64, 64);
		lblNewLabel.setIcon(new ImageIcon(DashboardStaff.class.getResource("/Images/cropped_image.png")));
		panel.add(lblNewLabel);
		
		JPanel panelChange = new JPanel();
		panelChange.setBackground(new Color(104, 104, 255));
		panelChange.setBounds(174, 20, 501, 420);
		panelChange.setLayout(new BorderLayout());
		getContentPane().add(panelChange);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(65, 105, 225));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(DashboardStaff.class.getResource("/Images/6760169 (1).jpg")));
		panelChange.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Profile Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(29, 100, 125, 13);
		panel.add(lblNewLabel_1);
		
		JButton btnButtonCheck = new JButton("Check Order");
		btnButtonCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.removeAll();
                panelChange.add(new Check_Status());
                panelChange.revalidate();
                panelChange.repaint();
			}
		});
		btnButtonCheck.setBounds(0, 157, 154, 38);
		panel.add(btnButtonCheck);
		
		JButton btnButtonUpdate = new JButton("Update Status\r\n\r\n");
		btnButtonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.removeAll();
                panelChange.add(new Update_Status());
                panelChange.revalidate();
                panelChange.repaint();
			}
		});
		btnButtonUpdate.setBounds(0, 205, 154, 38);
		panel.add(btnButtonUpdate);
		
		JButton btnButtonReport = new JButton("Analysis Report");
		btnButtonReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.removeAll();
                panelChange.add(new Report_Sales());
                panelChange.revalidate();
                panelChange.repaint();
			}
		});
		btnButtonReport.setBounds(0, 253, 154, 38);
		panel.add(btnButtonReport);
		
		JButton btnButtonSignout = new JButton("Sign Out");
		btnButtonSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard.main(null);
			}
		});
		btnButtonSignout.setBounds(0, 301, 154, 38);
		panel.add(btnButtonSignout);
	}
}
