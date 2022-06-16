package br.com.healthtrack.teste;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

public class TesteUsuario {
	public static void main(String[] args) {
		UsuarioDAO usuarioDao = DAOFactory.getUsuarioDAO(); 

		Usuario u = new Usuario(0, "Vitor Franco", Calendar.getInstance(), "M".charAt(0), 1.80, 21, "vitoroliveirafranco@gmail.com", "123545");	
		try {
			usuarioDao.insert(u);
		} catch (DBException e) {
			e.printStackTrace();
		}


		List<Usuario> usuarios = usuarioDao.getAll();

		for(Usuario usuario : usuarios) {
			System.out.println(usuario.toString());
		}
		
		
		// Usuario u2 = new Usuario(0, "Vitor Franco", Calendar.getInstance(), "M".charAt(0), 1.80, 21, "vitoroliveirafranco@gmail.com", "123545");
		Usuario isLoggedUser = usuarioDao.getUserByCredentials(u);
		System.out.println(isLoggedUser);
	}
}