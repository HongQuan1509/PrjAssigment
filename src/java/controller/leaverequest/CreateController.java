/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.leaverequest;

import controller.auth.BaseRequiredAuthenticationController;
import dal.LeaveRequestDBContext;
import data.LeaveRequest;
import data.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;



/**
 *
 * @author admin
 */
public class CreateController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String title = req.getParameter("title");
        String reason = req.getParameter("reason");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        
        try {
        LeaveRequest lr = new LeaveRequest();
        lr.setTitle(title);
        lr.setReason(reason);
        lr.setFrom(Date.valueOf(from));
        lr.setTo(Date.valueOf(to));
        lr.setCreatedby(user);

        LeaveRequestDBContext db = new LeaveRequestDBContext();
        db.insert(lr);

        // Thêm message vào request
        req.setAttribute("message", "Leave request was send. Wait for approval!");
                req.getRequestDispatcher("../view/leave/create.jsp").forward(req, resp);

    } catch (Exception e) {
        req.setAttribute("error", "Oops, error " + e);
                req.getRequestDispatcher("../view/leave/create.jsp").forward(req, resp);

    }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        req.getRequestDispatcher("../view/leave/create.jsp").forward(req, resp);
    }
   
  
}
