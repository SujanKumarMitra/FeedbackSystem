package application;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.CRUD;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class StudentActivity extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
				try {
					StudentActivity frame = new StudentActivity("Student");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public StudentActivity(String name) throws IOException {
		
		HashMap<String,String> map = CRUD.fetchStudent(name);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(1035, 587);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Student Activity");
		contentPane.setLayout(null);
		
		JLabel studentIcon = new JLabel("");
		studentIcon.setHorizontalAlignment(SwingConstants.CENTER);
		studentIcon.setBounds(821, 28, 161, 168);
		contentPane.add(studentIcon);
		studentIcon.setIcon(new ImageIcon("StudentPhoto.png"));
		
		JLabel welcomeMsg = new JLabel("Welcome "+map.get("Name")+"!");
		welcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMsg.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		welcomeMsg.setBounds(79, 28, 556, 53);
		contentPane.add(welcomeMsg);
		
		Object[] obj = CRUD.getSubjects(map.get("Name")).toArray();
		String[] subjects = Arrays.copyOf(obj, obj.length,String[].class);
		comboBox = new JComboBox(subjects);
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		comboBox.setBounds(143, 171, 422, 36);
		comboBox.setSelectedItem(null);
		contentPane.add(comboBox);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		comboBox.setRenderer(renderer);
		
		
		JLabel facultyLabel = new JLabel("Choose a Subject to give Feedback");
		facultyLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		facultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		facultyLabel.setBounds(155, 111, 394, 28);
		contentPane.add(facultyLabel);
		
		
		JLabel facultyIcon = new JLabel("No Subject Selected");
		facultyIcon.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		facultyIcon.setHorizontalAlignment(SwingConstants.CENTER);
		facultyIcon.setBounds(237, 272, 225, 225);
		contentPane.add(facultyIcon);
		comboBox.addActionListener((ActionEvent e)-> {
			CRUD.getFacultyImage(comboBox.getSelectedItem().toString());
			facultyIcon.setIcon(new ImageIcon("FacultyPhoto.png"));
		});
		
		JButton feedback = new JButton("Give Feedback");
		feedback.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		feedback.setBounds(700, 366, 144, 36);
		contentPane.add(feedback);
		
		JLabel lblNewLabel = new JLabel("Faculty Photo");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(295, 220, 113, 24);
		contentPane.add(lblNewLabel);
		feedback.addActionListener((ActionEvent e) ->{
			if(comboBox.getSelectedItem()==null) {
				new JOptionPane().showMessageDialog(this, 
						"No Subject Selected",
						"ERROR", EXIT_ON_CLOSE);
			}
			else if(!CRUD.isResponseGiven(map.get("Student Id").toString(), comboBox.getSelectedItem().toString())) {
				String[] arg = {CRUD.getFacultyName(comboBox.getSelectedItem().toString()),map.get("Student Id")};
				FeedbackPage.main(arg);
			}
			else {
				new JOptionPane().showMessageDialog(this, 
						"You already have given feedback on this subject",
						"ERROR", EXIT_ON_CLOSE);
			}
			
		});
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new MainLogin().setVisible(true);
				System.gc();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
