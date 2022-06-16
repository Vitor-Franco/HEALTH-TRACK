package br.com.healthtrack.factory;

import br.com.healthtrack.dao.AlimentoDAO;
import br.com.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtrack.dao.IngestaoAguaDAO;
import br.com.healthtrack.dao.RefeicaoDAO;
import br.com.healthtrack.dao.RegistroCorporalDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.dao.impl.OracleAlimentoDAO;
import br.com.healthtrack.dao.impl.OracleAtividadeFisicaDAO;
import br.com.healthtrack.dao.impl.OracleIngestaoAguaDAO;
import br.com.healthtrack.dao.impl.OracleRefeicaoDAO;
import br.com.healthtrack.dao.impl.OracleRegistroCorporalDAO;
import br.com.healthtrack.dao.impl.OracleUsuarioDAO;

public abstract class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

	public static RegistroCorporalDAO getRegistroCorporalDAO() {
		return new OracleRegistroCorporalDAO();
	}

	public static IngestaoAguaDAO getIngestaoAguaDAO() {
		return new OracleIngestaoAguaDAO();
	}
	
	public static AtividadeFisicaDAO getAtividadeFisicaDAO() {
		return new OracleAtividadeFisicaDAO();
	}
	
	public static RefeicaoDAO getRefeicaoDAO() {
		return new OracleRefeicaoDAO();
	}
	
	public static AlimentoDAO getAlimentoDAO() {
		return new OracleAlimentoDAO();
	}
}
