package br.com.healthtrack.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Refeicao {
	private int id;
	
	private Usuario usuario;
	
	private Calendar dtRefeicao;
	
	private String descricao;
	
	private String nomeRefeicao;

	public Refeicao() {
		super();
	}

	public Refeicao(int id, Calendar dtRefeicao, String descricao, String nomeRefeicao) {
		super();
		this.id = id;
		this.dtRefeicao = dtRefeicao;
		this.descricao = descricao;
		this.nomeRefeicao = nomeRefeicao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataRefeicao() {
		return dtRefeicao;
	}

	public void setDataRefeicao(Calendar dt_refeicao) {
		this.dtRefeicao = dt_refeicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeRefeicao() {
		return nomeRefeicao;
	}

	public void setNomeRefeicao(String nome_refeicao) {
		this.nomeRefeicao = nome_refeicao;
	}	
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Refeição: [código = " + id + ", data refeição = " + sdf.format(dtRefeicao.getTime()) + ", descrição = "
				+ descricao + ", nome refeição: " + nomeRefeicao + ", Nome Usuário: " + usuario.getNome() + "]";
	}
}
