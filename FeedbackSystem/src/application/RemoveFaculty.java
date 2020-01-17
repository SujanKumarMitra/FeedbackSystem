package application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.CRUD;

public class RemoveFaculty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("rawtypes")
					RemoveFaculty frame = new RemoveFaculty(new JComboBox());
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
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public RemoveFaculty(JComboBox facultyComboBox) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 896, 508);
		setTitle("Remove Faculty");
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Object[] facultyObj = CRUD.getFaculty().toArray();
		String[] faculty = Arrays.copyOf(facultyObj, facultyObj.length,String[].class);
		comboBox = new JComboBox(faculty);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		comboBox.setRenderer(renderer);
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		comboBox.setBounds(240, 159, 433, 43);
		contentPane.add(comboBox);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeButton.setBounds(396, 291, 144, 43);
		contentPane.add(removeButton);
		removeButton.addActionListener((ActionEvent e) ->{
			int a =new JOptionPane().showConfirmDialog(this, "Are You sure you want to remove this faculty?");
			if(a==JOptionPane.YES_OPTION) {
				String facultyName = comboBox.getSelectedItem().toString();
				CRUD.removeFaculty(facultyName.toString());
				new JOptionPane().showMessageDialog(this, "Faculty removed!");
				facultyComboBox.removeItem(facultyName);
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Select Faculty to Remove");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 159, 216, 43);
		contentPane.add(lblNewLabel);
	}

}
