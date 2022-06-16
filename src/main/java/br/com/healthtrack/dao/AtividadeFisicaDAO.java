package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.AtividadeFisica;
import br.com.healthtrack.exception.DBException;

public interface AtividadeFisicaDAO {
	List<AtividadeFisica> getAll();
	
	void insert(AtividadeFisica atividade) throws DBException;
	
	void update(AtividadeFisica atividade) throws DBException;
	
	AtividadeFisica getById(int id);
	
	void delete(int id) throws DBException;
}
