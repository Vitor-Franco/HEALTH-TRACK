package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

public interface UsuarioDAO {
	public List<Usuario> getAll();

	public void insert(Usuario usuario) throws DBException;

	public Usuario getUserByCredentials(Usuario u);
}
