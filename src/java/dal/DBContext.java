/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public abstract class DBContext<T> {
        protected Connection connection;

    public DBContext() {
        String url = "jdbc:sqlserver://DESKTOP-7MOD5KR\\SQLEXPRESS:1433;databaseName=SE1937_Database;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "123456";

        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Kết nối thành công!");
    } catch (ClassNotFoundException ex) {
        System.err.println("Không tìm thấy driver JDBC!");
        ex.printStackTrace();
    } catch (SQLException ex) {
        System.err.println("Lỗi kết nối SQL Server!");
        ex.printStackTrace();
    }
}
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public abstract ArrayList<T> list();
    public abstract T get(int id);
    public abstract void insert(T model);
    public abstract void update(T model);
    public abstract void delete(T model);
}
