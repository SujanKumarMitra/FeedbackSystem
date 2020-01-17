package application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.CRUD;

public class FeedbackPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	int count = 0;
	JLabel qLabel = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackPage frame = new FeedbackPage();
					frame.gui(args[0],args[1]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

	/**
	 * Create the frame.
	 */
	
	@SuppressWarnings("static-access")
	public void gui(String facultyName,String studentId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1032, 527);
		setTitle("Feedback Form");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ArrayList<String> questions = CRUD.getQuestions();

		qLabel = new JLabel(questions.get(count),SwingConstants.CENTER);
		qLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		qLabel.setBounds(12, 78, 990, 93);
		contentPane.add(qLabel);
		
		JRadioButton option1 = new JRadioButton("1");
		option1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		option1.setHorizontalAlignment(SwingConstants.CENTER);
		option1.setBounds(47, 282, 127, 25);
		contentPane.add(option1);
		
		JRadioButton option2 = new JRadioButton("2");
		option2.setHorizontalAlignment(SwingConstants.CENTER);
		option2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		option2.setBounds(231, 282, 127, 25);
		contentPane.add(option2);
		
		JRadioButton option3 = new JRadioButton("3");
		option3.setHorizontalAlignment(SwingConstants.CENTER);
		option3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		option3.setBounds(437, 282, 127, 25);
		contentPane.add(option3);
		
		JRadioButton option4 = new JRadioButton("4");
		option4.setHorizontalAlignment(SwingConstants.CENTER);
		option4.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		option4.setBounds(640, 282, 127, 25);
		contentPane.add(option4);
		
		JRadioButton option5 = new JRadioButton("5");
		option5.setHorizontalAlignment(SwingConstants.CENTER);
		option5.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		option5.setBounds(841, 282, 127, 25);
		contentPane.add(option5);
		
		ButtonGroup option = new ButtonGroup();
		option.add(option1);
		option.add(option2);
		option.add(option3);
		option.add(option4);
		option.add(option5);
		
		JButton previousButton = new JButton("Previous");
		previousButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		previousButton.setBounds(91, 375, 135, 45);
		contentPane.add(previousButton);
		previousButton.setEnabled(false);
		
		HashMap<String,String> response = new HashMap<>();	
		JButton nextButton = new JButton("Next");
		nextButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		nextButton.setBounds(675, 375, 135, 45);
		contentPane.add(nextButton);
		nextButton.addActionListener((ActionEvent e) ->{
			previousButton.setEnabled(true);
			String res = getSelectedButtonText(option);
			if(res==null) {
				new JOptionPane().showMessageDialog(this, "No Option Selected", "ERROR", EXIT_ON_CLOSE);
			}
			else {
					
				if(nextButton.getText().equals("Submit")) {
					if(response.containsKey(questions.get(count))) {
						response.replace(questions.get(count), res);
					}
					else {
						response.put(questions.get(count), res);
					}
					CRUD.insertResponse(response, facultyName,studentId);
					this.dispose();
					
				}
				else {
					if(response.containsKey(questions.get(count))) {
						response.replace(questions.get(count), res);
					}
					else {
						response.put(questions.get(count), res);
					}
					
					qLabel.setText(questions.get(++count));
					if(count == questions.size()-1) {
						nextButton.setText("Submit");
					}
				}
			}
			
			
		});
		previousButton.addActionListener((ActionEvent e)-> {
			nextButton.setText("Next");
			qLabel.setText(questions.get(--count));
			if(count == 0) previousButton.setEnabled(false);
		});
	}
	
}
