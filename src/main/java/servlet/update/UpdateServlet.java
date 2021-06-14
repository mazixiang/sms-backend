package servlet.update;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import dao.StaffDao;
import dao.StaffDaoImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.Staff;

public class UpdateServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // set response type to json
    resp.setCharacterEncoding("utf-8");
    resp.setHeader("content-type", "application/json");

    // allow cross domain access
    resp.setHeader("Access-Control-Allow-Origin", "*");
    resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

    // Process hobbies string
    String[] hobbies = req.getParameterValues("hobby");
    StringBuilder hobbiesStringBuilder = new StringBuilder();
    if (hobbies != null) {
      for (int i = 0; i < hobbies.length; i++) {
        if (i != 0) {
          hobbiesStringBuilder.append(",");
        }
        hobbiesStringBuilder.append(hobbies[i]);
      }
    }

    Staff staff = new Staff();

    // Get parameters and set attributes
    staff.setId(Integer.parseInt(req.getParameter("id")));
    staff.setName(req.getParameter("name"));
    staff.setPassword(req.getParameter("password"));
    staff.setGender(req.getParameter("gender"));
    staff.setAge(Integer.parseInt(req.getParameter("age")));
    staff.setContactInfo(req.getParameter("contactInfo"));
    staff.setHobbies(hobbiesStringBuilder.toString());
    staff.setDepartment(req.getParameter("department"));

    StaffDao staffDao = new StaffDaoImpl();

    String updateResultString;

    if (staff.getId() == 0) {
      // add
      try {
        staffDao.insert(staff);
        resp.getWriter().println("Insert success");
        updateResultString = "success";
      } catch (Exception e) {
        e.printStackTrace();
        resp.getWriter().println("Insert failed");
        updateResultString = "failed";
      }
    } else {
      // update
      try {
        staffDao.update(staff);
        resp.getWriter().println("update success");
        updateResultString = "success";
      } catch (Exception e) {
        e.printStackTrace();
        resp.getWriter().println("update failed");
        updateResultString = "failed";
      }
    }

    JSONObject updateResultJson = JSONUtil.createObj();
    updateResultJson.put("updateResult", updateResultString);
    resp.getWriter().print(JSONUtil.toJsonStr(updateResultJson));
  }
}
