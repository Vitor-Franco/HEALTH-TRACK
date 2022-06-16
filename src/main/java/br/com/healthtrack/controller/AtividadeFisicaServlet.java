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

import br.com.healthtrack.bean.AtividadeFisica;
import br.com.healthtrack.bean.IngestaoAgua;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtrack.dao.IngestaoAguaDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/atividade")
public class AtividadeFisicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AtividadeFisicaDAO dao;
	private UsuarioDAO userDao;

    public AtividadeFisicaServlet() {
        dao = DAOFactory.getAtividadeFisicaDAO();  
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
		AtividadeFisica atividade = dao.getById(id);
		request.setAttribute("atividade", atividade);
		request.getRequestDispatcher("edicaoAtividadeFisica.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AtividadeFisica> lista = dao.getAll();
		request.setAttribute("atividades", lista);
		request.getRequestDispatcher("listarAtividadeFisica.jsp").forward(request, response);
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
			request.setAttribute("msg", "Atividade física removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String tipo = request.getParameter("tipoAtividade");			
			String nomeAtividade = request.getParameter("nomeAtividade");
			int calorias = Integer.parseInt(request.getParameter("calorias"));
			String descricao = request.getParameter("descricaoAtividade");		
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataAtividade = Calendar.getInstance();
			dataAtividade.setTime(format.parse(request.getParameter("dataAtividade")));
			
			Calendar dataDuracao = Calendar.getInstance();
			dataAtividade.setTime(format.parse(request.getParameter("dataDuracao")));
			
			HttpSession session = request.getSession();
            String sessionID = (String) session.getAttribute("userID");
            int usuarioId = Integer.parseInt(sessionID);

            Usuario usuario = userDao.getById(usuarioId);
			
            if(usuario == null) {
            	throw new Exception("Usuário não encontrado");
            }
            
            AtividadeFisica af = new AtividadeFisica(codigo, tipo, dataAtividade, dataDuracao, nomeAtividade, calorias, descricao);
                        
            af.setUsuario(usuario);
            
			dao.update(af);

			request.setAttribute("msg", "Atividade física atualizada!");
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
			String tipo = request.getParameter("tipoAtividade");			
			String nomeAtividade = request.getParameter("nomeAtividade");
			int calorias = Integer.parseInt(request.getParameter("calorias"));
			String descricao = request.getParameter("descricaoAtividade");		
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataAtividade = Calendar.getInstance();
			dataAtividade.setTime(format.parse(request.getParameter("dataAtividade")));
			
			Calendar dataDuracao = Calendar.getInstance();
			dataAtividade.setTime(format.parse(request.getParameter("dataDuracao")));
			
			HttpSession session = request.getSession();
            Usuario usuario = (Usuario)session.getAttribute("user");
			
            AtividadeFisica af = new AtividadeFisica(0, tipo, dataAtividade, dataDuracao, nomeAtividade, calorias, descricao);                        
            af.setUsuario(usuario);
            
			dao.insert(af);

			request.setAttribute("msg", "Atividade física cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao inserir");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}		
	}

}
