import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get selected language from the request parameter
        String language = request.getParameter("language");

        // Generate a random ISBN number (for demonstration purposes)
        String isbn = "ISBN-" + Math.round(Math.random() * 1000000);

        // Get the HttpSession object for the client
        HttpSession session = request.getSession(true); // Create a new session if one doesn't exist

        // Add the selected language and ISBN to the HttpSession object
        session.setAttribute(language, isbn);

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
        out.println("<title>Session Information</title>");
        out.println("</head>");
        out.println("<body>");

        // Display session information
        out.println("<h1>Session Information:</h1>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<p>New Session: " + (session.isNew() ? "Yes" : "No") + "</p>");
        out.println("<p>Creation Time: " + session.getCreationTime() + "</p>");
        out.println("<p>Last Accessed Time: " + session.getLastAccessedTime() + "</p>");
        out.println("<p>Maximum Inactive Interval: " + session.getMaxInactiveInterval() + " seconds</p>");

        out.println("</body>");
        out.println("</html>");
    }
}
