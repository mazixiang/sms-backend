package servlet.queryAll;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import vo.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryAllJsonServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/queryAll").include(req, resp);

        // set response type to json
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "application/json");


        // allow cross domain access
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        List<Staff> staffList = (List<Staff>) req.getAttribute("staffList");

        JSONArray staffArray = new JSONArray();

        for (Staff staff : staffList) {
            staffArray.put(staff.toJsonObject());
        }

        JSONObject allStaffsJson = JSONUtil.createObj()
                .put("staffs", staffArray);

        resp.getWriter().print(JSONUtil.toJsonStr(allStaffsJson));
    }
}
