/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manage;

import controller.auth.BaseRequiredAuthenticationController;
import dal.LeaveRequestDBContext;
import data.Employee;
import data.User;
import dto.LeaveDateDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author admin
 */
public class TimeTableController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        //get direct staff
        ArrayList<Employee> staffs = user.getE().getStaffs();

        //get calender
        int employeeId = Integer.parseInt(Optional.ofNullable(req.getParameter("employeeId")).orElse(String.valueOf(staffs.get(0).getId())));
        int month = Integer.parseInt(Optional.ofNullable(req.getParameter("month")).orElse(String.valueOf(LocalDate.now().getMonthValue())));
        int year = Integer.parseInt(Optional.ofNullable(req.getParameter("year")).orElse(String.valueOf(LocalDate.now().getYear())));

        // Lấy danh sách ngày nghỉ từ DB
        LeaveRequestDBContext leaveDB = new LeaveRequestDBContext();
        ArrayList<LeaveDateDTO> leaveDates = leaveDB.getLeaveDatesByEmployeeAndMonth(employeeId, month, year);

        // Convert ngày nghỉ thành set
        Set<LocalDate> leaveDaysSet = new HashSet<>();
        for (LeaveDateDTO ld : leaveDates) {
            LocalDate date = ld.getFrom();
            while (!date.isAfter(ld.getTo())) {
                leaveDaysSet.add(date);
                date = date.plusDays(1);
            }
        }

        // Tạo calendarData: List<List<Map<String, Object>>>
        List<List<Map<String, Object>>> calendarData = buildCalendar(year, month, leaveDaysSet);

        
        
        // Forward data về JSP
        req.setAttribute("calendarData", calendarData);
        req.setAttribute("employeeId", employeeId);
        req.setAttribute("month", month);
        req.setAttribute("year", year);
        req.setAttribute("directstaffs", staffs);
        
        //url
        req.getRequestDispatcher("/view/manage/time_table.jsp").forward(req, resp);
    }

    private List<List<Map<String, Object>>> buildCalendar(int year, int month, Set<LocalDate> leaveDaysSet) {
       List<List<Map<String, Object>>> calendarData = new ArrayList<>();

        LocalDate firstDay = LocalDate.of(year, month, 1);
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;

        int currentDay = 1;

        while (currentDay <= daysInMonth) {
            List<Map<String, Object>> week = new ArrayList<>();

            for (int dow = 0; dow < 7; dow++) {
                Map<String, Object> dayCell = new HashMap<>();

                if ((calendarData.isEmpty() && dow < (firstDayOfWeek == 0 ? 6 : firstDayOfWeek - 1)) || currentDay > daysInMonth) {
                    dayCell.put("day", 0);
                    dayCell.put("type", "empty-day");
                } else {
                    LocalDate currentDate = LocalDate.of(year, month, currentDay);
                    boolean isToday = currentDate.equals(LocalDate.now());
                    boolean isLeave = leaveDaysSet.contains(currentDate);

                    dayCell.put("day", currentDay);
                    dayCell.put("isToday", isToday);
                    dayCell.put("type", isLeave ? "leave-day" : "working-day");

                    currentDay++;
                }

                week.add(dayCell);
            }

            calendarData.add(week);
        }

        return calendarData;
    }
    

}
