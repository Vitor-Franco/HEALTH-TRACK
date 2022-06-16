package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.AtividadeFisica;
import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.bean.RefeicaoAlimento;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.RefeicaoAlimentoDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleRefeicaoAlimentoDAO implements RefeicaoAlimentoDAO {
	private Connection connection;

	@Override
	public List<RefeicaoAlimento> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefeicaoAlimento getById(int id) {
		RefeicaoAlimento refeicaoAlimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();

			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_REFEICAO_ALIMENTO inner join T_HT_ALIMENTO on T_HT_REFEICAO_ALIMENTO.cd_alimento = T_HT_ALIMENTO.cd_alimento INNER JOIN T_HT_REFEICAO ON T_HT_REFEICAO.cd_refeicao = T_HT_REFEICAO_ALIMENTO.cd_refeicao WHERE T_HT_REFEICAO_ALIMENTO.cd_refeicao_alimento = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int idRefeicaoAlimento = rs.getInt("CD_REFEICAO_ALIMENTO");
				

				int idRefeicao = rs.getInt("cd_refeicao");
				java.sql.Date dataRefeicao = rs.getDate("dt_refeicao");
				Calendar dtRefeicaoNow = Calendar.getInstance();
				dtRefeicaoNow.setTimeInMillis(dataRefeicao.getTime());
				String descricao = rs.getString("desc_refeicao");
				String nome_refeicao = rs.getString("nome_refeicao");
				
				Refeicao r = new Refeicao(id, dtRefeicaoNow, descricao, nome_refeicao);
				
				
				int idAlimento = rs.getInt("cd_alimento");
				String nome = rs.getString("nome_alimento");
				int calorias = rs.getInt("calorias_alimento");
				String macroNutrientes = rs.getString("macronutrientes_alimento");

				Alimento a = new Alimento(idAlimento, nome, calorias, macroNutrientes);
				
				int quantidade = rs.getInt("QUANTIDADE");
				String medida = rs.getString("TIPO_MEDIDADE"); 
				
				
				java.sql.Date dataAtividade = rs.getDate("DT_ATIVIDADE");
				Calendar dataAtividadeNow = Calendar.getInstance();
				dataAtividadeNow.setTimeInMillis(dataAtividade.getTime());
				
				refeicaoAlimento = new RefeicaoAlimento(idRefeicao, quantidade, medida);
				refeicaoAlimento.setAlimento(a);
				refeicaoAlimento.setRefeicao(r);
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

		return refeicaoAlimento;
	}

	@Override
	public void insert(RefeicaoAlimento refAlimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_REFEICAO_ALIMENTO (CD_REFEICAO_ALIMENTO, CD_REFEICAO, CD_ALIMENTO, QUANTIDADE, TIPO_MEDIDADE) VALUES(SQ_T_HT_REFEICAO_ALIMENTO.NEXTVAL, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, refAlimento.getRefeicao().getId());
			stmt.setInt(2, refAlimento.getAlimento().getId());
			stmt.setInt(3, refAlimento.getQuantidade());
			stmt.setString(4, refAlimento.getMedida());

			stmt.executeUpdate();
			System.out.println("Refeição | Alimento inserida com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar Refeição | Alimento.");
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
	public void update(RefeicaoAlimento refAlimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_REFEICAO_ALIMENTO SET QUANTIDADE = ?, TIPO_MEDIDADE = ? WHERE cd_refeicao_alimento = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, refAlimento.getQuantidade());
			stmt.setString(2, refAlimento.getMedida());
			stmt.setInt(3, refAlimento.getRefeicao().getId());

			stmt.executeUpdate();
			System.out.println("Refeição | Alimento alterada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao alterar Refeição | Alimento.");
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
			String sql = "DELETE FROM T_HT_REFEICAO_ALIMENTO where cd_refeicao_alimento = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Refeição | alimento deletada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Ocorreu um erro ao deletar Refeição | Alimento.");
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
