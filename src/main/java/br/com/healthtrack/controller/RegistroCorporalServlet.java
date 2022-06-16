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

import br.com.healthtrack.bean.RegistroCorporal;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RegistroCorporalDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class RegistroCorporal
 */
@WebServlet("/registroCorporal")
public class RegistroCorporalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private RegistroCorporalDAO dao;
    private UsuarioDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        
        dao = DAOFactory.getRegistroCorporalDAO();       
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
		List<RegistroCorporal> lista = dao.getAll();
		request.setAttribute("registros", lista);
		request.getRequestDispatcher("listarRegistroCorporal.jsp").forward(request, response);
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String sessionID = (String) session.getAttribute("userID");
			int usuarioId = Integer.parseInt(sessionID);	
			
			Usuario usuario = userDao.getById(usuarioId);
			
			Calendar dtRegistro = Calendar.getInstance();
			double peso = Double.parseDouble ( request.getParameter("peso") );
						
			RegistroCorporal ia = new RegistroCorporal(0, peso, dtRegistro);
            ia.setUsuario(usuario);
            dao.insert(ia);

            request.setAttribute("msg", "Registro Corporal cadastrado!");
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
		RegistroCorporal ia = dao.getById(id);
		request.setAttribute("RegistroCorporal", ia);
		request.getRequestDispatcher("edicaoRegistroCorporal.jsp").forward(request, response);
	}

}
