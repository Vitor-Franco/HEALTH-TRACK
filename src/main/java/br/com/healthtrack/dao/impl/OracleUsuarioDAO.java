package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleUsuarioDAO implements UsuarioDAO {
	private Connection connection;

	@Override
	public List<Usuario> getAll() {
		List<Usuario> users = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement("SELECT * FROM t_ht_usuario");

			rs = stmt.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("cd_usuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int idade = rs.getInt("idade");
				double altura = rs.getDouble("altura");
				char sexo = rs.getString("sexo").charAt(0);

				java.sql.Date dataNascimento = rs.getDate("dt_nasc");
				Calendar dtNasc = Calendar.getInstance();
				dtNasc.setTimeInMillis(dataNascimento.getTime());
				
				Usuario usuario = new Usuario();				
				usuario.setAltura(altura);
				usuario.setEmail(email);
				usuario.setId(id);
				usuario.setIdade(idade);
				usuario.setSexo(sexo);
				usuario.setDataNascimento(dtNasc);
				usuario.setNome(nome);			
				
				users.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	@Override
	public void insert(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO t_ht_usuario(cd_usuario, nome, dt_nasc, email, senha, sexo, altura, idade) VALUES (SQ_T_HT_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			java.sql.Date dataNasc = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(2, dataNasc);
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getPassword());
			stmt.setString(5, String.valueOf(usuario.getSexo()));
			stmt.setDouble(6, usuario.getAltura());
			stmt.setInt(7, usuario.getIdade());

			stmt.executeUpdate();
			System.out.println("Usuário inserido com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar usuário.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Usuario getUserByCredentials(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario user = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement("SELECT * FROM t_ht_usuario WHERE email = ? AND senha = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getPassword());
			rs = stmt.executeQuery();

			if (rs.next()){
				int id = rs.getInt("cd_usuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int idade = rs.getInt("idade");
				double altura = rs.getDouble("altura");
				char sexo = rs.getString("sexo").charAt(0);

				java.sql.Date dataNascimento = rs.getDate("dt_nasc");
				Calendar dtNasc = Calendar.getInstance();
				dtNasc.setTimeInMillis(dataNascimento.getTime());

				user = new Usuario(id, nome, dtNasc, sexo, altura, idade, email, "");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

}
