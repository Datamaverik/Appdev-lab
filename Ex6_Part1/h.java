import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SurveyServlet extends HttpServlet {
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:cloudscape://localhost:1527/your_database";
    private static final String JDBC_USERNAME = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get selected animal from the request parameter
        String selectedAnimal = request.getParameter("animal");

        // Update the database with the vote
        updateDatabase(selectedAnimal);

        // Generate XHTML response with survey results
        sendSurveyResults(response);
    }

    private void updateDatabase(String selectedAnimal) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Update the votes for the selected animal in the database
            String sql = "UPDATE SurveyResults SET votes = votes + 1 WHERE animal = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, selectedAnimal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and Connection
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendSurveyResults(HttpServletResponse response) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Query the database for survey results
            String sql = "SELECT animal, votes FROM SurveyResults";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Calculate total number of responses
            int totalResponses = 0;
            while (rs.next()) {
                totalResponses += rs.getInt("votes");
            }

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
            out.println("<title>Survey Results</title>");
            out.println("</head>");
            out.println("<body>");

            // Display survey results
            out.println("<h1>Survey Results:</h1>");
            out.println("<p>Total Responses: " + totalResponses + "</p>");
            out.println("<ul>");
            rs.beforeFirst(); // Move cursor back to the beginning
            while (rs.next()) {
                String animal = rs.getString("animal");
                int votes = rs.getInt("votes");
                double percentage = (double) votes / totalResponses * 100;
                out.println("<li>" + animal + ": " + votes + " votes (" + String.format("%.2f", percentage) + "%)</li>");
            }
            out.println("</ul>");

            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
