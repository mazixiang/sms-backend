package servlet.update;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import dao.StaffDao;
import dao.StaffDaoImpl;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.Staff;

public class UpdateServletJson extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // set response type to json
    resp.setCharacterEncoding("utf-8");
    resp.setHeader("content-type", "application/json");

    // allow cross domain access
    resp.setHeader("Access-Control-Allow-Origin", "*");
    resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

    req.setCharacterEncoding("utf-8");
    BufferedReader reader = req.getReader();
    String staffJsonString = reader.readLine();
    Staff staff = Staff.jsonStringToStaff(staffJsonString);

    StaffDao staffDao = new StaffDaoImpl();

    String updateResultString;

    if (staff.getId() == 0) {
      // add
      try {
        staffDao.insert(staff);
        updateResultString = "success";
      } catch (Exception e) {
        e.printStackTrace();
        updateResultString = "failed";
      }
    } else {
      // update
      try {
        staffDao.update(staff);
        updateResultString = "success";
      } catch (Exception e) {
        e.printStackTrace();
        updateResultString = "failed";
      }
    }

    JSONObject updateResultJson = JSONUtil.createObj();
    updateResultJson.put("updateResult", updateResultString);
    resp.getWriter().print(JSONUtil.toJsonStr(updateResultJson));
  }
}
