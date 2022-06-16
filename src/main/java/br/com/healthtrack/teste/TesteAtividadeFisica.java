package br.com.healthtrack.teste;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.AtividadeFisica;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

public class TesteAtividadeFisica {

	public static void main(String[] args) {
		AtividadeFisicaDAO atividadeFisicaDao = DAOFactory.getAtividadeFisicaDAO();
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();

		AtividadeFisica atividadeF = new AtividadeFisica(0, "Musculação", Calendar.getInstance(), Calendar.getInstance(), "Abdominais", 340, "Foram feitas 32 repetições de abdominais");

		Usuario userLogged = new Usuario();
		userLogged.setEmail("vitoroliveirafranco@gmail.com");
		userLogged.setPassword("123545");
		
		Usuario userFull = null;		
		try {
			userFull = userDao.getUserByCredentials(userLogged);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		atividadeF.setUsuario(userFull);


		try {			
			atividadeFisicaDao.insert(atividadeF);
		} catch (DBException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Get All ============================================");

		List<AtividadeFisica> afList = atividadeFisicaDao.getAll();		
		for(AtividadeFisica afActual : afList) {
			System.out.println(afActual);
		}

		System.out.println("Get By ID ============================================");

		AtividadeFisica afActual = atividadeFisicaDao.getById(2);
		System.out.println(afActual);


		System.out.println("Editar ============================================");

		afActual.setCalorias(123);
		afActual.setDescricao("Diversas abdominais");
		afActual.setNome("Abdominal Russo");
		try {
			atividadeFisicaDao.update(afActual);
			System.out.println("Registro editado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		System.out.println("Deletar ============================================");
		try {
			atividadeFisicaDao.delete(2);
			System.out.println("Deletado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
