package application;


import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import database.CRUD;

import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class AddStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sNametxt;
	private JTextField sIdtxt;
	private JFileChooser j;
	String filePath="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
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
	public AddStudent() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Add New Student");
		setSize(868, 545);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel sName = new JLabel("Student's Name");
		sName.setHorizontalAlignment(SwingConstants.LEFT);
		sName.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		sName.setBounds(131, 105, 123, 37);
		contentPane.add(sName);
		
		JLabel sId = new JLabel("Student's ID");
		sId.setHorizontalAlignment(SwingConstants.LEFT);
		sId.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		sId.setBounds(131, 151, 123, 37);
		contentPane.add(sId);
		
		JLabel sSem = new JLabel("Semester");
		sSem.setHorizontalAlignment(SwingConstants.LEFT);
		sSem.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		sSem.setBounds(131, 197, 123, 37);
		contentPane.add(sSem);
		
		sNametxt = new JTextField();
		sNametxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		sNametxt.setHorizontalAlignment(SwingConstants.CENTER);
		sNametxt.setBackground(Color.LIGHT_GRAY);
		sNametxt.setBounds(295, 110, 267, 29);
		contentPane.add(sNametxt);
		sNametxt.setColumns(10);
		
		sIdtxt = new JTextField();
		sIdtxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		sIdtxt.setHorizontalAlignment(SwingConstants.CENTER);
		sIdtxt.setBackground(Color.LIGHT_GRAY);
		sIdtxt.setColumns(10);
		sIdtxt.setBounds(295, 159, 267, 29);
		contentPane.add(sIdtxt);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(374, 205, 123, 29);
		contentPane.add(comboBox);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		comboBox.setRenderer(renderer);
		
		JLabel lblNewLabel = new JLabel("Enter the following information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(69, 29, 254, 37);
		contentPane.add(lblNewLabel);
		
		JLabel imgLabel = new JLabel("Add Image");
		imgLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBounds(650, 29, 200, 200);
		contentPane.add(imgLabel);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnNewButton.setBounds(695, 240, 125, 29);
		contentPane.add(btnNewButton);
		
		JButton addStudent = new JButton("Add Student");
		addStudent.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		addStudent.setBounds(365, 369, 132, 37);
		contentPane.add(addStudent);
		
		JProgressBar progressBar = new JProgressBar(0,100);
		progressBar.setBounds(295, 449, 277, 29);
		progressBar.setVisible(false);
		contentPane.add(progressBar);
		addStudent.addActionListener((ActionEvent e)->{
			int a =new JOptionPane().showConfirmDialog(this, "Are You sure about the details? If yes then proceed, else review details");
			if(a==JOptionPane.YES_OPTION) {
				String studentName = "";
				
				int studentSemester = 1;
				String studentId = "";
				studentId = sIdtxt.getText();
				studentName = sNametxt.getText();
				studentSemester = Integer.parseInt((String)comboBox.getSelectedItem());
				if(studentName.equals("")||studentId.equals("")||filePath.equals("")) {
					new JOptionPane().showMessageDialog(this, "All Fields should not be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					progressBar.setVisible(true);
					if(CRUD.addStudent(studentName, studentId, studentSemester, filePath,progressBar)) {
						new JOptionPane().showMessageDialog(this, "Student Information Added Successfully");
						this.dispose();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			}
		});
		btnNewButton.addActionListener((ActionEvent e)-> {
				j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				j.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only jpeg or png files", "jpeg","png"); 
	            j.addChoosableFileFilter(restrict); 
	            // invoke the showsOpenDialog function to show the save dialog 
	            int r = j.showOpenDialog(null); 
	  
	            // if the user selects a file 
	            if (r == JFileChooser.APPROVE_OPTION) 
	            {
	            	filePath = j.getSelectedFile().getAbsolutePath();
	                ImageIcon image = new ImageIcon(filePath);
	                imgLabel.setIcon(image);
	                
	            } 
	            // if the user cancelled the operation 
	            else{
	            	new JOptionPane().showMessageDialog(this, 
	            			"Please choose proper image file", "Invalid Image", EXIT_ON_CLOSE);
	            }
				
		});
		
	}
}
