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
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

@WebServlet("/ingestao")
public class IngestaoAguaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private IngestaoAguaDAO dao;

	@Override
    public void init() throws ServletException {
        super.init();
        
        dao = DAOFactory.getIngestaoAguaDAO();  
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch(acao) {
		case "listar":
			break;
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<IngestaoAgua> lista = dao.getAll();
		request.setAttribute("ingestoes", lista);
		request.getRequestDispatcher("listarIngestaoAgua.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
			case "cadastrar":
				cadastrar(request, response);
				break;
			case "editar":
				// editar(request,response);
				break;
			case "excluir":
				// excluir(request, response);
				break;
		}
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
            System.out.println(ia);
            
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