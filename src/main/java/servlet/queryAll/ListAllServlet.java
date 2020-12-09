package servlet.queryAll;

import vo.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListAllServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/queryAll").include(req, resp);

        List<Staff> staffList = (List<Staff>) req.getAttribute("staffList");

        // Chinese support
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");

        StringBuilder html = new StringBuilder();
        html.append("<table border=\"1\" title=\"staff table\">")
                .append("<caption>Staff Table</caption>")
                .append("<thead>")
                .append("<tr>")
                .append("<th scope=\"col\">ID</th>")
                .append("<th scope=\"col\">name</th>")
                .append("<th scope=\"col\">password")
                .append("</th>")
                .append("<th scope=\"col\">gender</th>")
                .append("<th scope=\"col\">age</th>")
                .append("<th scope=\"col\">contact")
                .append(" info</th>")
                .append("<th scope=\"col\">hobbies</th>")
                .append("<th scope=\"col\">department</th>")
                .append("<th ")
                .append("scope=\"col\">operations</th>")
                .append("</tr>")
                .append("</thead>");

        for (Staff staff : staffList) {
            html.append("<tr>" + "<td>")
                    .append(staff.getId())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getPassword())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getGender())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getAge())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getContactInfo())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getHobbies())
                    .append("</td>")
                    .append("<td>")
                    .append(staff.getDepartment())
                    .append("</td>")
                    .append("<td><a href=\"modify?id=")
                    .append(staff.getId())
                    .append("\">modify</a>&nbsp;<a href=\"delete?id=")
                    .append(staff.getId())
                    .append("\">delete</a></td>")
                    .append("</tr>");
        }

        writer.print(html.toString());
    }
}
