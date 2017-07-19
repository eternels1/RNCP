package exercice_web_1A.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class centreCalculServlet
 */
@WebServlet("/centreCalcul")
public class centreCalculServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public centreCalculServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/vues/calculon.jsp")
		   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	double premiernombre= 0;
	double deuxiemenombre= 0;
		String operateur= request.getParameter("operateur");
		try {
			 premiernombre= Integer.parseInt(request.getParameter("premiernombre"));
			 deuxiemenombre= Integer.parseInt(request.getParameter("deuxiemenombre"));
		} catch (NumberFormatException e) {
			getServletContext().getRequestDispatcher("/vues/erreurPage.jsp")
			   .forward(request, response);
			return;
		}
		
		double resultat=0;
		String messageresultat= premiernombre+operateur+deuxiemenombre+"=";
				
		
		switch (operateur) {
		
					case "+": resultat=(premiernombre+deuxiemenombre);
					messageresultat+=resultat;		
					break;
			
					case "-": resultat=premiernombre-deuxiemenombre;
					messageresultat+=resultat;
					break;
					
					case "*": resultat=premiernombre*deuxiemenombre;
					messageresultat+=resultat;
					break;
					
					case "/": 
						if (deuxiemenombre==0) {
							messageresultat+="impossible!";
							request.setAttribute("messageresultat", messageresultat);
							getServletContext().getRequestDispatcher("/vues/erreurPage.jsp")
							   .forward(request, response);
						}			
						
						resultat=premiernombre/deuxiemenombre;
					messageresultat+=resultat;
					break;
					
					default:
						break;
				}
		
		request.setAttribute("messageresultat", messageresultat);
		getServletContext().getRequestDispatcher("/vues/resultat.jsp")
		   .forward(request, response);
		
	}
	

}
