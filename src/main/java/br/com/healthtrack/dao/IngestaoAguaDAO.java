package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.IngestaoAgua;
import br.com.healthtrack.exception.DBException;

public interface IngestaoAguaDAO {
	List<IngestaoAgua> getAll();
	
	void insert(IngestaoAgua ingestao) throws DBException;
	
	void update(IngestaoAgua ingestao) throws DBException;
	
	IngestaoAgua getById(int id);
	
	void delete(int id) throws DBException;
}
