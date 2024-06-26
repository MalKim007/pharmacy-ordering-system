package projectFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardCustomer frame = new DashboardCustomer();
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
	public DashboardCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 20, 154, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(36, 55, 64, 64);
		lblNewLabel.setIcon(new ImageIcon(DashboardCustomer.class.getResource("/Images/cropped_image.png")));
		panel.add(lblNewLabel);
		
		JLabel lvlWelcome = new JLabel("");
		lvlWelcome.setText("Welcome " + SessionManager.getUsername());
		lvlWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lvlWelcome.setBounds(19, 129, 125, 13);
		panel.add(lvlWelcome);
		
		JPanel panelChange = new JPanel();
		panelChange.setBackground(new Color(224, 255, 255));
		panelChange.setBounds(174, 20, 501, 420);
		panelChange.setLayout(new BorderLayout());
		getContentPane().add(panelChange);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DashboardCustomer.class.getResource("/Images/2198063__1_-removebg-preview.png")));
		panelChange.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JButton btnButtonChooseItem = new JButton("Choose Item");
		btnButtonChooseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.removeAll();
                panelChange.add(new ChooseItem());
                panelChange.revalidate();
                panelChange.repaint();
			}
		});
		btnButtonChooseItem.setBounds(0, 178, 154, 38);
		panel.add(btnButtonChooseItem);
		
		JButton btnButtonHistory = new JButton("Check History");
		btnButtonHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.removeAll();
                panelChange.add(new Manage_History());
                panelChange.revalidate();
                panelChange.repaint();
			}
		});
		btnButtonHistory.setBounds(0, 226, 154, 38);
		panel.add(btnButtonHistory);
		
		JButton btnButtonSignout = new JButton("Sign Out");
		btnButtonSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionManager.clearSession();
				dispose();
				Dashboard.main(null);
			}
		});
		btnButtonSignout.setBounds(0, 274, 154, 38);
		panel.add(btnButtonSignout);
		
		
	}
	
	
}
