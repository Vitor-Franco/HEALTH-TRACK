package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.RefeicaoAlimento;
import br.com.healthtrack.exception.DBException;

public interface RefeicaoAlimentoDAO {
	List<RefeicaoAlimento> getAll();

	RefeicaoAlimento getById(int id);

	void insert(RefeicaoAlimento refAlimento) throws DBException;

	void update(RefeicaoAlimento refAlimento) throws DBException;

	void delete(int id) throws DBException;
}
