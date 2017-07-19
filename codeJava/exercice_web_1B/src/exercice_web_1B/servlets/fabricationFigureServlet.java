package exercice_web_1B.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class fabricationFigureServlet
 */
@WebServlet("/fabricationFigure")
public class fabricationFigureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fabricationFigureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/vues/figurons.jsp")
		   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			int taillefigure=0;
			
			try {
				taillefigure= Integer.parseInt(request.getParameter("taille"));
			} catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/vues/erreurTaillePage.jsp")
				   .forward(request, response);
				return;
			}


		String choixfigure= request.getParameter("choixfigure");
		taillefigure= Integer.parseInt(request.getParameter("taille"));
		
		request.setAttribute("taillefigure", taillefigure);
		
		
		switch (choixfigure) {
		
		case "0":getServletContext().getRequestDispatcher("/vues/trianglefigure.jsp")
		   .forward(request, response);
			
			break;
			
		case "1":getServletContext().getRequestDispatcher("/vues/careefigure.jsp")
		   .forward(request, response);
			
			break;

		default:
			break;
		}
				
		
		
	}

}
