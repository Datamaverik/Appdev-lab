import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get selected language from the request parameter
        String language = request.getParameter("language");

        // Create a cookie with the selected language
        Cookie languageCookie = new Cookie("favoriteLanguage", language);
        languageCookie.setMaxAge(3600); // Cookie expires in 1 hour
        response.addCookie(languageCookie);

        // Send XHTML response to the client
        sendXHTMLResponse(response, "Favorite Language Set to " + language);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read cookies from the client
        Cookie[] cookies = request.getCookies();

        // Get the value of the favorite language cookie
        String favoriteLanguage = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("favoriteLanguage")) {
                    favoriteLanguage = cookie.getValue();
                    break;
                }
            }
        }

        // Send XHTML response to the client with book recommendations based on the favorite language
        sendXHTMLResponse(response, "Book Recommendations for " + favoriteLanguage);
    }

    private void sendXHTMLResponse(HttpServletResponse response, String message)
            throws IOException {
        // Set content type of the response
        response.setContentType("application/xhtml+xml");

        // Get a PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Generate the XHTML document dynamically
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"");
        out.println("    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\" />");
        out.println("    <title>Book Recommendations</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>" + message + "</h1>");
        out.println("    <p>List of recommended books goes here...</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
