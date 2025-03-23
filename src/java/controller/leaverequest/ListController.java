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
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ListController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        // Chưa dùng POST
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {

        int pagesize = 10;

        // Lấy pageindex từ request
        String raw_pageindex = req.getParameter("page");
        int pageindex = 1;

        try {
            pageindex = (raw_pageindex == null || raw_pageindex.isEmpty()) ? 1 : Integer.parseInt(raw_pageindex);
        } catch (NumberFormatException e) {
            pageindex = 1;
        }

        // DBContext chỉ cần khởi tạo 1 lần
        LeaveRequestDBContext db = new LeaveRequestDBContext();

        // Tổng số dòng (bản ghi)
        int totalrows = db.count(user.getE().getId());

        // Tính tổng số trang
        int totalpage = (totalrows + pagesize - 1) / pagesize;

        // Giới hạn pageindex hợp lệ
        if (totalpage == 0) totalpage = 1;
        if (pageindex < 1) pageindex = 1;
        if (pageindex > totalpage) pageindex = totalpage;

        // Lấy danh sách theo trang
        db = new LeaveRequestDBContext();
        ArrayList<LeaveRequest> list = db.getByManager(user.getE().getId(), pagesize, pageindex);

        // Debug log nếu cần
        System.out.println("User ID: " + user.getE().getId());
        System.out.println("Total rows: " + totalrows);
        System.out.println("Page index: " + pageindex);
        System.out.println("Page size: " + pagesize);
        System.out.println("Total pages: " + totalpage);
        System.out.println("List size: " + list.size());

        // Đưa dữ liệu ra view
        req.setAttribute("pageindex", pageindex);
        req.setAttribute("totalpage", totalpage);
        req.setAttribute("list", list);
        req.setAttribute("size", list.size());

        req.getRequestDispatcher("../view/leave/list.jsp").forward(req, resp);
    }
}
