package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.IngestaoAgua;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.IngestaoAguaDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleIngestaoAguaDAO implements IngestaoAguaDAO {
	private Connection connection;

	@Override
	public List<IngestaoAgua> getAll() {
		List<IngestaoAgua> ingestaoAgua = new ArrayList<IngestaoAgua>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_INGESTAO_AGUA inner join T_HT_USUARIO on T_HT_INGESTAO_AGUA.cd_usuario = T_HT_USUARIO.cd_usuario");

			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("CD_INGESTAO");
				double ingestaoQtd = rs.getDouble("QTD_AGUA");
				java.sql.Date dataIngestao = rs.getDate("DT_INGESTAO");
				Calendar dataIngestaoNow = Calendar.getInstance();
				dataIngestaoNow.setTimeInMillis(dataIngestao.getTime());

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

				IngestaoAgua ia = new IngestaoAgua(id, dataIngestaoNow, ingestaoQtd);
				ia.setUsuario(user);
				ingestaoAgua.add(ia);
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

		return ingestaoAgua;
	}

	@Override
	public void insert(IngestaoAgua ingestao) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_INGESTAO_AGUA (CD_INGESTAO, CD_USUARIO, DT_INGESTAO, QTD_AGUA) VALUES(SQ_T_HT_INGESTAO_AGUA.NEXTVAL, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, ingestao.getUsuario().getId());
			java.sql.Date dataReg = new java.sql.Date(ingestao.getData().getTimeInMillis());
			stmt.setDate(2, dataReg);
			stmt.setDouble(3, ingestao.getQuantidadeAgua());

			stmt.executeUpdate();
			System.out.println("Ingestão de água inserida com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar Ingestão de água.");
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
	public IngestaoAgua getById(int id) {
		IngestaoAgua ingestaoAgua = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();

			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_INGESTAO_AGUA inner join T_HT_USUARIO on T_HT_INGESTAO_AGUA.cd_usuario = T_HT_USUARIO.cd_usuario where T_HT_INGESTAO_AGUA.cd_ingestao = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int idIngestao = rs.getInt("CD_INGESTAO");
				double ingestaoQtd = rs.getDouble("QTD_AGUA");
				java.sql.Date dataIngestao = rs.getDate("DT_INGESTAO");
				Calendar dataIngestaoNow = Calendar.getInstance();
				dataIngestaoNow.setTimeInMillis(dataIngestao.getTime());

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

				ingestaoAgua = new IngestaoAgua(idIngestao, dataIngestaoNow, ingestaoQtd);
				ingestaoAgua.setUsuario(user);
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

		return ingestaoAgua;
	}

	@Override
	public void delete(int id) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HT_INGESTAO_AGUA where cd_ingestao = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Ingestão de água deletada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Ocorreu um erro ao deletar Ingestão de água.");
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
	public void update(IngestaoAgua ingestao) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_INGESTAO_AGUA set DT_INGESTAO = ?, QTD_AGUA = ? where cd_ingestao = ?";

			stmt = connection.prepareStatement(sql);
			
			java.sql.Date dataReg = new java.sql.Date(ingestao.getData().getTimeInMillis());
			stmt.setDate(1, dataReg);
			stmt.setDouble(2, ingestao.getQuantidadeAgua());
			stmt.setDouble(3, ingestao.getId());

			stmt.executeUpdate();
			System.out.println("Ingestão de água atualizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar Ingestão de água.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
