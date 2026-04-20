import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet extends GenericServlet {
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>HELLO WORLD</h1>");
        out.println("</body></html>");
    }
}


/*Ex.No.5: Creation of Dynamic Web pages DHTML – Part I 
6.a. Write a DHTML code to move the text randomly on the web page 
where the color and font-size of the text vary dynamically. 
6.b. Add an image to the web page. Write a DHTML code that corresponds to    
the movement of mouse any where on the page. Display the element name   
along with its (X,Y) co-ordinates, whenever the mouse is moved over the    
page. 
6.c. Design a web page using DHTML such that one image gets loaded when  
the mouse is moved over the page and another image gets loaded when the  
mouse loses its control inside the page. 
6.d. Write a DHTML code to display all the XHTML elements in a document,  
in the order in which they appear using all and child collections. 
6.e. Write a DHTML code that responds to a click anywhere on the page by  
displaying an alert dialog. Display the event name if the user held SHIFT 
during the mouse click. Display the element name that triggered the event  
if the user held CTRL during the mouse click. 
__________________________________________________________________________________ */