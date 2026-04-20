import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type of the response
        response.setContentType("text/html");

        // Get a PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Generate the XHTML document dynamically
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Welcome to Servlets!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Welcome to Servlets!</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
