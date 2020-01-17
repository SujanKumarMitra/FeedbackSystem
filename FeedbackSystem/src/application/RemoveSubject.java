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

public class RemoveSubject extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveSubject frame = new RemoveSubject();
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
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public RemoveSubject() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 853, 423);
		setTitle("Remove Subject");
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose Subject to Remove");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 60, 201, 32);
		contentPane.add(lblNewLabel);
		
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		Object[] subjectObj = CRUD.getSubjects().toArray();
		String[] subjectNames = Arrays.copyOf(subjectObj, subjectObj.length,String[].class);
		JComboBox comboBox = new JComboBox(subjectNames);
		comboBox.setRenderer(renderer);
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		comboBox.setBounds(251, 61, 360, 32);
		contentPane.add(comboBox);
		
		JButton removeButton = new JButton("Remove Subject");
		removeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeButton.setBounds(335, 190, 146, 42);
		contentPane.add(removeButton);
		removeButton.addActionListener((ActionEvent e)-> {
			String subjectName = "";
			subjectName = comboBox.getSelectedItem().toString();
			CRUD.deleteSubject(subjectName);
			new JOptionPane().showMessageDialog(this, "Subject Deleted Successfully");
			comboBox.removeItem(subjectName);
		});
		
	}

}
