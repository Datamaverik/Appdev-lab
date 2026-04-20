import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckboxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set content type of the response
        response.setContentType("text/html");

        // Get selected checkbox options from the request parameter
        String[] selectedFruits = request.getParameterValues("fruit");

        // Get a PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Generate HTML response dynamically
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Selected Fruits</title>");
        out.println("</head>");
        out.println("<body>");

        // Display selected fruits
        out.println("<h1>Selected Fruits:</h1>");
        if (selectedFruits != null && selectedFruits.length > 0) {
            out.println("<ul>");
            for (String fruit : selectedFruits) {
                out.println("<li>" + fruit + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No fruits selected.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
