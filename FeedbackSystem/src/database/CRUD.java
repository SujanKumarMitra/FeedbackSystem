package database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JProgressBar;

public class CRUD implements DatabaseInfo {
	public static boolean loginAdmin(String id,String pass) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT admin_key FROM admin WHERE admin_name = ?");
			preparedStatement.setString(1, pass);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				if (pass.equals(resultSet.getString("admin_key")))
					return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	public static String loginStudent(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT student_name FROM student WHERE student_id = ?");
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				return resultSet.getString("student_name");
			}
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	public static boolean addStudent(String sname,String sid, int sSem, String imgPath,JProgressBar progessBar) throws Exception {

		File file = new File(imgPath);
		FileInputStream input = new FileInputStream(file);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		progessBar.setValue(30);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			progessBar.setValue(70);
			String stmt = "INSERT INTO student VALUES (default,?,?,?,?)";
			preparedStatement = connection.prepareStatement(stmt);
			preparedStatement.setString(1, sid);
			preparedStatement.setString(2, sname);
			preparedStatement.setInt(3, sSem);
			preparedStatement.setBinaryStream(4, input);
			preparedStatement.executeUpdate();
			progessBar.setValue(100);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				input.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	public static boolean addFaculty(String fname,String imgPath,JProgressBar progressBar) throws Exception {
		File file = new File(imgPath);
		FileInputStream input = new FileInputStream(file);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		progressBar.setValue(30);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			progressBar.setValue(70);
			String stmt = "INSERT INTO faculty VALUES (default,?,?)";
			preparedStatement = connection.prepareStatement(stmt);
			preparedStatement.setString(1, fname);
			preparedStatement.setBinaryStream(2, input);
			preparedStatement.executeUpdate();
			progressBar.setValue(100);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				input.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	public static HashMap<String,String> fetchStudent(String name) throws IOException {
		HashMap<String,String> list = new HashMap<>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		FileOutputStream output = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			String stmt = "SELECT * from student where student_name = ?";
			preparedStatement = connection.prepareStatement(stmt);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			list.put("Student Id", resultSet.getString("student_id"));
			list.put("Name",resultSet.getString("student_name"));
			list.put("Semester", resultSet.getString("sem"));
			output = new FileOutputStream(new File("StudentPhoto.png"));
			InputStream inputStream = resultSet.getBinaryStream("image");
			byte[] buffer = new byte[1024];
			while(inputStream.read(buffer)>0) {
				output.write(buffer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
				output.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		list.put("Image", "Test.png");
		return list;
	}
	public static ArrayList<String> getQuestions(){
		ArrayList<String>list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT question FROM questions");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				list.add(resultSet.getString("question"));
			}
			return list;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	public static void insertResponse(HashMap<String,String> response,String facultyName,String studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			int count = response.size();
			Iterator<String> it = response.keySet().iterator();
			for(int i = 0;i<count;i++) {
				preparedStatement = connection.prepareStatement("INSERT INTO responses VALUES (default,?,?,?,?)");				
				String question = it.next();
				preparedStatement.setString(1, facultyName);
				preparedStatement.setString(2,question);
				preparedStatement.setInt(3, Integer.parseInt(response.get(question)));
				preparedStatement.setString(4, studentId);
				preparedStatement.executeUpdate();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	@SuppressWarnings("resource")
	public static ArrayList<String> getSubjects(String studentName){
		ArrayList<String> subjects = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int sem=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT sem FROM student WHERE student_name = ?");
			preparedStatement.setString(1, studentName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				sem = resultSet.getInt("sem");
			}
			preparedStatement = connection.prepareStatement("SELECT subject_name FROM subjects WHERE sem = ?");
			preparedStatement.setInt(1, sem);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				subjects.add(resultSet.getString("subject_name"));
			}
			return subjects;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return subjects;
	}
	@SuppressWarnings("resource")
	public static void getFacultyImage(String subjectName) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		FileOutputStream output = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT responsible_faculty from subjects WHERE subject_name = ?");
			preparedStatement.setString(1, subjectName);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String facultyName = resultSet.getString("responsible_faculty");
			String stmt = "SELECT image from faculty where faculty_name = ?";
			preparedStatement = connection.prepareStatement(stmt);
			preparedStatement.setString(1, facultyName);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			output = new FileOutputStream(new File("FacultyPhoto.png"));
			InputStream inputStream = resultSet.getBinaryStream("image");
			byte[] buffer = new byte[1024];
			while(inputStream.read(buffer)>0) {
				output.write(buffer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}
	public static String getFacultyName(String subjectName) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String facultyName = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT responsible_faculty from subjects WHERE subject_name = ?");
			preparedStatement.setString(1, subjectName);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			facultyName = resultSet.getString("responsible_faculty");
			return facultyName;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return facultyName;
	}
	@SuppressWarnings("resource")
	public static boolean isResponseGiven(String studentId,String subject) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String facultyName = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT responsible_faculty FROM subjects WHERE subject_name = ?");
			preparedStatement.setString(1, subject);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				facultyName = resultSet.getString("responsible_faculty");
			}
			preparedStatement = connection.prepareStatement("SELECT faculty_name from responses WHERE student_id = ? ");
			preparedStatement.setString(1, studentId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				if(facultyName.equals(resultSet.getString("faculty_name"))) return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	public static HashMap<String,Integer> getOverallRating(String facultyName) {
		HashMap<String,Integer> map = new HashMap<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			for(Integer i = 1;i<=5;i++) {
				preparedStatement = connection.prepareStatement("SELECT COUNT(*) from responses WHERE response = ? and faculty_name = ?");
				preparedStatement.setInt(1, i);
				preparedStatement.setString(2, facultyName);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				map.put(i.toString(), resultSet.getInt("COUNT(*)"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return map;
	}
	public static ArrayList<String> getFaculty(){
		ArrayList<String> faculty = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT faculty_name from faculty");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				faculty.add(resultSet.getString("faculty_name"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return faculty;
	}
	public static void insertQuestion(String question) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO questions (question) VALUES(?)");
			preparedStatement.setString(1, question);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void updateAdmin(String adminId, String adminKey) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE admin SET admin_name = ?,admin_key = ? ");
			preparedStatement.setString(1, adminId);
			preparedStatement.setString(2, adminKey);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void removeFaculty(String facultyName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("DELETE from faculty WHERE faculty_name = ?");
			preparedStatement.setString(1, facultyName);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static boolean isStudentPresent(String studentName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE student_name = ?");
			preparedStatement.setString(1, studentName);
			resultSet=preparedStatement.executeQuery();
			return(resultSet.next());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	public static void removeStudent(String studentName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM student WHERE student_name = ?");
			preparedStatement.setString(1, studentName);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void resetResponses() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("TRUNCATE TABLE responses");
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void addSubject(String subjectName, String facultyName, int semester) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO subjects VALUES(default,?,?,?)");
			preparedStatement.setString(1, subjectName);
			preparedStatement.setString(2, facultyName);
			preparedStatement.setInt(3, semester);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static ArrayList<String> getSubjects() {
		ArrayList<String> subjects = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("SELECT subject_name FROM subjects");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				subjects.add(resultSet.getString("subject_name"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subjects;
	}
	public static void deleteSubject(String subjectName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM subjects WHERE subject_name = ?");
			preparedStatement.setString(1, subjectName);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}		
	}
	public static void removeQuestion(String question) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM questions WHERE question = ?");
			preparedStatement.setString(1, question);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	}
	public static void incrementSemester() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DatabaseInfo.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE student SET sem = sem + 1");
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	}
}
