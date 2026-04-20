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

public class FAQServlet extends HttpServlet {
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String JDBC_USERNAME = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // SQL query to retrieve topics and FAQs
            String sql = "SELECT t.topicName, f.question, f.answer FROM Topics t JOIN FAQs f ON t.topicID = f.topicID";

            // Create a PreparedStatement object
            stmt = connection.prepareStatement(sql);

            // Execute the query
            rs = stmt.executeQuery();

            // Set content type of the response
            response.setContentType("text/html");

            // Get a PrintWriter object to write the response
            PrintWriter out = response.getWriter();

            // Generate the HTML dynamically
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>FAQs</title>");
            out.println("</head>");
            out.println("<body>");

            // Iterate through the result set and display FAQs by topic
            String currentTopic = null;
            while (rs.next()) {
                String topicName = rs.getString("topicName");
                String question = rs.getString("question");
                String answer = rs.getString("answer");

                // Check if the topic has changed
                if (!topicName.equals(currentTopic)) {
                    currentTopic = topicName;
                    out.println("<h2>" + currentTopic + "</h2>");
                }

                // Display question and answer
                out.println("<p><strong>Q: " + question + "</strong></p>");
                out.println("<p>A: " + answer + "</p>");
            }

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
