/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.leaverequest;

import controller.auth.BaseRequiredAuthenticationController;
import dal.LeaveRequestDBContext;
import data.LeaveRequest;
import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ReviewController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String raw_id = req.getParameter("requestid");
        String action = req.getParameter("action");
        System.out.println(raw_id);
        if (raw_id == null || raw_id.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu request ID.");
            return;
        }

        int requestId;
        try {
            requestId = Integer.parseInt(raw_id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request ID không hợp lệ.");
            return;
        }

        LeaveRequestDBContext db = new LeaveRequestDBContext();
        try {
            LeaveRequest leave = db.get(requestId);
            
            if (leave == null || leave.getId() == 0) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn nghỉ phép.");
                return;
            }

            int status = "approve".equals(action) ? 2 : 1;
            db.update(leave, status);

            resp.sendRedirect("list");
        } finally {
            db.close();
        }
    }

        @Override
        protected void doGet
        (HttpServletRequest req, HttpServletResponse resp
        , User user) throws ServletException, IOException {
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            LeaveRequest lr = db.getByTitle(user.getE().getId(), req.getParameter("title"));
            req.setAttribute("lr", lr);
            req.getRequestDispatcher("../view/leave/review.jsp").forward(req, resp);
        }

    }
