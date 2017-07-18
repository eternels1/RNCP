package premierWeb1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>je suis la servlet</title></head>");
		pw.println("<body><h1>"+LocalDateTime.now()+"</h1></body>");
		pw.println("</html>");
	
		pw.close();
	}

	
	protected void doPost(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
		
	}

}
