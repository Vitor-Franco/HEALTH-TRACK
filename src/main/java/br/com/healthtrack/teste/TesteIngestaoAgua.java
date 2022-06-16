package br.com.healthtrack.teste;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.IngestaoAgua;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.IngestaoAguaDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

public class TesteIngestaoAgua {

	public static void main(String[] args) {
		IngestaoAguaDAO ingestaoAguaDao = DAOFactory.getIngestaoAguaDAO();
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();

		IngestaoAgua ia = new IngestaoAgua(0, Calendar.getInstance(), 3.735);

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

		ia.setUsuario(userFull);


		try {			
			ingestaoAguaDao.insert(ia);
		} catch (DBException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Get All ============================================");

		List<IngestaoAgua> iaList = ingestaoAguaDao.getAll();		
		for(IngestaoAgua ias : iaList) {
			System.out.println(ias);
		}

		System.out.println("Get By ID ============================================");

		IngestaoAgua iaGet = ingestaoAguaDao.getById(1);
		if(iaGet != null) {			
			System.out.println(iaGet);
		}


		System.out.println("Editar ============================================");

		iaGet.setQuantidadeAgua(9.330);
		try {
			ingestaoAguaDao.update(iaGet);
			System.out.println("Registro editado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		System.out.println("Deletar ============================================");
		try {
			ingestaoAguaDao.delete(1);
			System.out.println("Deletado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
