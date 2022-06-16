package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AlimentoDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class RegistroCorporal
 */
@WebServlet("/alimento")
public class AlimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private AlimentoDAO dao;
    private UsuarioDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        
        dao = DAOFactory.getAlimentoDAO();       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
    	
		switch (acao) {
			case "listar":
				listar(request, response);
				break;
			case "abrirEdit":
				abrirEdit(request, response); break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAction = request.getParameter("action");
		
		switch(userAction) {
			case "create":
				create(request, response); break;
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Alimento> lista = dao.getAll();
		request.setAttribute("alimentos", lista);
		request.getRequestDispatcher("listarALimentos.jsp").forward(request, response);
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nomeAlimento = request.getParameter("nomeAlimento");
			int calorias = Integer.parseInt ( request.getParameter("calorias") );
			String macroNutrientes = request.getParameter("macroNutrientes");
						
			Alimento ia = new Alimento(0, nomeAlimento, calorias, macroNutrientes);
            dao.insert(ia);

            request.setAttribute("msg", "ALimento cadastrado!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao inserir");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }   

	}
	
	private void abrirEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Alimento ia = dao.getById(id);
		request.setAttribute("Alimentos", ia);
		request.getRequestDispatcher("edicaoAlimento.jsp").forward(request, response);
	}

}
