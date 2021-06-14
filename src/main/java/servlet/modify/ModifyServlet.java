package servlet.modify;

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

public class ModifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set response type to json
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "application/json");


        // allow cross domain access
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");


        // init staffDao
        StaffDao staffDao = new StaffDaoImpl();

        int id = Integer.parseInt(req.getParameter("id"));
        Staff targetStaff = new Staff();
        targetStaff.setId(id);

        try {
            Staff queryResult = staffDao.queryById(targetStaff);
            JSONObject result = JSONUtil.createObj();
            result.put("target", queryResult.toJsonObject());
            resp.getWriter().print(JSONUtil.toJsonStr(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
