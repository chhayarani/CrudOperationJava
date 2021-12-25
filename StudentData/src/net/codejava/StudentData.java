package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

class DBConnection 
{
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/student_info";
	private static final String username = "root";
	private static final String password = "maa@9939";
	
	private static Connection conn = null;
	static {
		try {
			 conn = DriverManager.getConnection(jdbcURL, username, password);
			 
			 if(conn != null) {
					System.out.println(" Connected to Student database");
			 }
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
		 		
}
public class StudentData {

	public static void main(String[] args){
		//createStudent();	
		 //UpdateStudent();
		//DeleteStudent();
		//retrieveStudent();
		int choice;
		do {
			System.out.println("**************************************************************");
			System.out.println("\t\t\t\t MENU");
			System.out.println("**************************************************************");
			System.out.println("\t\t\t 1.CREATE STUDENT TABLE");
			System.out.println("\t\t\t 2.RETRIEVE STUDENT RECORD");
			System.out.println("\t\t\t 3.DELETE STUDENT RECORD");
			System.out.println("\t\t\t 4.UPDATE STUDENT RECORD");
			System.out.println("\t\t\t 5.EXIT");
			System.out.println("\n\t\t\t  Enter Your Choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			System.out.print(choice);
			switch(choice) 
			{
			case 1: createStudent();
			break;
			case 2: retrieveStudent();
			break;
			case 3: DeleteStudent();
			break;
			case 4: UpdateStudent();
			break;
			case 5: break;
			default: System.out.print("SORRY !!! INVALID CHOICE");
			break;
			}
		
		}while(choice!=5);
		
	}
	
	private static void createStudent() {
		try{
			Connection conn = DBConnection.getConnection();
			Statement st = conn.createStatement(); 
			String sql = "INSERT INTO student(student_name,student_dob,student_doj)" + "VALUES('Rohan','1999-08-26','2021-12-12')";
			int row = st.executeUpdate(sql);
			if(row > 0)
			{
				System.out.println(" A new data entered");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void retrieveStudent() {
		try{
			Connection conn = DBConnection.getConnection();
			Statement st = conn.createStatement(); 
			String sql = "SELECT * FROM student";
			ResultSet res  = st.executeQuery(sql);
	
			while(res.next())
			{
				//String student_name = res.getString(2);
				//String student_dob = res.getString(3);
				//String student_doj = res.getString(4);
				
				//String output = "student #%d: %s - %s - %s";
				//System.out.println(String.format(++count, student_name, student_dob, student_doj));
				System.out.print("ID : "+res.getInt("student_no"));
				System.out.print(", NAME : "+res.getString("student_name"));
				System.out.print(", DOB : "+res.getString("student_dob"));
				System.out.println(", DOj : "+res.getString("student_doj"));
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void UpdateStudent() {
		try{
			Connection conn = DBConnection.getConnection();
			Statement st = conn.createStatement(); 
			String sql = "UPDATE student SET student_name='Ram Singh', student_dob='1999-1-14' WHERE student_no=2 ";
			int rowsUpdated = st.executeUpdate(sql);
			if(rowsUpdated > 0)
			{
				System.out.println(" An Existing student was updated successfully");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void DeleteStudent() {
		try{
			Connection conn = DBConnection.getConnection();
			Statement st = conn.createStatement(); 
			String sql = "DELETE FROM student WHERE student_no=4";
			int rowsDeleted = st.executeUpdate(sql);
			if(rowsDeleted > 0)
			{
				System.out.println(" A student record was deleted successfully !!");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
