package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.AtividadeFisica;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleAtividadeFisicaDAO implements AtividadeFisicaDAO {
	private Connection connection;

	@Override
	public List<AtividadeFisica> getAll() {
		List<AtividadeFisica> atividadeFisicaList = new ArrayList<AtividadeFisica>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_ATIVIDADE_FISICA inner join T_HT_USUARIO on T_HT_ATIVIDADE_FISICA.cd_usuario = T_HT_USUARIO.cd_usuario");

			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("CD_ATIVIDADE");
				String tipo = rs.getString("TIPO_ATIVIDADE");
				String nome = rs.getString("NOME_ATIVIDADE");
				int calorias = rs.getInt("CALORIAS");
				String descricao = rs.getString("DESCRICAO");

				java.sql.Date duracao = rs.getDate("DURACAO");
				Calendar duracaoNow = Calendar.getInstance();
				duracaoNow.setTimeInMillis(duracao.getTime());

				java.sql.Date dataAtv = rs.getDate("DT_ATIVIDADE");
				Calendar dataAtvNow = Calendar.getInstance();
				dataAtvNow.setTimeInMillis(dataAtv.getTime());

				int idUser = rs.getInt("cd_usuario");
				String nomeUsuario = rs.getString("nome");
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
				user.setNome(nomeUsuario);

				AtividadeFisica af = new AtividadeFisica(id, tipo, dataAtvNow, duracaoNow, nome, calorias, descricao);
				af.setUsuario(user);
				atividadeFisicaList.add(af);
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

		return atividadeFisicaList;
	}

	@Override
	public void insert(AtividadeFisica atividade) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_ATIVIDADE_FISICA (CD_ATIVIDADE, CD_USUARIO, TIPO_ATIVIDADE, DT_ATIVIDADE, DURACAO, NOME_ATIVIDADE, CALORIAS, DESCRICAO) VALUES(SQ_T_HT_ATIVIDADE_FISICA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, atividade.getUsuario().getId());
			stmt.setString(2, atividade.getTipoAtividade());

			java.sql.Date dataAtv = new java.sql.Date(atividade.getData().getTimeInMillis());
			stmt.setDate(3, dataAtv);

			java.sql.Date dataDuracao = new java.sql.Date(atividade.getDuracao().getTimeInMillis());
			stmt.setDate(4, dataDuracao);
			stmt.setString(5, atividade.getNome());
			stmt.setInt(6, atividade.getCalorias());
			stmt.setString(7, atividade.getDescricao());

			stmt.executeUpdate();
			System.out.println("Atividade física inserida com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar Atividade física.");
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
	public void update(AtividadeFisica atividade) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_ATIVIDADE_FISICA set TIPO_ATIVIDADE = ?, DT_ATIVIDADE = ?, DURACAO = ?, NOME_ATIVIDADE = ?, CALORIAS = ?, DESCRICAO = ? where cd_atividade = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setString(1, atividade.getTipoAtividade());

			java.sql.Date dataAtv = new java.sql.Date(atividade.getData().getTimeInMillis());
			stmt.setDate(2, dataAtv);
			
			java.sql.Date dataDuracao = new java.sql.Date(atividade.getDuracao().getTimeInMillis());
			stmt.setDate(3, dataDuracao);
			stmt.setString(4, atividade.getNome());
			stmt.setInt(5, atividade.getCalorias());
			stmt.setString(6, atividade.getDescricao());

			stmt.setInt(7, atividade.getId());
			stmt.executeUpdate();
			System.out.println("Atividade física atualizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar Atividade física.");
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
	public AtividadeFisica getById(int id) {
		AtividadeFisica atividadeFisica = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();

			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_ATIVIDADE_FISICA inner join T_HT_USUARIO on T_HT_ATIVIDADE_FISICA.cd_usuario = T_HT_USUARIO.cd_usuario where T_HT_ATIVIDADE_FISICA.cd_atividade = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next()) {
			
				int idAtividade = rs.getInt("CD_ATIVIDADE");
				String tipo = rs.getString("TIPO_ATIVIDADE");
				
				java.sql.Date dataAtividade = rs.getDate("DT_ATIVIDADE");
				Calendar dataAtividadeNow = Calendar.getInstance();
				dataAtividadeNow.setTimeInMillis(dataAtividade.getTime());
				
				java.sql.Date dataDuracao = rs.getDate("DURACAO");
				Calendar dataDuracaoNow = Calendar.getInstance();
				dataDuracaoNow.setTimeInMillis(dataDuracao.getTime());

				String nomeAtividade = rs.getString("NOME_ATIVIDADE");
				int calorias = rs.getInt("CALORIAS");
				String descricao = rs.getString("DESCRICAO");
				
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

				atividadeFisica = new AtividadeFisica(idAtividade, tipo, dataAtividadeNow, dataDuracaoNow, nomeAtividade, calorias, descricao);
				atividadeFisica.setUsuario(user);
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

		return atividadeFisica;
	}

	@Override
	public void delete(int id) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HT_ATIVIDADE_FISICA where cd_atividade = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Atividade física deletada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Ocorreu um erro ao deletar Atividade física.");
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
