/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import data.Department;
import data.Employee;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class EmployeeDBContext extends DBContext<Employee> {

    @Override
    public ArrayList<Employee> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Employee get(int id) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "WITH EmployeeHierarchy AS(\n"
                    + "	SELECT \n"
                    + "	eid,\n"
                    + "	managerid,\n"
                    + "	0 as [Level]\n"
                    + "	FROM Employees\n"
                    + "	WHERE eid = ?\n"
                    + "\n"
                    + "	UNION ALL\n"
                    + "\n"
                    + "	SELECT e.eid,e.managerid, m.[Level] + 1 as [Level] \n"
                    + "	FROM Employees e\n"
                    + "	INNER JOIN EmployeeHierarchy m ON m.eid = e.managerid\n"
                    + ")\n"
                    + "SELECT ps.eid,ps.ename,ds.did,ds.dname,e.managerid,e.[Level],m.eid as managerid,m.ename as managername FROM \n"
                    + "	EmployeeHierarchy e INNER JOIN Employees ps ON ps.eid = e.eid\n"
                    + "	INNER JOIN Departments ds ON ds.did = ps.did \n"
                    + "	LEFT JOIN Employees m ON m.eid = e.managerid";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
                    Employee e = new Employee();
                    e.setId(rs.getInt("eid"));
                    e.setName(rs.getString("ename"));
                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    d.setName(rs.getString("dname"));
                    e.setDept(d);
                    int mid = rs.getInt("managerid");
                    if(mid != 0)
                    {
                        e.setManager(new Employee());
                        e.getManager().setId(mid);
                        e.getManager().setName(rs.getString("managername"));
                    }
                    employees.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            
        }
        if(!employees.isEmpty())
            {
                Employee root = employees.get(0);
                for (Employee employee : employees) {
                    if(employee != root)
                    {
                        //look up for its manager
                        Employee manager = getManagerNode(employees,employee);
                        employee.setManager(manager);
                        manager.getDirectstaffs().add(employee);
                        root.getStaffs().add(employee);
                    }
                }
                return root;
            }
            else 
                return null;
    }
    
    private Employee getManagerNode(ArrayList<Employee> emps, Employee e)
    {
        for (Employee emp : emps) {
            if(emp.getId() == e.getManager().getId())
            {
                return emp;
            }
        }
        return null;
    }

    @Override
    public void insert(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
 