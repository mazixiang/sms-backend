package servlet.delete;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import dao.StaffDao;
import dao.StaffDaoImpl;
import vo.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set response type to json
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "application/json");


        // allow cross domain access
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        StaffDao staffDao = new StaffDaoImpl();

        String id = req.getParameter("id");

        Staff staff = new Staff();
        staff.setId(id);

        // return json result
        JSONObject deleteResult = JSONUtil.createObj();
        String deleteResultString;

        try {
            staffDao.delete(staff);
            resp.getWriter().println("delete success");
            deleteResultString = "success";
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("delete failed");
            deleteResultString = "failed";
        }

        deleteResult.put("deleteResult", deleteResultString);
        resp.getWriter().println(JSONUtil.toJsonStr(deleteResult));
    }
}
