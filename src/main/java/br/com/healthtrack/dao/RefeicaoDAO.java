package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.exception.DBException;

public interface RefeicaoDAO {
	List<Refeicao> getAll();

	Refeicao getById(int id);

	void insert(Refeicao refeicao) throws DBException;

	void update(Refeicao refeicao) throws DBException;

	void delete(int id) throws DBException;
}
