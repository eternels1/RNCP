package TodoManager.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TodoManager.dao.TacheDAO;


@WebServlet("/TodoManager")
public class TodoManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TacheDAO tacheDAO;
	
	
   
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.tacheDAO=(TacheDAO)getServletContext().getAttribute("tacheDAO");
	}

	public TodoManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lstTaches", tacheDAO.findAll());
		getServletContext().getRequestDispatcher("/vues/tache/listetache.jsp")
							.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("tacheId")==null) {
			System.out.println("Pas d'id, pas de suppression...");
			response.sendRedirect("TodoManager");
			return;
		}
		//faire le delete
		response.sendRedirect("TodoManager");
	}

}
