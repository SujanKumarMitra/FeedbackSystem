package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.CRUD;

import javax.swing.JTabbedPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;

public class AdminActivity extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField questionField;
	private JTextField adminId;
	private JPasswordField adminKey;
	Object[] questionObj;
	String[] questions;
	@SuppressWarnings("rawtypes")
	JComboBox questionComboBox;
	@SuppressWarnings("rawtypes")
	JComboBox facultyComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminActivity frame = new AdminActivity();
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
	public AdminActivity() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 497);
		setTitle("Admin Activity");
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 918, 450);
		contentPane.add(tabbedPane);
		
		JPanel facultyPanel = new JPanel();
		tabbedPane.addTab("Faculty", null, facultyPanel, "Settings regarded Faculty like Add/Remove");
		facultyPanel.setLayout(null);
		
		JButton addFacultyButton = new JButton("Add Faculty");
		addFacultyButton.setToolTipText("Add new Faculty");
		addFacultyButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		addFacultyButton.setBounds(356, 128, 162, 43);
		facultyPanel.add(addFacultyButton);
		addFacultyButton.addActionListener((ActionEvent e)-> {
			new AddFaculty(facultyComboBox).setVisible(true);
			System.gc();
		});
		
		JButton removeFacultyButton = new JButton("Remove Faculty");
		removeFacultyButton.setToolTipText("Remove existing Faculty");
		removeFacultyButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeFacultyButton.setBounds(356, 244, 162, 43);
		facultyPanel.add(removeFacultyButton);
		removeFacultyButton.addActionListener((ActionEvent e) ->{
			new RemoveFaculty(facultyComboBox).setVisible(true);
			System.gc();
		});
		
		JPanel studentPanel = new JPanel();
		tabbedPane.addTab("Student", null, studentPanel, "Settings regarded Student like Add/Remove");
		studentPanel.setLayout(null);
		
		JButton addStudentButton = new JButton("Add Student");
		addStudentButton.setToolTipText("Add new Student");
		addStudentButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		addStudentButton.setBounds(356, 99, 162, 43);
		studentPanel.add(addStudentButton);
		addStudentButton.addActionListener((ActionEvent e)-> {
			new AddStudent().setVisible(true);
			System.gc();
		});
		
		JButton removeStudentButton = new JButton("Remove Student");
		removeStudentButton.setToolTipText("Remove existing Student");
		removeStudentButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeStudentButton.setBounds(356, 192, 162, 46);
		studentPanel.add(removeStudentButton);
		removeStudentButton.addActionListener((ActionEvent e)-> {
			new RemoveStudent().setVisible(true);
			System.gc();
		});
		
		JButton incrementButton = new JButton("Increment Semester");
		incrementButton.setToolTipText("Incremets Semester values of all Students");
		incrementButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		incrementButton.setBounds(349, 290, 177, 46);
		studentPanel.add(incrementButton);
		incrementButton.addActionListener((ActionEvent e)->{
			int a = new JOptionPane().showConfirmDialog(this, "Are you sure you want to increment semester of all students?");
			if(a==JOptionPane.YES_OPTION) {
				CRUD.incrementSemester();
				new JOptionPane().showMessageDialog(this, "Semester Values of all Students are added by one");
			}
		});
		
		
		JPanel feedbackPanel = new JPanel();
		tabbedPane.addTab("Feedback", null, feedbackPanel, "Showing Feedback results, Conduct Feedback");
		feedbackPanel.setLayout(null);
		
		
		Object[] facultyObj = CRUD.getFaculty().toArray();
		String[] facultyNames = Arrays.copyOf(facultyObj, facultyObj.length,String[].class);
		facultyComboBox = new JComboBox(facultyNames);
		facultyComboBox.setRenderer(renderer);
		facultyComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		facultyComboBox.setBounds(226, 99, 506, 35);
		feedbackPanel.add(facultyComboBox);
		
		JLabel lblNewLabel = new JLabel("Show Faculty Stats");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 98, 187, 35);
		feedbackPanel.add(lblNewLabel);
		
		JButton showStatsButton = new JButton("Show Stats");
		showStatsButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		showStatsButton.setBounds(758, 98, 115, 35);
		feedbackPanel.add(showStatsButton);
		showStatsButton.addActionListener((ActionEvent e)-> {
			new ShowChart(facultyComboBox.getSelectedItem().toString()).setVisible(true);
			System.gc();
		});
		
		JButton resetStats = new JButton("Reset Stats");
		resetStats.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		resetStats.setBounds(379, 250, 169, 42);
		feedbackPanel.add(resetStats);
		resetStats.addActionListener((ActionEvent e)-> {
			int a = new JOptionPane().showConfirmDialog(this, "Are You sure you want to reset the feedback table.\n"
					+"This will clear feedback given by students");
			if(a==JOptionPane.YES_OPTION) {
				CRUD.resetResponses();
				new JOptionPane().showMessageDialog(this, "Stats Cleared");
				System.gc();
			}
		});
		
		JPanel questionPanel = new JPanel();
		tabbedPane.addTab("Questions", null, questionPanel, null);
		questionPanel.setLayout(null);
		
		questionField = new JTextField();
		questionField.setHorizontalAlignment(SwingConstants.CENTER);
		questionField.setBackground(Color.LIGHT_GRAY);
		questionField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		questionField.setBounds(169, 89, 623, 44);
		questionPanel.add(questionField);
		questionField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Add New Question");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 91, 145, 38);
		questionPanel.add(lblNewLabel_1);
		
		JButton addQuestionButton = new JButton("Add");
		addQuestionButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		addQuestionButton.setBounds(804, 91, 97, 38);
		questionPanel.add(addQuestionButton);
		
		
		questionObj = CRUD.getQuestions().toArray();
		questions = Arrays.copyOf(questionObj, questionObj.length,String[].class);
		questionComboBox = new JComboBox(questions);
		questionComboBox.setRenderer(renderer);
		questionComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		questionComboBox.setBounds(169, 203, 623, 38);
		questionPanel.add(questionComboBox);
		
		addQuestionButton.addActionListener((ActionEvent e) ->{
			String question = "";
			if(questionField.getText().equals("")) {
				new JOptionPane().showMessageDialog(this, "Nothing Typed!", "ERROR", EXIT_ON_CLOSE);
				System.gc();
			}
			else {
				question=questionField.getText();
				CRUD.insertQuestion(question);
				questionComboBox.addItem(question);
				new JOptionPane().showMessageDialog(this, "Question Added Successfully");
				System.gc();
			}
		});
		JLabel lblRemoveQuestion = new JLabel("Remove Question");
		lblRemoveQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveQuestion.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblRemoveQuestion.setBounds(12, 203, 145, 38);
		questionPanel.add(lblRemoveQuestion);
		
		JButton removeQuestionButton = new JButton("Remove");
		removeQuestionButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeQuestionButton.setBounds(804, 206, 97, 32);
		questionPanel.add(removeQuestionButton);
		removeQuestionButton.addActionListener((ActionEvent e)->{
			int a = new JOptionPane().showConfirmDialog(this, "Are You sure you want to remove this question?");
			if(a==JOptionPane.YES_OPTION) {
				String question = questionComboBox.getSelectedItem().toString();
				CRUD.removeQuestion(question);
				new JOptionPane().showMessageDialog(this, "Question Deleted Successfully!");
				questionComboBox.removeItem(question);
				System.gc();
			}
		});
		
		JPanel subjectPanel = new JPanel();
		tabbedPane.addTab("Subjects", null, subjectPanel, null);
		subjectPanel.setLayout(null);
		
		JButton newSubjectButton = new JButton("Add New Subject");
		newSubjectButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		newSubjectButton.setBounds(362, 124, 187, 49);
		subjectPanel.add(newSubjectButton);
		newSubjectButton.addActionListener((ActionEvent e)-> {
			new AddSubject().setVisible(true);
			System.gc();
		});
		
		JButton removeSubjectButton = new JButton("Remove Subject");
		removeSubjectButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		removeSubjectButton.setBounds(362, 214, 187, 49);
		subjectPanel.add(removeSubjectButton);
		removeSubjectButton.addActionListener((ActionEvent e)->{
			new RemoveSubject().setVisible(true);
			System.gc();
		});
		
		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Admin Settings", null, settingsPanel, null);
		settingsPanel.setLayout(null);
		
		JButton updateAdmin = new JButton("Update");
		updateAdmin.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		updateAdmin.setBounds(728, 345, 151, 44);
		settingsPanel.add(updateAdmin);
		
		adminId = new JTextField();
		adminId.setHorizontalAlignment(SwingConstants.CENTER);
		adminId.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		adminId.setBackground(Color.LIGHT_GRAY);
		adminId.setBounds(282, 123, 316, 44);
		settingsPanel.add(adminId);
		adminId.setColumns(10);
		
		adminKey = new JPasswordField();
		adminKey.setHorizontalAlignment(SwingConstants.CENTER);
		adminKey.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		adminKey.setBackground(Color.LIGHT_GRAY);
		adminKey.setBounds(282, 225, 316, 44);
		settingsPanel.add(adminKey);
		
		JLabel lblNewLabel_2 = new JLabel("New Admin ID");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(54, 131, 163, 30);
		settingsPanel.add(lblNewLabel_2);
		
		JLabel lblNewAdminKey = new JLabel("New Admin Key");
		lblNewAdminKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAdminKey.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewAdminKey.setBounds(54, 232, 163, 30);
		settingsPanel.add(lblNewAdminKey);
		
		JLabel lblNewLabel_3 = new JLabel("Update Admin Credentials");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(230, 44, 407, 44);
		settingsPanel.add(lblNewLabel_3);
		updateAdmin.addActionListener((ActionEvent e)-> {
			String adminId = "";
			String adminKey = "";
			adminId = this.adminId.getText();
			adminKey = String.valueOf(this.adminKey.getPassword());
			if(adminId.equals("") || adminKey.equals("")) {
				new JOptionPane().showMessageDialog(this, "ID and Key field can't be blank", "ERROR", EXIT_ON_CLOSE);
				System.gc();
			}
			else {
				CRUD.updateAdmin(adminId,adminKey);
				new JOptionPane().showMessageDialog(this, "ID and Key Updated Successfully");
				this.dispose();
				new MainLogin().setVisible(true);
				System.gc();
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
