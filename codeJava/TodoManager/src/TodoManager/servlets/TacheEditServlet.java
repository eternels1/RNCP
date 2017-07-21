package TodoManager.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TodoManager.dao.TacheDAO;


@WebServlet("/TacheEdit")
public class TacheEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TacheDAO tacheDAO;
	
	
   
    @Override
	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	this.tacheDAO=(TacheDAO)getServletContext().getAttribute("tacheDAO");
	}


	public TacheEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
