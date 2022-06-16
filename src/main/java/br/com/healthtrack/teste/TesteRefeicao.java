package br.com.healthtrack.teste;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RefeicaoDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

public class TesteRefeicao {

	public static void main(String[] args) {
		RefeicaoDAO refDAO = DAOFactory.getRefeicaoDAO();		
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();

		Refeicao refeicao = new Refeicao(0, Calendar.getInstance(), "Dieta para os dias corridos.", "Janta");

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

		refeicao.setUsuario(userFull);


		try {			
			refDAO.insert(refeicao);
		} catch (DBException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Get All ============================================");

		List<Refeicao> refList = refDAO.getAll();		
		for(Refeicao ref : refList) {
			System.out.println(ref);
		}

		System.out.println("Get By ID ============================================");

		Refeicao refGet = refDAO.getById(1);
		if(refGet != null) {			
			System.out.println(refGet);
		}


		System.out.println("Editar ============================================");

		refGet.setDescricao("Melhorei a descrição!");
		try {
			refDAO.update(refGet);
			System.out.println("Registro editado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("Get Atualizado  ============================================");

		Refeicao refGet2 = refDAO.getById(1);
		if(refGet2 != null) {			
			System.out.println(refGet2);
		}


		System.out.println("Deletar ============================================");
		try {
			refDAO.delete(1);
			System.out.println("Deletado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
