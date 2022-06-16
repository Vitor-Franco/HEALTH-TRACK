package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RefeicaoDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleRefeicaoDAO implements RefeicaoDAO {
	private Connection connection;

	@Override
	public List<Refeicao> getAll() {
		List<Refeicao> refeicaoList = new ArrayList<Refeicao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_REFEICAO inner join T_HT_USUARIO on T_HT_REFEICAO.cd_usuario = T_HT_USUARIO.cd_usuario");

			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("cd_refeicao");
				java.sql.Date dataRefeicao = rs.getDate("dt_refeicao");
				Calendar dtRefeicaoNow = Calendar.getInstance();
				dtRefeicaoNow.setTimeInMillis(dataRefeicao.getTime());
				String descricao = rs.getString("desc_refeicao");
				String nome_refeicao = rs.getString("nome_refeicao");

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

				Refeicao r = new Refeicao(id, dtRefeicaoNow, descricao, nome_refeicao);
				r.setUsuario(user);

				refeicaoList.add(r);
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

		return refeicaoList;
	}

	@Override
	public Refeicao getById(int id) {
		Refeicao refeicao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_REFEICAO inner join T_HT_USUARIO on T_HT_REFEICAO.cd_usuario = T_HT_USUARIO.cd_usuario where cd_refeicao = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idRefeicao = rs.getInt("cd_refeicao");
				java.sql.Date dataRefeicao = rs.getDate("dt_refeicao");
				Calendar dtRefeicaoNow = Calendar.getInstance();
				dtRefeicaoNow.setTimeInMillis(dataRefeicao.getTime());
				String descricao = rs.getString("desc_refeicao");
				String nome_refeicao = rs.getString("nome_refeicao");

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

				refeicao = new Refeicao(idRefeicao, dtRefeicaoNow, descricao, nome_refeicao);
				refeicao.setUsuario(user);
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

		return refeicao;
	}

	@Override
	public void insert(Refeicao refeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_REFEICAO (cd_refeicao, cd_usuario, dt_refeicao, desc_refeicao, nome_refeicao) VALUES(sq_t_ht_refeicao.NEXTVAL, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, refeicao.getUsuario().getId());
			java.sql.Date dataRef = new java.sql.Date(refeicao.getDataRefeicao().getTimeInMillis());
			stmt.setDate(2, dataRef);
			stmt.setString(3, refeicao.getDescricao());
			stmt.setString(4, refeicao.getNomeRefeicao());

			stmt.executeUpdate();
			System.out.println("Refeição inserida com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar Refeição.");
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
	public void update(Refeicao refeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_REFEICAO SET dt_refeicao = ?, desc_refeicao = ?, nome_refeicao = ? WHERE cd_refeicao = ?";

			stmt = connection.prepareStatement(sql);
			java.sql.Date dataRef = new java.sql.Date(refeicao.getDataRefeicao().getTimeInMillis());
			stmt.setDate(1, dataRef);
			
			stmt.setString(2, refeicao.getDescricao());
			stmt.setString(3, refeicao.getNomeRefeicao());
			stmt.setInt(4, refeicao.getId());

			stmt.executeUpdate();
			System.out.println("Refeição atualizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar Refeição ID: " + refeicao.getId());
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

			String sql = "DELETE FROM T_HT_REFEICAO WHERE cd_refeicao = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Refeição " + id + " deletada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Ocorreu um erro ao deletar Refeição ID: " + id + ".");
		} finally {
			try {
				connection.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
