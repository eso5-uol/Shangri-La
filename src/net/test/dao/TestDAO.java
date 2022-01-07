package net.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import net.test.models.Test;



public class TestDAO {
	private static Connection connect = null;
	private static String jdbcURL = "jdbc:mysql://localhost:3307/eso5";
    private static String dbUser = "eso5";
    private static String dbPassword = "Phahm7ae";
    
    private static final String SELECT_ALL_USERS = "select * from TestResult";
    private static final String AGE_DEMOGRAPHICS = "SELECT SUM(CASE WHEN age < 18 THEN 1 ELSE 0 END) AS `[Under 18]`,"
    		+ "		SUM(CASE WHEN age BETWEEN 18 AND 34 THEN 1 ELSE 0 END) AS `[18-34]`,"
    		+ "        SUM(CASE WHEN age BETWEEN 35 AND 50 THEN 1 ELSE 0 END) AS `[35-50]`,"
    		+ "		SUM(CASE WHEN age BETWEEN 50 AND 72 THEN 1 ELSE 0 END) AS `[50-72]`,"
    		+ "		SUM(CASE WHEN age > 72 THEN 1 ELSE 0 END) AS `[72+]`"
    		+ " FROM TestResult;";
    private static final String SET_TTN = "UPDATE HomeTestKit SET ? = 0 WHERE ttn =";
    
    
    //Connection Set and get
	public static Connection getConnection(){
		if(connect ==null){
			try{
			 Class.forName("com.mysql.jdbc.Driver");
		      Connection connect = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		      return connect;
			}catch(Exception ex){
				 return null;
				//ex.printStackTrace();
			}
		}else{
			return connect;
		}
	}
	
	
	//Check if TTN is valid in the table HomeTestKit
	public boolean searchTestsTTN(String ttn) throws Exception {
		String jdbcURL = "jdbc:mysql://localhost:3307/eso5";
        String dbUser = "eso5";
        String dbPassword = "Phahm7ae";
        
        boolean isValid= false;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try(Connection myConn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
			ttn += "%";
			myStmt = myConn.prepareStatement("select * from HomeTestKit where ttn like ?  order by ttn");
			myStmt.setString(1, ttn);
			myRs = myStmt.executeQuery();
			if(myRs.next()) {
				isValid = true;
			}	
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
		return isValid;
	}
	
	public boolean search2(String ttn) throws Exception {
		String jdbcURL = "jdbc:mysql://localhost:3307/eso5";
        String dbUser = "eso5";
        String dbPassword = "Phahm7ae";
        boolean isValid= true;
        
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		
		ResultSet myRs = null;
		

		try(Connection myConn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
			myStmt = myConn.prepareStatement("select * from TestResult where ttn like ? order by ttn");
			myStmt2 = myConn.prepareStatement("update HomeTestKit set used = ? where ttn = ?");
			myStmt.setString(1, ttn);
			myRs = myStmt.executeQuery();
			if(myRs.next()) {
				isValid = false;
			}else {
				if(myRs.next()) {
					isValid = true;
					myStmt2.executeUpdate();
			}
			}
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
		return isValid;
			
		}

	
	//Select all tests
	public List<Test> selectAllTests() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Test> tests = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int age = rs.getInt("age");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String ttn = rs.getString("ttn");
				String postcode = rs.getString("postcode");
				String testresult = rs.getString("testresult");
				tests.add(new Test(email, fullname, age, gender, address, postcode, ttn, testresult));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tests;
	}
	
	public static String getTestResultByAge(){
		int i =0;
    	try( Connection connect = getConnection(); 
	    	 PreparedStatement pstmt = connect.prepareStatement(AGE_DEMOGRAPHICS);
    		){
	    	 try (ResultSet rs = pstmt.executeQuery()){
	    		 java.sql.ResultSetMetaData rsmd = rs.getMetaData();
	    		 while(rs.next()){
	    			 rs.getMetaData().getColumnName(i);
	    			 i++;
		      }
	    	 }
    	}catch(SQLException ex){
    		ex.printStackTrace();	
    	}	 
    	return "Not Found";
    }
		
	
	public static String getTestResultByEmail(String email){
    	String sql="SELECT * from TestResult WHERE email=?";
    	try( Connection connect = getConnection(); 
	    	 PreparedStatement pstmt = connect.prepareStatement(sql);
    		){	
	    	 pstmt.setString(1,email);
	    	 try (ResultSet rs = pstmt.executeQuery();){
	    	   while(rs.next()){
		    	  String emails=rs.getString("email");
		    	  return emails;
		      }
	    	 }
    	}catch(SQLException ex){
    		ex.printStackTrace();	
    	}	 
    	return "Not Found";
    }
	
	public boolean searchTests(String email) throws Exception {
		String jdbcURL = "jdbc:mysql://localhost:3307/eso5";
        String dbUser = "eso5";
        String dbPassword = "Phahm7ae";
        
        boolean isValid= true;
        

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try(Connection myConn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
			email += "%";
			myStmt = myConn.prepareStatement("select * from TestResult where email like ?  order by email");
			myStmt.setString(1, email);
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				
				isValid= false;
			}
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
		return isValid;
	}
	
	public List<Test> allTests(String testresult) throws Exception{
		List<Test> list = new ArrayList<Test>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try(Connection connect = getConnection()){
			testresult+="%";
			myStmt = connect.prepareStatement("Select * from TestResult where testresult like ?");
			myStmt.setString(1, testresult);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Test tempTest = convertRowToTest(myRs);
				list.add(tempTest);
			}
			return list;
		
		}finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	private Test convertRowToTest(ResultSet myRs) throws SQLException {
		
		int age = myRs.getInt("age");
		String email = myRs.getString("email");
		String fullname = myRs.getString("fullname");
		String gender = myRs.getString("gender");
		String address = myRs.getString("address");
		String postcode = myRs.getString("postcode");
		String ttn = myRs.getString("ttn");
		String testresult = myRs.getString("testresult");

		Test tempTest = new Test(email, fullname, age, gender, address, postcode, ttn, testresult);
		
		return tempTest;
	}
	
	
	
	
	
	
	
	public int submitTest(Test test) throws ClassNotFoundException{
		
		String jdbcURL = "jdbc:mysql://localhost:3307/eso5";
        String dbUser = "eso5";
        String dbPassword = "Phahm7ae";
        
        String INSERT_USERS_SQL = "INSERT INTO TestResult" +
            "  (email, fullname, age, gender, address, postcode, ttn, testresult) VALUES " +
            " (?, ?, ?, ?, ?,?,?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        		) {
       
            preparedStatement.setString(1, test.getEmail());
            preparedStatement.setString(2, test.getFullName());
            preparedStatement.setInt(3, test.getAge());
            preparedStatement.setString(4, test.getGender());
            preparedStatement.setString(5, test.getAddress());
            preparedStatement.setString(6, test.getPostcode());
            preparedStatement.setString(7, test.getTtn());
            preparedStatement.setString(8, test.getTestResult());
            

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
    	TestDAO dao = new TestDAO();
    	
    	System.out.println(dao.selectAllTests());
    	System.out.println(dao.searchTests("abc@le.ac.uk"));
    	System.out.println(dao.allTests("Positive").size());
    	System.out.println(dao.searchTestsTTN("MM2874Z6"));
    	getTestResultByAge();
    }
}   
