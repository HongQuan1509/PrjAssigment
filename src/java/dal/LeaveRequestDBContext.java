/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import data.Department;
import data.Employee;
import data.LeaveRequest;
import data.User;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class LeaveRequestDBContext extends DBContext<LeaveRequest> {

    @Override
    public ArrayList<LeaveRequest> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LeaveRequest get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public LeaveRequest get(int mid, int rid) {
        LeaveRequest r = new LeaveRequest();
        try {
            String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], r.createddate, r.[status], "
                    + "u.username, u.displayname, e.eid, e.ename, d.did, d.dname "
                    + "FROM LeaveRequest r "
                    + "INNER JOIN Users u ON u.username = r.createdby "
                    + "INNER JOIN Employees e ON e.eid = u.eid "
                    + "INNER JOIN Departments d ON d.did = e.did "
                    + "WHERE e.managerid = ? AND r.rid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, mid);
            stm.setInt(2, rid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                r.setId(rs.getInt("rid"));
                r.setTitle(rs.getString("title"));
                r.setReason(rs.getString("reason"));
                r.setFrom(rs.getDate("from"));
                r.setTo(rs.getDate("to"));
                r.setCreateddate(rs.getTimestamp("createddate"));
                r.setStatus(rs.getInt("status"));

                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setDisplayname(rs.getString("displayname"));
                r.setCreatedby(u);

                Employee e = new Employee();
                u.setE(e);
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));

                EmployeeDBContext db = new EmployeeDBContext();

                e.setManager(db.get(mid)); // Gán manager ID

                Department d = new Department();
                e.setDept(d);
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } 
        return r;

    }

    public LeaveRequest getByTitle(int mid, String title) {
        LeaveRequest r = new LeaveRequest();
        try {
            String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], r.createddate, r.[status], "
                    + "u.username, u.displayname, e.eid, e.ename, d.did, d.dname "
                    + "FROM LeaveRequest r "
                    + "INNER JOIN Users u ON u.username = r.createdby "
                    + "INNER JOIN Employees e ON e.eid = u.eid "
                    + "INNER JOIN Departments d ON d.did = e.did "
                    + "WHERE e.managerid = ? AND r.title = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, mid);
            stm.setString(2, title);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                r.setId(rs.getInt("rid"));
                r.setTitle(rs.getString("title"));
                r.setReason(rs.getString("reason"));
                r.setFrom(rs.getDate("from"));
                r.setTo(rs.getDate("to"));
                r.setCreateddate(rs.getTimestamp("createddate"));
                r.setStatus(rs.getInt("status"));

                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setDisplayname(rs.getString("displayname"));
                r.setCreatedby(u);

                Employee e = new Employee();
                u.setE(e);
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));

                EmployeeDBContext db = new EmployeeDBContext();
                e.setManager(db.get(mid));

                Department d = new Department();
                e.setDept(d);
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return r; 
    }

    @Override
    public void insert(LeaveRequest model) {
        try {
            String sql = "INSERT INTO [LeaveRequest]\n"
                    + "           ([title]\n"
                    + "           ,[reason]\n"
                    + "           ,[from]\n"
                    + "           ,[to]\n"
                    + "           ,[createdby]\n"
                    + "           ,[status]\n"
                    + "		   ,[createddate]\n"
                    + "		   )\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,0,GETDATE())";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getTitle());
            stm.setString(2, model.getReason());
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setString(5, model.getCreatedby().getUsername());
            stm.executeUpdate();//update insert delete

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<LeaveRequest> getByManager(int managerId) {
        ArrayList<LeaveRequest> requests = new ArrayList<>();
        try {
            String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], r.createddate, r.[status], "
                    + "u.username, u.displayname, e.eid, e.ename, d.did, d.dname "
                    + "FROM LeaveRequest r "
                    + "INNER JOIN Users u ON u.username = r.createdby "
                    + "INNER JOIN Employees e ON e.eid = u.eid "
                    + "INNER JOIN Departments d ON d.did = e.did "
                    + "WHERE e.managerid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, managerId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                LeaveRequest r = new LeaveRequest();
                r.setId(rs.getInt("rid"));
                r.setTitle(rs.getString("title"));
                r.setReason(rs.getString("reason"));
                r.setFrom(rs.getDate("from"));
                r.setTo(rs.getDate("to"));
                r.setCreateddate(rs.getTimestamp("createddate"));
                r.setStatus(rs.getInt("status"));

                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setDisplayname(rs.getString("displayname"));
                r.setCreatedby(u);

                Employee e = new Employee();
                u.setE(e);
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));

                EmployeeDBContext db = new EmployeeDBContext();

                e.setManager(db.get(managerId)); // Gán manager ID

                Department d = new Department();
                e.setDept(d);
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));

                requests.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } 
        return requests;
    }

    @Override
    public void update(LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void update(LeaveRequest model, int status) {
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE LeaveRequest SET status = ? WHERE rid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, model.getId());
            int rows = stm.executeUpdate();
            System.out.println(rows);
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void delete(LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
