package application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.CRUD;

public class MainLogin extends JFrame {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField adminId;
	private JPasswordField adminKey;
	private JTextField studentId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLogin mainLoginFrame = new MainLogin();
					mainLoginFrame.setVisible(true);
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
	public MainLogin() {
		setTitle("Feedback System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(646, 421);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 640, 387);
		contentPane.add(tabbedPane);
		
		JPanel adminTab = new JPanel();
		tabbedPane.addTab("Admin", null, adminTab, null);
		adminTab.setLayout(null);
		
		JLabel idLabel = new JLabel("Admin ID");
		idLabel.setBounds(113, 79, 95, 53);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		adminTab.add(idLabel);
		
		JLabel passLabel = new JLabel("Admin Key");
		passLabel.setBounds(113, 154, 95, 53);
		passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		adminTab.add(passLabel);
		
		adminId = new JTextField();
		adminId.setBounds(220, 85, 253, 42);
		adminId.setBackground(Color.LIGHT_GRAY);
		adminId.setForeground(Color.BLACK);
		adminId.setToolTipText("Enter Admin Id");
		adminId.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		adminId.setHorizontalAlignment(SwingConstants.CENTER);
		adminTab.add(adminId);
		adminId.setColumns(10);
		
		adminKey = new JPasswordField();
		adminKey.setBounds(220, 163, 253, 39);
		adminKey.setBackground(Color.LIGHT_GRAY);
		adminKey.setHorizontalAlignment(SwingConstants.CENTER);
		adminKey.setToolTipText("Enter Admin Key");
		adminTab.add(adminKey);
		
		JButton adminLogin = new JButton("Login");
		adminLogin.addActionListener((ActionEvent e)-> {
				String adminId = "";
				String adminPass="";
				adminId = this.adminId.getText();
				adminPass = String.valueOf(this.adminKey.getPassword());
				//JDBC code
				if(CRUD.loginAdmin(adminId, adminPass)) {
					dispose();
					new AdminActivity().setVisible(true);
					System.gc();
					
				}
				else
				{
					new JOptionPane().showMessageDialog(this, "Invalid Credentials!\n"
							+ "Please Enter Correct Credentials", "ERROR", EXIT_ON_CLOSE);
					System.gc();
				}
				
		});
		adminLogin.setBounds(292, 243, 97, 25);
		adminLogin.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		adminTab.add(adminLogin);
		
		JPanel studentTab = new JPanel();
		tabbedPane.addTab("Student", null, studentTab, null);
		studentTab.setLayout(null);
		
		JLabel idStudent = new JLabel("Student Id");
		idStudent.setBounds(106, 147, 104, 40);
		idStudent.setHorizontalAlignment(SwingConstants.CENTER);
		idStudent.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		studentTab.add(idStudent);
		
		studentId = new JTextField();
		studentId.setToolTipText("Enter Student Id");
		studentId.setHorizontalAlignment(SwingConstants.CENTER);
		studentId.setForeground(Color.BLACK);
		studentId.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		studentId.setColumns(10);
		studentId.setBackground(Color.LIGHT_GRAY);
		studentId.setBounds(222, 147, 253, 42);
		studentTab.add(studentId);
		
		JButton studentLogin = new JButton("Login");
		studentLogin.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		studentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentId = "";
				studentId = MainLogin.this.studentId.getText();
				String result = CRUD.loginStudent(studentId);
				if(result==null) {
					new JOptionPane().showMessageDialog(MainLogin.this, "Invalid Credentials!\n"
							+ "Please Enter Correct StudentId", "ERROR", EXIT_ON_CLOSE);
				}
				else
				{
					dispose();
					try {
						new StudentActivity(result).setVisible(true);
						System.gc();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.gc();
				}
			}
		});
		studentLogin.setBounds(296, 223, 111, 31);
		studentTab.add(studentLogin);
	}
}
