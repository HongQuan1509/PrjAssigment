/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import data.Employee;
import data.Feature;
import data.Role;
import data.User;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserDBContext extends DBContext<User> {

    public User get(String username, String password) {
        try {
            String sql = "SELECT u.username,u.displayname\n" +
"                    		,r.rid,r.rname\n" +
"                    		,f.fid,f.url\n" +
"                    	,e.eid,e.ename\n" +
"                    FROM Users u INNER JOIN Employees e ON e.eid = u.eid\n" +
"                     LEFT JOIN User_Role ur ON u.username = ur.username\n" +
"                    						LEFT JOIN Roles r ON ur.rid = r.rid\n" +
"                    						LEFT JOIN Role_Feature rf ON rf.rid = r.rid\n" +
"                    						LEFT JOIN Features f ON f.fid = rf.fid\n" +
"                    WHERE u.username = ? AND u.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            User user = null;
            Role current_role = new Role();
            current_role.setId(-1);
            
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setUsername(username);
                    user.setDisplayname(rs.getString("displayname"));
                    Employee e = new Employee();
                    e.setId(rs.getInt("eid"));
                    e.setName(rs.getString("ename"));
                    user.setE(e);
                }
                
                int rid = rs.getInt("rid");
                if(rid !=0 && rid != current_role.getId())
                {
                    current_role = new Role();
                    current_role.setId(rid);
                    current_role.setName(rs.getString("rname"));
                    user.getRoles().add(current_role);
                    current_role.getUsers().add(user);
                }
                
                int fid = rs.getInt("fid");
                if(fid != 0)
                {
                    Feature f = new Feature();
                    f.setId(rs.getInt("fid"));
                    f.setUrl(rs.getString("url"));
                    current_role.getFeatures().add(f);
                    f.getRoles().add(current_role);
                }
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
