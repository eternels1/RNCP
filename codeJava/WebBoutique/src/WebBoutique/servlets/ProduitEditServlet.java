package WebBoutique.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebBoutique.dao.ProduitDAO;
import WebBoutique.metier.Produit;


@WebServlet("/ProduitEdit")
public class ProduitEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ProduitDAO produitDAO;
	
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.produitDAO= (ProduitDAO)getServletContext().getAttribute("produitDAO");
	}


	public ProduitEditServlet() {
        super();       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("produitId")==null) {
			Produit p = new Produit(0,"produit...",0.001,0.001);
			request.setAttribute("produit", p);
			request.setAttribute("titre", "Création Produit");
		}
		else {
			Produit p= produitDAO.findOne(Integer.parseInt(request.getParameter("produitId")));
			if (p==null) {
				response.sendRedirect("Produit");
				return;
			}
			request.setAttribute("produit", p);
			request.setAttribute("titre", "Edition Produit");
		}
		getServletContext().getRequestDispatcher("/vues/produit/form.jsp")
							.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id")==null) {
			response.sendRedirect("Produits");
			return;
		}	
		Produit p = new Produit(Integer.parseInt(request.getParameter("id")),
				request.getParameter("nom"),
				Double.parseDouble(request.getParameter("prix")),
				Double.parseDouble(request.getParameter("poids")));
		
		produitDAO.saveProduit(p);
		
		response.sendRedirect("Produits");
				
			
		
	}

}
