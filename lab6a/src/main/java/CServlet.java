import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CServlet")
public class CServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Form Parameters</title></head>");
        out.println("<body>");
        out.println("<h2>Form Parameters</h2>");
        
        // Retrieve all form parameter names and their corresponding values
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            out.println("<p>" + paramName + ": " + paramValue + "</p>");
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}