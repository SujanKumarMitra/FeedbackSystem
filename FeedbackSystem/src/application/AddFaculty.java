package application;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
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
import javax.swing.JComboBox;
import javax.swing.JProgressBar;

public class AddFaculty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fNametxt;
	private JFileChooser j;
	String filePath="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("rawtypes")
					AddFaculty frame = new AddFaculty(new JComboBox());
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
	public AddFaculty(JComboBox comboBox) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Add New Faculty");
		setSize(868, 545);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fName = new JLabel("Faculty's Name");
		fName.setHorizontalAlignment(SwingConstants.LEFT);
		fName.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		fName.setBounds(69, 153, 123, 37);
		contentPane.add(fName);
		
		fNametxt = new JTextField();
		fNametxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		fNametxt.setHorizontalAlignment(SwingConstants.CENTER);
		fNametxt.setBackground(Color.LIGHT_GRAY);
		fNametxt.setBounds(251, 157, 267, 29);
		contentPane.add(fNametxt);
		fNametxt.setColumns(10);
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		
		JLabel lblNewLabel = new JLabel("Enter the following information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(69, 29, 254, 37);
		contentPane.add(lblNewLabel);
		
		JLabel imgLabel = new JLabel("Add Image");
		imgLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBounds(602, 29, 248, 234);
		contentPane.add(imgLabel);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnNewButton.setBounds(669, 304, 125, 29);
		contentPane.add(btnNewButton);
		
		JButton addStudent = new JButton("Add Faculty");
		addStudent.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		addStudent.setBounds(366, 361, 132, 37);
		contentPane.add(addStudent);
		
		JProgressBar progressBar = new JProgressBar(0,100);
		progressBar.setBounds(295, 449, 277, 29);
		progressBar.setVisible(false);
		contentPane.add(progressBar);
		addStudent.addActionListener((ActionEvent e)->{
			int a =new JOptionPane().showConfirmDialog(this, "Are You sure about the details? If yes then proceed, else review details");
			if(a==JOptionPane.YES_OPTION) {
				String facultyName = "";
				facultyName = fNametxt.getText();
				if(filePath.equals("")||facultyName.equals("")) {
					new JOptionPane().showMessageDialog(this, "All Fields should not be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					progressBar.setVisible(true);
					if(CRUD.addFaculty(facultyName, filePath,progressBar)) {
						new JOptionPane().showMessageDialog(this, "Faculty Information Added Successfully");
						comboBox.addItem(facultyName);
						this.dispose();
					}
				} catch (Exception e1) {
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
