package controller.leaverequest;

import controller.auth.BaseRequiredAuthenticationController;
import dal.LeaveRequestDBContext;
import data.LeaveRequest;
import data.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MyRequestController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
        throws ServletException, IOException {

    int pagesize = 10;
    int pageindex = 1;

    String raw_pageindex = req.getParameter("page");
    try {
        pageindex = (raw_pageindex == null || raw_pageindex.isEmpty()) ? 1 : Integer.parseInt(raw_pageindex);
    } catch (NumberFormatException e) {
        pageindex = 1;
    }

    LeaveRequestDBContext db = new LeaveRequestDBContext();

    
        int totalrows = db.countMy(user.getUsername());
        int totalpage = (totalrows + pagesize - 1) / pagesize;

        if (pageindex < 1) pageindex = 1;
        if (pageindex > totalpage) pageindex = totalpage;

        db = new LeaveRequestDBContext();
        ArrayList<LeaveRequest> list = db.getByCreator(user.getUsername(), pagesize, pageindex);

        req.setAttribute("pageindex", pageindex);
        req.setAttribute("totalpage", totalpage);
        req.setAttribute("list", list);
        req.setAttribute("size", list.size());

        req.getRequestDispatcher("../view/leave/myrequest.jsp").forward(req, resp);
    
}
}