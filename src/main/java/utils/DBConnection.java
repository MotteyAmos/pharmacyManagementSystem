package utils;

import java.sql.*;

public class DBConnection {


    public static Connection connection;


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacymanagementsystem", "root","1234567890");
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet re){
        try{
            if (re != null) re.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
