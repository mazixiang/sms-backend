package filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
    httpRequest.setCharacterEncoding("utf-8");
    HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
    httpResponse.setHeader("content-type", "application/json");

    // allow cross domain access
    httpResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST");

    filterChain.doFilter(httpRequest, httpResponse);
  }
}
