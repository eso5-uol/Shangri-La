package net.test.dao;

import net.test.models.User;
import net.test.models.HashGenerator;
import java.sql.*;


public class UserDAO {
	
	private static Connection connect = null;
	
	private static String jdbcURL = "jdbc:mysql://localhost:3307/eso5";
    private static String dbUser = "eso5";
    private static String dbPassword = "Phahm7ae";


    public static Connection getConnection() {
    	if (connect==null) {
    		try {
    		    Class.forName("com.mysql.jdbc.Driver");
    		    Connection connect = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    		    return connect;
    		}catch(Exception e) {
    			return null;
    		}
    	}else {
    		return connect;
    	}
    }
    
	
	
    public User checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {
        
        String sql = "SELECT * FROM Admin WHERE username = ? and passwordHash = ?";
        User user = null;
        try(Connection connect = getConnection();
        		PreparedStatement statement = connect.prepareStatement(sql);
        		) {
        	statement.setString(1, username);
            statement.setString(2, HashGenerator.getSHA256(password));
            try( ResultSet result = statement.executeQuery();){
            	if (result.next()) {
                    user = new User();
                    user.setPasswordHash(result.getString("passwordHash"));
                    user.setFullname(result.getString("fullname"));
                    user.setEmail(result.getString("email"));
                    connect.close();
                }
            }
        	
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        return user;
    }
    
    public boolean userExist(String email) {
    	String sql="Select * from Admin WHERE username =?";
    	boolean userExist = false;
    	
    	try(Connection connection = getConnection();
    		PreparedStatement pstmt = connect.prepareStatement(sql);){
    		pstmt.setString(1, email);
    		try(ResultSet rs = pstmt.executeQuery();){
    			while (rs.next()) {
    				userExist=true;
    				break;
    			}
    		}
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	return userExist;
    	
    }
    
}