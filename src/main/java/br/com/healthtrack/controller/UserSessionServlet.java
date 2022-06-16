package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.factory.DAOFactory;
import br.com.healthtrack.exception.DBException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user")
public class UserSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;

	public UserSessionServlet() {
		dao = DAOFactory.getUsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAction = request.getParameter("action");
		
		switch(userAction) {
			case "signin":
				signIn(request); break;
			case "signup":
				signUp(request, response); break;
		}
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	private void signIn(HttpServletRequest request) {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario(email, senha);
		Usuario currentUser = dao.getUserByCredentials(usuario);

		if (currentUser != null) {
			HttpSession session = request.getSession();
			int usuarioID = currentUser.getId();
			session.setAttribute("user", currentUser);
			session.setAttribute("userID", usuarioID);
		} else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			double altura = Double.parseDouble ( request.getParameter("altura") );
			char sexo = request.getParameter("sexo").charAt(0);
			int idade = Integer.parseInt( request.getParameter("idade") );
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dtNascimento = Calendar.getInstance();
			dtNascimento.setTime(format.parse(request.getParameter("dtNascimento")));

			Usuario usuario = new Usuario(0, nome, dtNascimento, sexo, altura, idade, email, senha);

			if (usuario != null) {
				HttpSession session = request.getSession();
				int usuarioID = usuario.getId();
				
				session.setAttribute("user", usuario);
				session.setAttribute("userID", usuarioID);
				
				request.setAttribute("msg", "Usuário cadastrado!");
				
			} else {
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados" + e.getMessage());
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
