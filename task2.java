package com.TechVidvan;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;
public class Database {
//	declaring connection and datasource variables
  static Connection conn;
  static SQLiteDataSource ds;
  
//	initialize method to initialize the database with students table
  public static void dbInit() {
    ds = new SQLiteDataSource();
    
    try {
ds = new SQLiteDataSource();
ds.setUrl("jdbc:sqlite:StudentManagementDB.db");
} catch ( Exception e ) {
e.printStackTrace();
System.exit(0);
}
try {
   conn = ds.getConnection();
  
   Statement stmt = conn.createStatement();
// 	 sql query to add student table
   String query = "CREATE TABLE IF NOT EXISTS students ( "
   				+ "student_id TEXT PRIMARY KEY ,"
   				+ "student_name TEXT,"
   				+ "father_name TEXT,"
   				+ "student_dob TEXT,"
   				+ "student_gender TEXT,"
   				+ "student_contact TEXT,"
   				+ "student_email TEXT,"
   				+ "section TEXT,"
   				+ "student_address TEXT"
   				+ " );"; 	
// 	 executing the query using statement variable
   stmt.executeUpdate(query);
   conn.close();
  
} catch ( SQLException e ) {
e.printStackTrace();
System.exit( 0 );
}
;
}
  
//	function to add the student into the database
  protected static void insertStudent(String id,String name,String fatherName,
                   String dob,String gender,String contact,String section,String email,String address
                  ) throws SQLException {
    String query = "INSERT INTO students(student_id,student_name,father_name,student_dob,student_gender,student_contact,section,student_email,student_address) "
          + "VALUES("
            +"'"+ id +"',"
            +"'"+ name +"',"
            +"'"+ fatherName +"',"
            +"'"+ dob +"',"
            +"'"+ gender +"',"
            +"'"+ contact +"',"
            +"'"+ section +"',"
            +"'"+ email +"',"
            +"'"+ address +"');" ;
    
    conn = ds.getConnection();
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(query);
    conn.close();
  }
//	Fucntion to update the student data using the id
  protected static void updateStudent(String id,String name,String fatherName,String contact,
       String dob,String gender,String email, String section,String address
      ) throws SQLException {
      String query = "UPDATE students "
          + "SET "
          + "student_name = '"+name + "',"
          + "father_name = '"+fatherName + "',"
          + "student_contact = '"+contact+ "',"
          + "student_dob = '"+dob+ "',"
          + "student_gender = '"+gender + "',"
          + "student_email = '"+email + "',"
          + "section = '"+section + "',"
          + "student_address = '"+address + "'"
          
          + "WHERE "
          + "student_id = '"+id+"'";
      System.out.println(query);
      conn = ds.getConnection();
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(query);
      conn.close();
      }
  //	function to delete the student from the database
  protected static void deleteStudent(String id) throws SQLException {
    String query = "DELETE FROM students WHERE student_id = '"+id+"';";
    conn = ds.getConnection();
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(query);
    conn.close();
  
  }
  //	function that searches the student in the database and updates the values using tabel model
  public static void searchStudents(DefaultTableModel model,String searchTerm) throws SQLException {
    model.setRowCount(0);
    String query = "SELECT * FROM students WHERE student_name LIKE '%"+searchTerm +"%';";
    conn = ds.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    
    while(rs.next()) {
      String id = rs.getString("student_id");
      String name = rs.getString("student_name");
      String fatherName = rs.getString("father_name");
      String dob = rs.getString("student_dob");
      String gender = rs.getString("student_gender");
      String contact = rs.getString("student_contact");
      String section = rs.getString("section");
      String email = rs.getString("student_email");
      String address = rs.getString("student_address");
      
      
      model.addRow(new Object[]{id,name,fatherName,dob,gender,contact,section,email,address});
      
    }
    
    conn.close();
    rs.close();
    
  }
  // function to fetch the data and add it to the model so that the jtable is updated
  public static void fetchAllData(DefaultTableModel model) throws SQLException {
    model.setRowCount(0);
    String query = "SELECT * FROM students ;";
    conn = ds.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    
    while(rs.next()) {
      String id = rs.getString("student_id");
      String name = rs.getString("student_name");
      String fatherName = rs.getString("father_name");
      String dob = rs.getString("student_dob");
      String gender = rs.getString("student_gender");
      String contact = rs.getString("student_contact");
      String section = rs.getString("section");
      String email = rs.getString("student_email");
      String address = rs.getString("student_address");
      
      
      model.addRow(new Object[]{id,name,fatherName,dob,gender,contact,section,email,address});
      
    }
    
    conn.close();
    rs.close();
    
  }
}