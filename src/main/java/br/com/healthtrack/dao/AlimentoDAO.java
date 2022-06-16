package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.exception.DBException;

public interface AlimentoDAO {
	List<Alimento> getAll();

	Alimento getById(int id);

	void insert(Alimento alimento) throws DBException;

	void update(Alimento alimento) throws DBException;

	void delete(int id) throws DBException;
}
