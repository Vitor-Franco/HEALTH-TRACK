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
import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtrack.dao.IngestaoAguaDAO;
import br.com.healthtrack.dao.RefeicaoDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/refeicao")
public class RefeicaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RefeicaoDAO dao;

	public RefeicaoServlet() {
		dao = DAOFactory.getRefeicaoDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		Refeicao refeicao = dao.getById(id);
		request.setAttribute("refeicao", refeicao);
		request.getRequestDispatcher("edicaoRefeicao.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Refeicao> lista = dao.getAll();
		request.setAttribute("refeicoes", lista);
		request.getRequestDispatcher("listarRefeicao.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));

		try {
			dao.delete(codigo);
			request.setAttribute("msg", "Refeição removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nomeRefeicao = request.getParameter("nomeRefeicao");
			String descricao = request.getParameter("descricaoRefeicao");

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataRefeicao = Calendar.getInstance();
			dataRefeicao.setTime(format.parse(request.getParameter("dataRefeicao")));

			Refeicao r = new Refeicao(codigo, dataRefeicao, descricao, nomeRefeicao);

			dao.update(r);

			request.setAttribute("msg", "Refeição atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}

		listar(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String nomeRefeicao = request.getParameter("nomeRefeicao");
			String descricao = request.getParameter("descricaoRefeicao");

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataRefeicao = Calendar.getInstance();
			dataRefeicao.setTime(format.parse(request.getParameter("dataAtividade")));

			Refeicao r = new Refeicao(0, dataRefeicao, descricao, nomeRefeicao);

			dao.insert(r);

			request.setAttribute("msg", "Refeição cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao inserir");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
	}

}
