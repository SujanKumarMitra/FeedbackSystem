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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class RemoveStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveStudent frame = new RemoveStudent();
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
	@SuppressWarnings("static-access")
	public RemoveStudent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 928, 419);
		setResizable(false);
		setTitle("Remove Student");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Student's Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(38, 66, 172, 37);
		contentPane.add(lblNewLabel);
		
		studentTextField = new JTextField();
		studentTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		studentTextField.setHorizontalAlignment(SwingConstants.CENTER);
		studentTextField.setBackground(Color.LIGHT_GRAY);
		studentTextField.setBounds(239, 66, 493, 37);
		contentPane.add(studentTextField);
		studentTextField.setColumns(10);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeButton.setBounds(400, 150, 138, 37);
		contentPane.add(removeButton);
		removeButton.addActionListener((ActionEvent e)-> {
			String studentName = "";
			studentName = studentTextField.getText();
			if(studentName.equals("")) {
				new JOptionPane().showMessageDialog(this, "Nothing Typed!", "ERROR", EXIT_ON_CLOSE);
			}
			else if(!CRUD.isStudentPresent(studentName)) {
				new JOptionPane().showMessageDialog(this, "No such student exists !", "ERROR", EXIT_ON_CLOSE);
			}
			else {
				int a =new JOptionPane().showConfirmDialog(this, "Are You sure you want to delete this information?");
				if(a==JOptionPane.YES_OPTION) {
					CRUD.removeStudent(studentName);
					new JOptionPane().showMessageDialog(this, "Student Record Deleted Successfully");
					dispose();
					System.gc();
				}
			}
		});
	}

}
