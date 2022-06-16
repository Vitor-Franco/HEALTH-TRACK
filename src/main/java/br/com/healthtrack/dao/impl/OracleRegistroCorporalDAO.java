package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.RegistroCorporal;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RegistroCorporalDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleRegistroCorporalDAO implements RegistroCorporalDAO {
	private Connection connection;

	@Override
	public List<RegistroCorporal> getAll() {
		List<RegistroCorporal> rc = new ArrayList<RegistroCorporal>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_REGISTRO_CORPORAL inner join T_HT_USUARIO on T_HT_REGISTRO_CORPORAL.cd_usuario = T_HT_USUARIO.cd_usuario");

			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("cd_registro");
				double peso = rs.getDouble("peso");
				// Manipulando a data
				java.sql.Date dataReg = rs.getDate("dt_registro");
				Calendar dataRegistro = Calendar.getInstance();
				dataRegistro.setTimeInMillis(dataReg.getTime());

				int idUser = rs.getInt("cd_usuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int idade = rs.getInt("idade");
				double altura = rs.getDouble("altura");
				char sexo = rs.getString("sexo").charAt(0);
				java.sql.Date dataNascimento = rs.getDate("dt_nasc");
				Calendar dtNasc = Calendar.getInstance();
				dtNasc.setTimeInMillis(dataNascimento.getTime());

				Usuario user = new Usuario();
				user.setAltura(altura);
				user.setEmail(email);
				user.setId(idUser);
				user.setIdade(idade);
				user.setSexo(sexo);
				user.setDataNascimento(dtNasc);
				user.setNome(nome);

				RegistroCorporal registro = new RegistroCorporal(id, peso, dataRegistro);
				registro.setUsuario(user);
				rc.add(registro);
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

		return rc;
	}

	@Override
	public void insert(RegistroCorporal registroCorporal) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_REGISTRO_CORPORAL (cd_registro, cd_usuario, peso, dt_registro) VALUES(SQ_T_HT_REGISTRO_CORPORAL.NEXTVAL, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, registroCorporal.getUsuario().getId());
			stmt.setDouble(2, registroCorporal.getPeso());
			java.sql.Date dataReg = new java.sql.Date(registroCorporal.getData().getTimeInMillis());
			stmt.setDate(3, dataReg);

			stmt.executeUpdate();
			System.out.println("Registro corporal inserido com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar Registro corporal.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(RegistroCorporal registroCorporal) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_REGISTRO_CORPORAL SET peso = ?, dt_registro = ? WHERE cd_registro = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setDouble(1, registroCorporal.getPeso());
			java.sql.Date dataReg = new java.sql.Date(registroCorporal.getData().getTimeInMillis());
			stmt.setDate(2, dataReg);

			stmt.setInt(3, registroCorporal.getId());

			stmt.executeUpdate();
			System.out.println("Registro corporal atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar Registro corporal.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(int id) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HT_REGISTRO_CORPORAL where cd_registro = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Registro corporal deletado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Ocorreu um erro ao deletar registro corporal.");
		} finally {
			try {
				connection.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public RegistroCorporal getById(int idRegistro) {
		RegistroCorporal rc = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();

			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_REGISTRO_CORPORAL inner join T_HT_USUARIO on T_HT_REGISTRO_CORPORAL.cd_usuario = T_HT_USUARIO.cd_usuario where T_HT_REGISTRO_CORPORAL.cd_registro = ?");
			stmt.setInt(1, idRegistro);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("cd_registro");
				double peso = rs.getDouble("peso");
				// Manipulando a data
				java.sql.Date dataReg = rs.getDate("dt_registro");
				Calendar dataRegistro = Calendar.getInstance();
				dataRegistro.setTimeInMillis(dataReg.getTime());

				int idUser = rs.getInt("cd_usuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int idade = rs.getInt("idade");
				double altura = rs.getDouble("altura");
				char sexo = rs.getString("sexo").charAt(0);
				java.sql.Date dataNascimento = rs.getDate("dt_nasc");
				Calendar dtNasc = Calendar.getInstance();
				dtNasc.setTimeInMillis(dataNascimento.getTime());

				Usuario user = new Usuario();
				user.setAltura(altura);
				user.setEmail(email);
				user.setId(idUser);
				user.setIdade(idade);
				user.setSexo(sexo);
				user.setDataNascimento(dtNasc);
				user.setNome(nome);

				rc = new RegistroCorporal(id, peso, dataRegistro);
				rc.setUsuario(user);
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

		return rc;
	}
}
