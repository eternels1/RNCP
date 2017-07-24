package TodoManager.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TodoManager.dao.TacheDAO;
import TodoManager.metier.Tache;


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
		if (request.getParameter("tacheId")==null) {
			Tache t= new Tache(0,"tache...",0,"contexte...",false);
			request.setAttribute("tache", t);
			request.setAttribute("titre", "Céation Tache");
		}
		else {
			Tache t= tacheDAO.findOne(Integer.parseInt(request.getParameter("tacheId")));
			if (t==null) {
				response.sendRedirect("TodoManager");
				return;
			}
			request.setAttribute("tache", t);
			request.setAttribute("titre", "Edition Tache");
		}
		
		getServletContext().getRequestDispatcher("/vues/tache/formtache.jsp")
							.forward(request, response);
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id")==null) {
			response.sendRedirect("TodoManager");
			return;
		}
		
		boolean booltemp=false;
		if ("on".equals(request.getParameter("checkboxfinished"))) {
			booltemp=true;
		}
		
		
		Tache t = new Tache(Integer.parseInt(request.getParameter("id")),
									request.getParameter("description"),
									Integer.parseInt(request.getParameter("priorite")),
									request.getParameter("contexte"),
									booltemp);
		
		tacheDAO.saveTache(t);
		response.sendRedirect("TodoManager");
		
	}

}
