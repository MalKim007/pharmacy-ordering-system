package projectFiles;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;


public class CheckOrder extends JPanel {

	public CheckOrder() {
		
		setBackground(Color.WHITE);
        // Set up the panel
    	setBounds(174, 20, 501, 420);
    	setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sort By :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 75, 26);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ALL", "PENDING", "PACKING", "TRANSIT", "DELIVERED", "CANCELED"}));
		comboBox.setBounds(82, 13, 201, 26);
		comboBox.addItemListener(null);
		((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		add(comboBox);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(303, 11, 89, 27);
		add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnReset.setBounds(402, 11, 89, 27);
		add(btnReset);
		
		JTable table = new JTable();
		table.setBounds(10, 61, 481, 348);
		add(table);
	}
}