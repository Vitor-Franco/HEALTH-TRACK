package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.RegistroCorporal;
import br.com.healthtrack.exception.DBException;

public interface RegistroCorporalDAO {
	void insert(RegistroCorporal rc) throws DBException;

	void update(RegistroCorporal rc) throws DBException;

	void delete(int id) throws DBException;

	RegistroCorporal getById(int id);

	List<RegistroCorporal> getAll();
}
