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

import br.com.healthtrack.bean.IngestaoAgua;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.IngestaoAguaDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ingestao")
public class IngestaoAguaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IngestaoAguaDAO dao;
	private UsuarioDAO userDao;

    public IngestaoAguaServlet() {
        dao = DAOFactory.getIngestaoAguaDAO();  
        userDao = DAOFactory.getUsuarioDAO();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
    	
		switch (acao) {
			case "listar":
				listar(request, response);
				break;
			case "abrir-form-edicao":
				abrirFormEdicao(request, response);
				break;
		}
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		IngestaoAgua ingestao = dao.getById(id);
		request.setAttribute("ingestao", ingestao);
		request.getRequestDispatcher("edicaoIngestaoAgua.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<IngestaoAgua> lista = dao.getAll();
		request.setAttribute("ingestoes", lista);
		request.getRequestDispatcher("listarIngestaoAgua.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
			case "cadastrar":
				cadastrar(request, response);
				break;
			case "editar":
				editar(request,response);
				break;
			case "excluir":
				excluir(request, response);
				break;
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		try {
			dao.delete(codigo);
			request.setAttribute("msg", "Ingestão de água removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			double qtdAgua = Double.parseDouble(request.getParameter("quantidadeAgua"));
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dtConsumo = Calendar.getInstance();
			dtConsumo.setTime(format.parse(request.getParameter("data")));

			HttpSession session = request.getSession();
            String sessionID = (String) session.getAttribute("userID");
            int usuarioId = Integer.parseInt(sessionID);

            Usuario usuario = userDao.getById(usuarioId);
			
            if(usuario == null) {
            	throw new Exception("Usuário não encontrado");
            }
            
            IngestaoAgua ia = new IngestaoAgua(codigo, dtConsumo, qtdAgua);            
            ia.setUsuario(usuario);
            
			dao.update(ia);

			request.setAttribute("msg", "Ingestão de água atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}	
		
		listar(request,response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double qtdAgua = Double.parseDouble(request.getParameter("quantidadeAgua"));
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dtConsumo = Calendar.getInstance();
			dtConsumo.setTime(format.parse(request.getParameter("data")));

			HttpSession session = request.getSession();
            Usuario usuario = (Usuario)session.getAttribute("user");
			
            IngestaoAgua ia = new IngestaoAgua(0, dtConsumo, qtdAgua);
            
            ia.setUsuario(usuario);            
			dao.insert(ia);

			request.setAttribute("msg", "Ingestão de água cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao inserir");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}		
	}

}
