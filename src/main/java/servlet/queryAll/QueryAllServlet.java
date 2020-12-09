package servlet.queryAll;

import dao.StaffDao;
import dao.StaffDaoImpl;
import vo.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryAllServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StaffDao staffDao = new StaffDaoImpl();
        try {
            List<Staff> staffList = staffDao.queryAll();
            req.setAttribute("staffList", staffList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
