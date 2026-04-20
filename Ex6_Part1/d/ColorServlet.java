import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ColorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type of the response
        response.setContentType("text/html");

        // Get selected color from the request parameter
        String selectedColor = request.getParameter("color");

        // Get a PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Display the selected color in the servlet window
        out.println("<h1>Selected Color: " + selectedColor + "</h1>");
    }
}
