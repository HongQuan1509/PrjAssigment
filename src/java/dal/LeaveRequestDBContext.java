/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import data.Department;
import data.Employee;
import data.LeaveRequest;
import data.User;
import dto.LeaveDateDTO;
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

    public int count(int mid) {
        try {
            String sql = "WITH EmployeeHierarchy AS ( \n"
                    + "                    SELECT eid, ename, managerid, did, 0 AS [Level] \n"
                    + "                    FROM Employees WHERE eid = ? \n"
                    + "                    UNION ALL \n"
                    + "                    SELECT e.eid, e.ename, e.managerid, e.did, eh.Level + 1 \n"
                    + "                    FROM Employees e INNER JOIN EmployeeHierarchy eh ON eh.eid = e.managerid \n"
                    + "                    ) \n"
                    + "SELECT Count(*)\n"
                    + "FROM EmployeeHierarchy eh\n"
                    + "  INNER JOIN Users u ON u.eid = eh.eid\n"
                    + "                      INNER JOIN LeaveRequest lr ON lr.createdby = u.username \n"
                    + "                    INNER JOIN Departments d ON d.did = eh.did\n"
                    + "					WHERE eh.[Level] > 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, mid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public int countMy(String createby) {
        try {
            String sql = "SELECT COUNT(*) FROM LeaveRequest WHERE createdby = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, createby);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
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
                    + "u.username, u.displayname, e.eid, e.ename,e.managerid, d.did, d.dname "
                    + "FROM LeaveRequest r "
                    + "INNER JOIN Users u ON u.username = r.createdby "
                    + "INNER JOIN Employees e ON e.eid = u.eid "
                    + "INNER JOIN Departments d ON d.did = e.did "
                    + "WHERE r.title = ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, title);
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
                e.setManager(db.get(rs.getInt("managerid")));

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

    public ArrayList<LeaveRequest> getByCreator(String createdby, int pagesize, int pageindex) {
        ArrayList<LeaveRequest> list = new ArrayList<>();

        if (pageindex < 1) {
            pageindex = 1;
        }
        if (pagesize < 1) {
            pagesize = 10;
        }

        int offset = (pageindex - 1) * pagesize;

        try {
            String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], r.createddate, r.[status], "
                    + "u.username, u.displayname, e.eid, e.ename, d.did, d.dname "
                    + "FROM LeaveRequest r "
                    + "INNER JOIN Users u ON u.username = r.createdby "
                    + "INNER JOIN Employees e ON e.eid = u.eid "
                    + "INNER JOIN Departments d ON d.did = e.did "
                    + "WHERE r.createdby = ? "
                    + "ORDER BY r.rid ASC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            System.out.println("SQL OFFSET: " + offset + ", FETCH NEXT: " + pagesize);

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, createdby);
            stm.setInt(2, offset);
            stm.setInt(3, pagesize);

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

                Department d = new Department();
                e.setDept(d);
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));

                list.add(r);
            }

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Fetched records: " + list.size());
        return list;
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

    public ArrayList<LeaveRequest> getByManager(int managerId, int pagesize, int pageindex) {
        ArrayList<LeaveRequest> requests = new ArrayList<>();

        if (pageindex < 1) {
            pageindex = 1;
        }
        if (pagesize < 1) {
            pagesize = 10;
        }

        int offset = (pageindex - 1) * pagesize;

        try {
            String sql = "WITH EmployeeHierarchy AS ( "
                    + "SELECT eid, ename, managerid, did, 0 AS [Level] "
                    + "FROM Employees WHERE eid = ? "
                    + "UNION ALL "
                    + "SELECT e.eid, e.ename, e.managerid, e.did, eh.Level + 1 "
                    + "FROM Employees e INNER JOIN EmployeeHierarchy eh ON eh.eid = e.managerid "
                    + ") "
                    + "SELECT lr.rid, lr.title, lr.reason, lr.[from], lr.[to], lr.status, lr.createddate, "
                    + "u.username, u.displayname, eh.eid, eh.ename, d.did, d.dname "
                    + "FROM EmployeeHierarchy eh "
                    + "INNER JOIN Users u ON u.eid = eh.eid "
                    + "INNER JOIN LeaveRequest lr ON lr.createdby = u.username "
                    + "INNER JOIN Departments d ON d.did = eh.did "
                    + "WHERE eh.Level > 0 "
                    + "ORDER BY lr.rid ASC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            PreparedStatement stm = connection.prepareStatement(sql);

            // ✅ Đúng thứ tự tham số truyền vào
            stm.setInt(1, managerId); // eid = ?
            stm.setInt(2, offset);    // OFFSET ?
            stm.setInt(3, pagesize);  // FETCH NEXT ?

            System.out.println("managerId = " + managerId);
            System.out.println("pageindex = " + pageindex);
            System.out.println("pagesize = " + pagesize);
            System.out.println("offset = " + offset);

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

                Department d = new Department();
                e.setDept(d);
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));

                requests.add(r);
            }

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return requests;
    }

    public ArrayList<LeaveDateDTO> getLeaveDatesByEmployeeAndMonth(int employeeId, int month, int year) {
        ArrayList<LeaveDateDTO> leaveDates = new ArrayList<>();

        String sql = "SELECT lr.[from], lr.[to] FROM LeaveRequest lr "
                + "INNER JOIN [Users] u ON lr.createdby = u.username\n"
                + "INNER JOIN Employees e ON u.eid = e.eid\n"
                + "WHERE e.eid = ? AND status = 2 "
                + "AND ((YEAR([from]) = ? AND MONTH([from]) = ?) "
                + "  OR (YEAR([to]) = ? AND MONTH([to]) = ?))";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, employeeId);
            stm.setInt(2, year);
            stm.setInt(3, month);
            stm.setInt(4, year);
            stm.setInt(5, month);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                LeaveDateDTO date = new LeaveDateDTO();
                date.setFrom(rs.getDate("from").toLocalDate());
                date.setTo(rs.getDate("to").toLocalDate());
                leaveDates.add(date);
            }

            rs.close();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leaveDates;
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
