package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.dao.AlimentoDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.DBManager;

public class OracleAlimentoDAO implements AlimentoDAO {
	private Connection connection;

	@Override
	public List<Alimento> getAll() {
		List<Alimento> alimentoList = new ArrayList<Alimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement("SELECT * FROM T_HT_ALIMENTO");

			rs = stmt.executeQuery();

			while (rs.next()) {
				int idAlimento = rs.getInt("cd_alimento");
				String nome = rs.getString("nome_alimento");
				int calorias = rs.getInt("calorias_alimento");
				String macroNutrientes = rs.getString("macronutrientes_alimento");

				Alimento a = new Alimento(idAlimento, nome, calorias, macroNutrientes);
				alimentoList.add(a);
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

		return alimentoList;
	}

	@Override
	public Alimento getById(int id) {
		Alimento alimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getInstance().getConnection();
			stmt = connection.prepareStatement(
					"SELECT * FROM T_HT_ALIMENTO where cd_alimento = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idAlimento = rs.getInt("cd_alimento");
				String nome = rs.getString("nome_alimento");
				int calorias = rs.getInt("calorias_alimento");
				String macroNutrientes = rs.getString("macronutrientes_alimento");
			
				alimento = new Alimento(idAlimento, nome, calorias, macroNutrientes);
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

		return alimento;
	}

	@Override
	public void insert(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_ALIMENTO (cd_alimento, nome_alimento, calorias_alimento, macronutrientes_alimento) VALUES (sq_t_ht_alimento.NEXTVAL, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, alimento.getNomeAlimento());
			stmt.setInt(2, alimento.getCalorias());
			stmt.setString(3, alimento.getMacroNutrientes());
			
			stmt.executeUpdate();
			System.out.println("Alimento criado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao criar Alimento: " + alimento.getNomeAlimento());
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
	public void update(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			connection = DBManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_ALIMENTO SET nome_alimento = ?, calorias_alimento = ?, macronutrientes_alimento = ? WHERE cd_alimento = ?";

			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, alimento.getNomeAlimento());
			stmt.setInt(2, alimento.getCalorias());
			stmt.setString(3, alimento.getMacroNutrientes());
			stmt.setInt(4, alimento.getId());

			stmt.executeUpdate();
			System.out.println("Alimento atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar Alimento ID: " + alimento.getId());
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

			String sql = "DELETE FROM T_HT_ALIMENTO WHERE cd_alimento = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Alimento {" + id + "} deletado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Ocorreu um erro ao deletar Alimento ID: " + id + ".");
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
