import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type of the response
        response.setContentType("text/html");

        // Get a PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Get all parameter names and their values
        Enumeration<String> parameterNames = request.getParameterNames();

        // Print parameter names and values
        out.println("<h1>Employee Details:</h1>");
        out.println("<ul>");
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            out.println("<li>" + paramName + ": " + paramValue + "</li>");
        }
        out.println("</ul>");
    }
}
