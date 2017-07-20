package WebBoutique.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebBoutique.dao.ProduitDAO;
import WebBoutique.metier.Produit;

@WebServlet("/Produits")
public class ProduitsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProduitDAO produitDAO;
	 
       
   
    public ProduitsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.produitDAO=(ProduitDAO)getServletContext().getAttribute("produitDAO");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				
				request.setAttribute("produits", produitDAO.findAll());
				getServletContext().getRequestDispatcher("/vues/produit/liste.jsp")
									.forward(request, response);
				
				
			
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
