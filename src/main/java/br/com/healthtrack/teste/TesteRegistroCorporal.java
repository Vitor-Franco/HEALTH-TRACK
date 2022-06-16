package br.com.healthtrack.teste;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.RegistroCorporal;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RegistroCorporalDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

public class TesteRegistroCorporal {

	public static void main(String[] args) {

		RegistroCorporalDAO regDao = DAOFactory.getRegistroCorporalDAO();
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();

		RegistroCorporal reg = new RegistroCorporal(0, 72.0, Calendar.getInstance());

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

		reg.setUsuario(userFull);


		try {			
			regDao.insert(reg);
		} catch (DBException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Get All ============================================");

		List<RegistroCorporal> rcs = regDao.getAll();		
		for(RegistroCorporal rc : rcs) {
			System.out.println(rc);
		}

		System.out.println("Get By ID ============================================");

		RegistroCorporal regGetted = regDao.getById(20);
		System.out.println(regGetted);


		System.out.println("Editar ============================================");

		regGetted.setPeso(101.0);
		try {
			regDao.update(regGetted);
			System.out.println("Registro editado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Deletar ============================================");
		try {
			regDao.delete(19);
			System.out.println("Deletado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
