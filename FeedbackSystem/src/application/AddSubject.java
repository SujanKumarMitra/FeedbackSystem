package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.CRUD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;

public class AddSubject extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField subjectTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSubject frame = new AddSubject();
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
	public AddSubject() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Add Subject");
		setBounds(100, 100, 945, 438);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Subject Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 45, 225, 28);
		contentPane.add(lblNewLabel);
		
		subjectTextField = new JTextField();
		subjectTextField.setHorizontalAlignment(SwingConstants.CENTER);
		subjectTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		subjectTextField.setBackground(Color.LIGHT_GRAY);
		subjectTextField.setBounds(263, 45, 339, 26);
		contentPane.add(subjectTextField);
		subjectTextField.setColumns(10);
		
		JLabel lblAddResponsibleFaculty = new JLabel("Add Responsible Faculty");
		lblAddResponsibleFaculty.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddResponsibleFaculty.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblAddResponsibleFaculty.setBounds(12, 102, 225, 28);
		contentPane.add(lblAddResponsibleFaculty);
		
		Object[] facultyObj = CRUD.getFaculty().toArray();
		String[] facultyNames = Arrays.copyOf(facultyObj, facultyObj.length,String[].class);
		JComboBox facultyComboBox = new JComboBox(facultyNames);
		facultyComboBox.setBounds(263, 102, 339, 26);
		facultyComboBox.setRenderer(renderer);
		facultyComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		contentPane.add(facultyComboBox);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		submitButton.setBounds(376, 235, 105, 28);
		contentPane.add(submitButton);
		
		JLabel lblNewLabel_1 = new JLabel("Add Semester");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(69, 163, 105, 28);
		contentPane.add(lblNewLabel_1);
		
		
		JComboBox semComboBox = new JComboBox();
		semComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		semComboBox.setRenderer(renderer);
		semComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		semComboBox.setBounds(367, 167, 149, 22);
		contentPane.add(semComboBox);
		submitButton.addActionListener((ActionEvent e)-> {
			String subjectName = "";
			String facultyName = "";
			int semester = 0;
			subjectName = subjectTextField.getText();
			if(subjectName.equals("")) {
				new JOptionPane().showMessageDialog(this, "Nothing Typed!", "ERROR", EXIT_ON_CLOSE);
			}
			else {
				facultyName = facultyComboBox.getSelectedItem().toString();
				semester = Integer.parseInt(semComboBox.getSelectedItem().toString());
				CRUD.addSubject(subjectName,facultyName,semester);
				new JOptionPane().showMessageDialog(this, "Subject Added Successfully!");
				dispose();
				System.gc();
			}
		});
	}

}
